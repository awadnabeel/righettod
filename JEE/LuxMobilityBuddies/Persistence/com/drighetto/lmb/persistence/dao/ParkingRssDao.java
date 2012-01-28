package com.drighetto.lmb.persistence.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.datanucleus.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.drighetto.lmb.persistence.model.Parking;
import com.drighetto.lmb.transversal.error.LMBException;

/**
 * DAO to access to parking informations coming from VDL.LU RSS feed. <br>
 * Feed is updated every minute, 24 hours a day.<br>
 * 
 * @see "http://www.vdl.lu/Technical+Info+Parking+Feed.html"
 * @see "http://service.vdl.lu/rss/circulation_guidageparking.php"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public abstract class ParkingRssDao {

	/** URL of the RSS feed */
	private static final String FEED_URL = "http://service.vdl.lu/rss/circulation_guidageparking.php";

	/**
	 * Method to obtains parking informations from VDL.LU RSS feed
	 * 
	 * @param lang
	 *        Locale used by the user
	 * 
	 * @return a list of parking VO containing parking informations
	 * @throws LMBException
	 */
	public static List<Parking> retrieveParkingInformations(String lang) throws LMBException {
		List<Parking> parkings = new ArrayList<Parking>();
		Element tmpElt = null;
		// Feed only supports 2 translations : FR and DE...
		String localeUsed = (lang == null || "".equals(lang.trim())) ? "fr" : lang.trim();
		try {
			/*
			 * Step 1 : Load the feed content using a DOM parser in order to use
			 * XPath to extract informations
			 */
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new URL(FEED_URL).openStream());

			/* Step 2 : Extract the parkings informations */
			// Extract the parking items node list
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList items = (NodeList) xpath.evaluate("/rss/channel/item", doc, XPathConstants.NODESET);
			// Extract parking informations
			for (int i = 0; i < items.getLength(); i++) {
				Element item = (Element) items.item(i);
				Parking parking = new Parking();
				// Parking name
				parking.setName(((Element) item.getElementsByTagName("title").item(0)).getTextContent());
				// Parking incoming address
				tmpElt = (Element) item.getElementsByTagName("vdlxml:localisation").item(0);
				tmpElt = (Element) tmpElt.getElementsByTagName("vdlxml:localisationEntree").item(0);
				parking.setAddress(tmpElt.getTextContent());
				// Parking zone in Luxemburg town
				NodeList adrs = item.getElementsByTagName("vdlxml:quartier");
				for (int j = 0; j < adrs.getLength(); j++) {
					tmpElt = (Element) adrs.item(j);
					if (localeUsed.equalsIgnoreCase(tmpElt.getAttribute("lang"))) {
						parking.setZone(tmpElt.getTextContent());
						break;
					}
				}
				// Parking total capacity
				parking.setCapacity(((Element) item.getElementsByTagName("vdlxml:total").item(0)).getTextContent());
				if (StringUtils.isEmpty(parking.getCapacity())) {
					parking.setCapacity("-1");
				}
				// Parking available place
				parking.setAvailable(((Element) item.getElementsByTagName("vdlxml:actuel").item(0)).getTextContent());
				if (StringUtils.isEmpty(parking.getAvailable())) {
					parking.setAvailable("-1");
				}
				// Parking filling status
				parking.setFillingStatus(((Element) item.getElementsByTagName("vdlxml:complet").item(0)).getTextContent());
				if (StringUtils.isEmpty(parking.getFillingStatus())) {
					parking.setFillingStatus("0");
				}
				// Parking filling trend
				parking.setFillingTrend(((Element) item.getElementsByTagName("vdlxml:tendance").item(0)).getTextContent());
				if (StringUtils.isEmpty(parking.getFillingTrend())) {
					parking.setFillingTrend("0");
				}
				// Parking open status
				parking.setOpen(((Element) item.getElementsByTagName("vdlxml:ouvert").item(0)).getTextContent());
				if (StringUtils.isEmpty(parking.getOpen())) {
					parking.setOpen("0");
				}
				// Add parking to list
				parkings.add(parking);
			}
		} catch (Exception e) {
			throw new LMBException(e);
		}

		return parkings;
	}
}
