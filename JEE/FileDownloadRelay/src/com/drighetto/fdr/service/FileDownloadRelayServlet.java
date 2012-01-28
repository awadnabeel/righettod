package com.drighetto.fdr.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.drighetto.fdr.persistence.dao.FileDaoJPAImpl;
import com.drighetto.fdr.persistence.model.FileInformations;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

/**
 * Servlet in charge of file download relaying...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class FileDownloadRelayServlet extends HttpServlet {

	/** Servlet logger */
	private static final Logger LOGGER = Logger.getLogger(FileDownloadRelayServlet.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			/* Get the parameter containing the file download URL */
			String fileUrl = req.getParameter("srcurl");
			if (fileUrl == null || "".equals(fileUrl.trim())) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "The 'srcurl' parameter is required !");
				return;
			}

			/* Connect to file Url and tranfer it's content to the Http response */
			byte[] buffer = new byte[4096];
			URLConnection connection = new URL(fileUrl).openConnection();
			connection.setReadTimeout(1000 * 60);// one minute
			InputStream is = connection.getInputStream();
			resp.setContentType(connection.getContentType());
			while (is.read(buffer) != -1) {
				resp.getOutputStream().write(buffer);
				resp.getOutputStream().flush();
			}

			/* Log downloaded file to the datastore */
			new FileDaoJPAImpl().save(new FileInformations(fileUrl.trim()));

			/* Inform a admin of this download by email and XMPP (GTalk) */
			String trace = "File [" + fileUrl + "] downloaded by " + req.getRemoteAddr() + " (" + req.getRemoteHost() + ").";
			// By Email
			// --Create a MIME Message (mail)
			Message eMsg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
			eMsg.setFrom(new InternetAddress(getServletContext().getInitParameter("admin.gmail.email")));
			eMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(getServletContext().getInitParameter("admin.gmail.email")));
			eMsg.setSubject("File downloaded");
			eMsg.setText(trace);
			// --Send it
			// Transport.send(eMsg);
			// By XMPP
			// --Create the receiver and sender buddies
			JID toJid = new JID(getServletContext().getInitParameter("admin.gmail.email"));
			JID fromJid = new JID("filedownloadrelay@appspot.com");
			// --Create the message
			com.google.appengine.api.xmpp.Message xMsg = new MessageBuilder().withRecipientJids(toJid).withBody(trace).withMessageType(MessageType.NORMAL).withFromJid(fromJid).build();
			// --Get a XMPP service provider
			XMPPService xmpp = XMPPServiceFactory.getXMPPService();
			// --Send the message only if the receiver buddy is connected
			if (xmpp.getPresence(toJid).isAvailable()) {
				// Do not care about the message delivery status...
				// xmpp.sendMessage(xMsg);
			}

			// Display a information log
			LOGGER.info(trace);
		} catch (Exception e) {
			LOGGER.fatal(e.getMessage(), e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
