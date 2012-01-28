package com.cychop.til.gapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import com.cychop.til.duration.Duration;
import com.cychop.til.duration.InstantDuration;
import com.cychop.til.exceptions.DataStructureException;
import com.cychop.til.exceptions.DataValueException;
import com.cychop.til.exceptions.SourceUnavailableException;

/**
 * Class for access to CITA data. This class accesses the CITA website to get
 * the traffic information, then stores the results in order to compute
 * statistics about the information when it is asked and returns the results to
 * the caller.<br>
 * <br>
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org) and Dominique
 *         Righetto (dominique.righetto@gmail.com)
 * @version 1.0
 * 
 * 
 */
// DEFAULT ACCESS: this class can be accessed only from the Google App
public final class InfoGetter {

	/**
	 * The URL the CITA file can be obtained
	 */
	private final static String TRAFFIC_URL = "http://www2.pch.etat.lu/info_trafic/temps_parcours/temps_parcours_convert.jsp";
	// private final static String TRAFFIC_URL =
	// "http://localhost/sav/traffic_cita.jsp";

	private static Logger log;
	static {
		log = Logger.getLogger(MobileDeviceServlet.class.getName());
	}

	/**
	 * Connects to the CITA website, and extracts information from the source
	 * file but do not update the statistics
	 * 
	 * @return the list of durations according to the source file
	 * @throws SourceUnavailableException
	 *         when the CITA source file is unavailable
	 * @throws DataValueException
	 *         if the data seems inappropriate.
	 * @throws DataStructureException
	 *         if the structure of the file seems inappropriate.
	 * @deprecated Please use getCitaInfo() instead.
	 */
	@Deprecated
	public static ArrayList<Duration> getCitaInfoWithoutUpdateStats() throws SourceUnavailableException, DataValueException, DataStructureException {

		log.fine("--------> entered InfoGetter.getCitaInfoWithoutUpdateStats");
		return getCitaInfo();

	}

	/**
	 * Connects to the CITA website, and extracts information from the source
	 * file
	 * 
	 * @return the list of durations according to the source file
	 * @throws SourceUnavailableException
	 *         when the CITA source file is unavailable
	 * @throws DataValueException
	 *         if the data seems inappropriate.
	 * @throws DataStructureException
	 *         if the structure of the file seems inappropriate.
	 */
	public static ArrayList<Duration> getCitaInfo() throws SourceUnavailableException, DataValueException, DataStructureException {

		log.fine("--------> entered InfoGetter.getCitaInfo");

		String fileLine;
		BufferedReader reader = null;
		ArrayList<Duration> returnList = new ArrayList<Duration>();

		try {
			// connecting to URL
			log.info("Connecting to: " + TRAFFIC_URL);
			URL url = new URL(TRAFFIC_URL);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			log.info("Connection OK! Reading page...");

			// reading page
			while (reader.ready()) {
				fileLine = reader.readLine();
				if (!"".equals(fileLine)) {
					Duration howLong = Duration.fromCita(fileLine);
					if (howLong != null) {
						returnList.add(new InstantDuration(howLong));
						log.fine("Read: " + howLong.toString());
					}
				}
			}

		} catch (ConnectException ce) {
			log.fine("Connection failed, raising a SourceUnavailableException");
			throw new SourceUnavailableException("Could not connect to CITA website");
		} catch (IOException ioe) {
			log.fine("Reading failed, raising a SourceUnavailable Exception");
			throw new SourceUnavailableException("Could not read CITA source file:\n" + ioe.getMessage());
		} finally {
			try {
				reader.close();
				log.fine("Reader closed");
			} catch (IOException ioe) {
				// should not happen
				log.info("Error while closing the reader: " + ioe.getMessage());
			} catch (NullPointerException npe) {
				// the reader may have not been initialized (URL Exception)
			}
		}

		log.fine("--- Sorting result");

		Collections.sort(returnList);

		log.fine("--------< exiting InfoGetter.getCitaInfo");

		return returnList;
	}

}
