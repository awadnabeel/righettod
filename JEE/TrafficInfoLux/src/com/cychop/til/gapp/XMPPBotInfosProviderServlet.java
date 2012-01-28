package com.cychop.til.gapp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cychop.til.duration.Duration;
import com.cychop.til.enums.Driveway;
import com.cychop.til.enums.Trip;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;

/**
 * This servlet implements a instant messaging BOT providong informations about
 * a trip using the XMPP protocol. <br>
 * According the Google App Engine documentation the message information are
 * povided using a HTTP POST request.<br>
 * <br>
 * 
 * @see "http://code.google.com/appengine/docs/java/xmpp/overview.html"
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class XMPPBotInfosProviderServlet extends HttpServlet {

	/** Class logger */
	private static final Logger LOGGER = Logger.getLogger(XMPPBotInfosProviderServlet.class.getName());

	/** Application XMPP address */
	private static final String APPLICATION_JID = "trafficinfolux@appspot.com";

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long beginTime = System.currentTimeMillis();
		boolean debugMode = false;
		
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		Message message = null;
		try {
			/* Step 1 : Get the message */
			message = xmpp.parseMessage(req);

			/* Step 2 : Check the message body */
			// The body cannot be empty
			if (StringUtil.isEmpty(message.getBody())) {
				sendResponseToSender(xmpp, message.getFromJid(), "Bad request message format ;(");
				return;
			}
			// This is the case where help about command is asked
			if (message.getBody().trim().equalsIgnoreCase("help")) {
				String msg = "_Send a message containing a list (separated by a space) of the driveways you want to get trips informations._\n\n*The available driveways are "
						+ Arrays.asList(Driveway.values()) + ".*\n\nExample of message (without \") : \"A1 A6 A3\" or \"A1\"\n\n;)";
				sendResponseToSender(xmpp, message.getFromJid(), msg);
				return;
			}

			/* Step 3 : Get the trips informations and send the response */
			// Get all trips informations from CITA
			List<Duration> tripsCitaInfos = InfoGetter.getCitaInfo();
			// Prepare response message body for the driveways specified into
			// the message
			StringBuilder xmlppReponseBody = new StringBuilder("\n");
			// Extract the list of specified driveways and parse it, building
			// the response in the same time...
			String[] driveways = message.getBody().trim().split(" ");
			for (String driveway : driveways) {
				// No processing if the driveway is not filled
				if (driveway == null) {
					continue;
				}
				// turn debug mode on if requested
				if ("D".equalsIgnoreCase(driveway)) {
					debugMode = true;
					continue;
				}
				// Check that the driveway is in the available list, if not send
				// an response and quit...
				if (!Driveway.isMember(driveway.trim().toUpperCase())) {
					sendResponseToSender(xmpp, message.getFromJid(), "The driveway '" + driveway.trim().toUpperCase() + "' is unknown. ;)\n Please type 'help' for more information on how to use this bot.");
					return;
				}
				// Get the list of trips for the current driveway
				List<Trip> trips = Trip.fromDriveway(Driveway.valueOf(driveway.trim().toUpperCase()));
				if (trips == null) {
					continue;
				}
				// Parse list of trips for the current driveway
				xmlppReponseBody.append("*").append(driveway.toUpperCase().trim()).append("* :\n");
				for (Duration duration : tripsCitaInfos) {
					if (trips.contains(duration.getTrip())) {
						xmlppReponseBody.append("'").append(duration.getTrip().getStartPoint()).append("' To '").append(duration.getTrip().getEndinPoint()).append("' : ");
						if (duration.getDuration() == -1) {
							xmlppReponseBody.append(" _FLUID_\n");
						} else {
							xmlppReponseBody.append(duration.getDuration()).append(" minute(s)\n");
						}
					}
				}
				xmlppReponseBody.append("\n\n");
			}
			
			if (debugMode) {
				xmlppReponseBody.append("Debug mode:\nResponse generated in " + (System.currentTimeMillis() - beginTime) + "ms.\n\n");
			}

			/* Step 4 : Send the response to the sender buddy */
			boolean sent = sendResponseToSender(xmpp, message.getFromJid(), xmlppReponseBody.toString());
			if (sent) {
				LOGGER.info("Message to " + message.getFromJid().getId() + " successfully sent !");
			} else {
				LOGGER.info("Message to " + message.getFromJid().getId() + " send failed !");
			}

		} catch (Exception e) {
			// We trace the error
			LOGGER.severe("Error from XMPP BOT : " + e.getMessage());
			// In case of error we return a message response indicating that an
			// error occur...
			try {
				sendResponseToSender(xmpp, message.getFromJid(), "Oups an error occured!");
			} catch (Exception e1) {
				LOGGER.severe("Error from XMPP BOT : " + e1.getMessage());
			}
		}

	}

	/**
	 * Method to send a response message to a sender
	 * 
	 * @param xmpp
	 *        The XMPP Service
	 * @param sender
	 *        The JOD sender
	 * @param messageBody
	 *        The message body
	 * @return TRUE only if the message is successfully sent
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean sendResponseToSender(XMPPService xmpp, JID sender, String messageBody) throws Exception {
		boolean sent = false;
		// Send the message only if the buddy is online
		if (xmpp.getPresence(sender).isAvailable()) {
			// Create a message container
			Message msg = new MessageBuilder().withRecipientJids(sender).withBody(messageBody.trim()).withFromJid(new JID(APPLICATION_JID)).withMessageType(MessageType.CHAT).build();
			SendResponse status = xmpp.sendMessage(msg);
			// Check delivery status
			if ((status.getStatusMap().get(sender) == SendResponse.Status.SUCCESS)) {
				sent = true;
			}
		}
		return sent;
	}

}
