package com.cychop.til.gapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cychop.til.duration.Duration;
import com.cychop.til.exceptions.DataStructureException;
import com.cychop.til.exceptions.DataValueException;
import com.cychop.til.exceptions.SourceUnavailableException;

/**
 * Servlet designed for mobile devices. Provides raw information that a mobile
 * device application can display with an appropriate layout.
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 * 
 */
public class MobileDeviceServlet extends HttpServlet {

	private static final long serialVersionUID = -3617917855697342353L;

	private static Logger log;
	static {
		log = Logger.getLogger(MobileDeviceServlet.class.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");

		log.fine("--------> entered MobileDeviceServlet.doGet");
		log.info("+--------------------------------------------------+\n"
				+ "|      NEW CONNEXION TO MOBILE DEVICE SERVLET      |\n"
				+ "+--------------------------------------------------+");

		String lg = req.getParameter("lg");
		if (lg == null) {
			log.fine("Page was called without a language param, default is English");
			lg = "en";
		} else {
			log.fine("Requested lanquage: " + lg);
		}
		Locale lgLoc = new Locale(lg);
		
		try {
			// getting data from CITA website
			ArrayList<Duration> durations = InfoGetter.getCitaInfo();

			// if we got here without any Exception caught, we can assume the
			// display will be alright. RC=0
			resp.getWriter().println("0");

			for (Duration duration : durations) {
				resp.getWriter().println(duration.toString(lgLoc));
			}
		} catch (SourceUnavailableException sue) {
			// Data is unavailable
			log.warning("SourceUnavailableException: " + sue.getMsg());
			resp.getWriter().println("1");
		} catch (DataValueException dve) {
			// Data looks wrong
			log.severe("DataValueException: " + dve.getMsg());
			resp.getWriter().println("2");
		} catch (DataStructureException dse) {
			// Data syntax is wrong
			log.severe("DataStructureException: " + dse.getMsg());
			resp.getWriter().println("3");
		}

		log.info("+--------------------------------------------------+\n"
				+ "|    END OF CONNEXION TO MOBILE DEVICE SERVLET     |\n"
				+ "+--------------------------------------------------+");
		log.fine("--------< exiting MobileDeviceServlet.doGet");
	}
}
