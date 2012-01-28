package com.drighetto.lmb.persistence.dao;

import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import com.drighetto.lmb.persistence.model.Ride;
import com.drighetto.lmb.persistence.type.Rides;
import com.drighetto.lmb.transversal.error.LMBException;

/**
 * DAO to access to Howald tunnel rides informations using the dedicated CITA
 * website HTML page informations
 * 
 * @see "http://tunnel.cita.lu/fr/statistiques"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public abstract class HowaldTunnelStatisticsDao {

	/** URL of the HTML page */
	private static final String HTML_URL = "http://tunnel.cita.lu/fr/statistiques";

	/**
	 * Method to obtains ride informations from HTML page
	 * 
	 * @param rides
	 *        Ride desired
	 * @return the ride informations through a VO
	 * @throws LMBException
	 */
	public static Ride retrieveRideInformations(Rides rides) throws LMBException {
		Ride ride = null;
		try {
			/*
			 * Step 1 : Use a HTML parser (JTidy) to load the page as XML object
			 * document
			 */
			Tidy tidy = new Tidy();
			tidy.setIndentContent(false);
			tidy.setHideComments(true);
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setShowErrors(0);
			tidy.setXHTML(false);
			tidy.setSmartIndent(false);
			Document doc = tidy.parseDOM(new URL(HTML_URL).openStream(), null);

			/*
			 * Step 2 : Get the ID identifying the content according to the ride
			 * specified (the content is stored in a DIV)
			 */
			String divID = "";
			switch (rides) {
			case BERCHEM_HOWALD: {
				divID = "stats-3";
				break;
			}
			case IRRGARTEN_HOWALD: {
				divID = "stats-2";
				break;
			}
			case BERTRANGE_HOWALD: {
				divID = "stats-1";
				break;
			}
			default: {
				divID = "stats-1";
				break;
			}
			}

			/* Step 3 : Use XPATH to extract ride informations */
			XPath xp = XPathFactory.newInstance().newXPath();
			// Extract the short name
			String expr = "//div[@id='" + divID + "']/div[@class='header']/h3/text()";
			String rideShortName = xp.evaluate(expr, doc);
			// Extract the display name
			expr = "//div[@id='" + divID + "']/div[@class='header']/span/text()";
			String rideDisplayName = xp.evaluate(expr, doc);
			// Extract the current delay
			expr = "//div[@id='" + divID + "']/ul/li[@class='time-actu']/span/text()";
			int rideCurrentDelay = Integer.parseInt(xp.evaluate(expr, doc).trim().split(" ")[0]);
			// Extract the average delay
			expr = "//div[@id='" + divID + "']/ul/li[@class='time-average']/span/text()";
			int rideAverageDelay = Integer.parseInt(xp.evaluate(expr, doc).trim().split(" ")[0]);
			// Extract the max delay
			expr = "//div[@id='" + divID + "']/ul/li[@class='time-max']/span/text()";
			int rideMaximumDelay = Integer.parseInt(xp.evaluate(expr, doc).trim().split(" ")[0]);

			/* Step 4 : Create the storage VO */
			ride = new Ride(rideShortName, rideDisplayName, rideCurrentDelay, rideAverageDelay, rideMaximumDelay);

		} catch (Exception e) {
			throw new LMBException(e);
		}

		return ride;
	}

}
