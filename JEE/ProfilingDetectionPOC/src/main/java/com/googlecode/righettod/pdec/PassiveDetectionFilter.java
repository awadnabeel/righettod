package com.googlecode.righettod.pdec;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedPolicy;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter managing detection of passive profiling.<br>
 * Here, for the example, we simply block the connection as defensive measure.<br>
 * Set of command to test the filter (send all in sequence):<br>
 * <ul>
 * <li>curl http://localhost:9344/profdec/create</li>
 * <li>curl http://localhost:9344/profdec/read</li>
 * <li>curl http://localhost:9344/profdec/update</li>
 * <li>curl http://localhost:9344/profdec/delete</li>
 * <li>curl http://localhost:9344/profdec/delete</li>
 * </ul>
 * 
 * @author XK317 (Dominique RIGHETTO)
 * 
 * 
 */
@WebFilter("/*")
public class PassiveDetectionFilter implements Filter {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(PassiveDetectionFilter.class);

	/**
	 * Ref on storage.
	 */
	@Resource(name = "jdbc/storeDS")
	private DataSource storageDS = null;

	/**
	 * List of features of the application (URI here).
	 */
	private List<String> appFeaturesList = new ArrayList<>();

	/** Blocked sender cache. */
	private CacheManager cacheManager = null;
	private Cache<String, Boolean> blockedSenderCache = null;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.blockedSenderCache.close();
		this.cacheManager.close();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Load the list of features of the application (URI here)
		String query = "select feature_uri from app_feature_ref";
		try (Connection dbCon = this.storageDS.getConnection()) {
			try (Statement stmt = dbCon.createStatement()) {
				try (ResultSet rs = stmt.executeQuery(query)) {
					while (rs.next()) {
						this.appFeaturesList.add(rs.getString("feature_uri").toLowerCase(Locale.ENGLISH).trim());
					}
				}
			}
		}
		catch (SQLException e) {
			throw new ServletException("Unable to correctly load the list of features of the application !", e);
		}
		// Initialize the cache for the Blocked sender list: put the retention to 1 hour
		this.cacheManager = Caching.getCachingProvider().getCacheManager();
		MutableConfiguration<String, Boolean> config = new MutableConfiguration<>();
		config.setExpiryPolicyFactory(TouchedPolicy.factoryOf(new Duration(TimeUnit.HOURS, Long.parseLong("1"))));
		this.cacheManager.configureCache("CacheBlockedSender", config);
		this.blockedSenderCache = this.cacheManager.getCache("CacheBlockedSender");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fChain) throws IOException, ServletException {
		HttpServletRequest hReq = (HttpServletRequest) req;
		HttpServletResponse hResp = (HttpServletResponse) resp;
		String queryPrototype = null;

		try {
			/* Step 1a: Generate Unique Identifier for current request */
			LOG.info("PassiveDetectionFilter::Step01a");
			String visitorId = Utils.generateID(hReq);

			/* Step 1b: Check if the sender is into the blocked sender cache */
			LOG.info("PassiveDetectionFilter::Step01b");
			if (this.blockedSenderCache.containsKey(visitorId)) {
				LOG.info("===> '{}' blocked !", visitorId);
				hResp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}

			/* Step 2: Store visit informations (store the hit) */
			LOG.info("PassiveDetectionFilter::Step02");
			queryPrototype = "insert into visit (visitor_id,feature_uri,last_visit) values (?,?,?)";
			Date currentSqlDate = new Date(Calendar.getInstance().getTime().getTime());
			try (Connection dbCon = this.storageDS.getConnection()) {
				try (PreparedStatement prep = dbCon.prepareStatement(queryPrototype)) {
					prep.setString(1, visitorId);
					prep.setString(2, hReq.getRequestURI());
					prep.setDate(3, currentSqlDate);
					prep.execute();
					dbCon.commit();
				}
				catch (SQLException e) {
					dbCon.rollback();
					throw e;
				}
			}

			/* Step 3: Check if, for the last two weeks, the visitor has visited all the application functionalities */
			// Load all uri visited by the visitor in the timeframe
			LOG.info("PassiveDetectionFilter::Step03");
			List<String> appFeaturesVisited = new ArrayList<>();
			queryPrototype = "select feature_uri from visit where last_visit between ? and ?";
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -14);
			Date lessTwoWeeksSqlDate = new Date(c.getTime().getTime());
			try (Connection dbCon = this.storageDS.getConnection()) {
				try (PreparedStatement prep = dbCon.prepareStatement(queryPrototype)) {
					prep.setDate(1, lessTwoWeeksSqlDate);
					prep.setDate(2, currentSqlDate);
					try (ResultSet rs = prep.executeQuery()) {
						while (rs.next()) {
							appFeaturesVisited.add(rs.getString("feature_uri").toLowerCase(Locale.ENGLISH).trim());
						}
					}
				}
			}
			// Compare list of visited features according to list of application features
			int matchCount = 0;
			for (String uri : this.appFeaturesList) {
				if (appFeaturesVisited.contains(uri)) {
					matchCount++;
				}
			}
			if (matchCount == this.appFeaturesList.size()) {
				// Here the visitor has visited all the application features into the timeframes
				// ----Send warning to to a monitoring system
				this.sendWarningAboutVisitor(hReq, visitorId);
				// ----Remove the visit data associated with the visitor flaged as suspicious
				queryPrototype = "delete from visit where visitor_id = ?";
				try (Connection dbCon = this.storageDS.getConnection()) {
					try (PreparedStatement prep = dbCon.prepareStatement(queryPrototype)) {
						dbCon.setAutoCommit(false);
						prep.setString(1, visitorId);
						prep.execute();
						dbCon.commit();
					}
					catch (SQLException e) {
						dbCon.rollback();
						throw e;
					}
				}
				// Stop request in processing flow
				LOG.info("===> '{}' stopped !", visitorId);
				hResp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}

			// Continue filter chain
			LOG.info("PassiveDetectionFilter::Out");
			fChain.doFilter(req, resp);
		}
		catch (Exception e) {
			throw new ServletException("Unable to correctly handle current request !", e);
		}

	}

	/**
	 * Method to send warning to to a monitoring system in order to generate an alert and launch a review <br>
	 * of the sender information in order to decide if aggressive defensive measure should be taken against him.<br>
	 * Here, for the example, we simply add the visitor to the blocked sender cache.
	 * 
	 * @param req HTTP request information.
	 * @param visitorId Visitor unique ID generated to track it.
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unused" })
	private void sendWarningAboutVisitor(HttpServletRequest req, String visitorId) throws SQLException {
		LOG.warn("PassiveDetectionFilter::Warning sent for visitor '{}'.", visitorId);
		// Add the visitor to the blocked sender cache.
		this.blockedSenderCache.put(visitorId, Boolean.TRUE);
	}
}
