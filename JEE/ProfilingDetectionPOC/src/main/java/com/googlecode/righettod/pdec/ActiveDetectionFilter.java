package com.googlecode.righettod.pdec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter managing detection of active profiling.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@WebFilter("/*")
public class ActiveDetectionFilter implements Filter {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(ActiveDetectionFilter.class);

	/** Name of fake cookie used */
	private static final String FAKE_COOKIE_NAME = "verbose_mode";

	/** Value of fake cookie used */
	private static final String FAKE_COOKIE_VALUE = "false";

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
	public void init(FilterConfig fc) throws ServletException {
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

		try {
			/* Step 1a: Generate Unique Identifier for current request */
			LOG.info("ActiveDetectionFilter::Step01a");
			String visitorId = Utils.generateID(hReq);

			/* Step 1b: Check if the sender is into the blocked sender cache */
			LOG.info("ActiveDetectionFilter::Step01b");
			if (this.blockedSenderCache.containsKey(visitorId)) {
				LOG.info("===> '{}' blocked !", visitorId);
				hResp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}

			/* Step 2: Check if fake cookie is present into request, if yes grab value during check */
			LOG.info("ActiveDetectionFilter::Step02");
			String fakeCookieValue = "";
			if (hReq.getCookies() != null) {
				for (Cookie c : hReq.getCookies()) {
					// Explicitly concat value in case of multi submit of same cookie name
					if (FAKE_COOKIE_NAME.equalsIgnoreCase(c.getName())) {
						fakeCookieValue = fakeCookieValue.concat(c.getValue());
					}
				}
			}

			/* Step 3: Check if we are received or not the cookie */
			LOG.info("ActiveDetectionFilter::Step03");
			// If we have received the value we check the values against the expected values
			if (!"".equals(fakeCookieValue) && !fakeCookieValue.equalsIgnoreCase(FAKE_COOKIE_VALUE)) {
				// Cookie value has been modified
				// Send warning to to a monitoring system
				this.sendWarningAboutVisitor(hReq, visitorId);
				// Stop request in processing flow
				LOG.info("===> '{}' stopped !", visitorId);
				hResp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			// If we have received nothing we assume that is the first call of the sender
			// the we create the value and cookie
			Cookie c = new Cookie(FAKE_COOKIE_NAME, FAKE_COOKIE_VALUE);
			c.setMaxAge(86400);// 1 day
			c.setHttpOnly(true);// Explicitly secure it
			hResp.addCookie(c);
			LOG.info("===> Cookie issued to '{}'.", visitorId);

			// Continue filter chain
			LOG.info("ActiveDetectionFilter::Out");
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
		LOG.warn("ActiveDetectionFilter::Warning sent for visitor '{}'.", visitorId);
		// Add the visitor to the blocked sender cache.
		this.blockedSenderCache.put(visitorId, Boolean.TRUE);
	}
}
