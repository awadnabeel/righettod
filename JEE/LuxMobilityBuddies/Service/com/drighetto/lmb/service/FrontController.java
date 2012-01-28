package com.drighetto.lmb.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drighetto.lmb.persistence.dao.HowaldTunnelStatisticsDao;
import com.drighetto.lmb.persistence.dao.ParkingRssDao;
import com.drighetto.lmb.persistence.model.Parking;
import com.drighetto.lmb.persistence.model.Ride;
import com.drighetto.lmb.persistence.type.Rides;
import com.drighetto.lmb.transversal.error.LMBException;

/**
 * Filter acting as a FrontController in order to provide data to the view using
 * the persistence layer (and model VO for data carrying)
 * 
 * @see "http://java.sun.com/blueprints/corej2eepatterns/Patterns/FrontController.html"
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class FrontController implements Filter {

	/** Class logger */
	private static final Logger LOGGER = Logger.getLogger(FrontController.class.getName());

	/** Transation message for french used by Parking Buddy */
	private Properties translationsFR = new Properties();

	/** Transation message for german used by Parking Buddy */
	private Properties translationsDE = new Properties();

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LOGGER.info("FrontController::Init...");
		/* Initialize translations bundle for the parking buddy */
		try {
			this.translationsFR.load(this.getClass().getResourceAsStream("/parking_uimessages_fr.properties"));
			this.translationsDE.load(this.getClass().getResourceAsStream("/parking_uimessages_de.properties"));
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
			throw new ServletException(e);
		}
		LOGGER.info("FrontController::Init...OK");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		LOGGER.info("FrontController::Destroy");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		/* Case for the PARKING buddy */
		if (req.getRequestURI().toLowerCase().startsWith("/parking/")) {
			// UI translation : FR or DE because feed only support these
			// languages
			if ("de".equalsIgnoreCase(req.getLocale().getLanguage())) {
				req.setAttribute("I18N", this.translationsDE);
			} else {
				req.setAttribute("I18N", this.translationsFR);
			}
			// Determine locale to use for the feed according to the remark
			// above
			String localeFeed = req.getLocale().getLanguage();
			if (!"fr".equalsIgnoreCase(localeFeed) && !"de".equalsIgnoreCase(localeFeed)) {
				localeFeed = "fr";
			}
			// Parkings informations
			List<Parking> lst = null;
			try {
				lst = ParkingRssDao.retrieveParkingInformations(localeFeed);
				// Sort parking by name using comparable implementation of the
				// Parking VO...
				Collections.sort(lst);
				req.setAttribute("PARKING", lst);
			} catch (LMBException e) {
				LOGGER.severe(e.getMessage());
				req.removeAttribute("PARKING");
			}
			// Case where details of a parking is requested
			if (req.getRequestURI().toLowerCase().endsWith("details.jsp")) {
				// Get the name of the parking request
				String pname = req.getParameter("pname");
				if (lst != null) {
					int pos = Collections.binarySearch(lst, new Parking(pname));
					req.setAttribute("PARKING", lst.get(pos));
				}
			}
		}

		/* Case for the TUNNEL HOWALD buddy */
		if (req.getRequestURI().toLowerCase().startsWith("/tunnelhowald/")) {
			try {
				// Get ride informations
				Rides rd = Rides.BERTRANGE_HOWALD;
				if (req.getParameter("ride") != null) {
					for (Rides r : Rides.values()) {
						if (r.getDriveWayCode().replaceAll(" > ", "").trim().equalsIgnoreCase(req.getParameter("ride"))) {
							rd = r;
							break;
						}
					}
				}
				Ride r = HowaldTunnelStatisticsDao.retrieveRideInformations(rd);
				// Store ride informations in a request attribute
				req.setAttribute("THRIDE", r);
			} catch (LMBException e) {
				LOGGER.severe(e.getMessage());
				req.removeAttribute("THRIDE");
			}
		}

		// We continue the chain...
		chain.doFilter(req, resp);
	}
}
