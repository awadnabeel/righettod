package com.drighetto.spring25x.jms;

import java.text.DateFormat;
import java.util.Date;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Message producer implementation
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class MessageProducer {
	/** Spring JMS Template */
	private JmsTemplate jmsTemplate;

	/** Destination Name */
	private String destinationName;

	/**
	 * Method to send a persistant JMS message
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @param message
	 */
	public void sendMessage(final String message) {
		try {
			MessageCreator messageCreator = new MessageCreator() {
				public Message createMessage(Session session)
						throws JMSException {
					TextMessage textMessage = session
							.createTextMessage(message);
					textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
					textMessage.setJMSMessageID("ID:".concat(Long
							.toString(System.currentTimeMillis())));
					return textMessage;
				}
			};
			this.jmsTemplate.send(this.destinationName, messageCreator);
			System.out.printf("{%s} - New message sent to %s\n", DateFormat
					.getDateTimeInstance().format(new Date()),
					this.destinationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter for jmsTemplate<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return this.jmsTemplate;
	}

	/**
	 * Setter for jmsTemplate<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @param jmsTemplate
	 *            the jmsTemplate to set
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * Getter for destinationName<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return this.destinationName;
	}

	/**
	 * Setter for destinationName<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @param destinationName
	 *            the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
}
