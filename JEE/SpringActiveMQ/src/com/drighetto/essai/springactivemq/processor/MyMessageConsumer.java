package com.drighetto.essai.springactivemq.processor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import java.text.DateFormat;
import java.util.Date;

/**
 * Message consumer implementation
 * 
 * @author Dominique RIGHETTO<br>
 *         29 nov. 07<br>
 */
public class MyMessageConsumer implements MessageListener {

	/** Message consumed counter */
	private static int MESSAGE_CONSUMED_COUNT = 0;

	/** Message consumer name */
	private String messageConsumerName;

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 * 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("boxing")
	public void onMessage(Message message) {
		try {
			MESSAGE_CONSUMED_COUNT++;
			System.out.printf("{%s} - %s New message [%s] from %s : [%s]\n",
					DateFormat.getDateTimeInstance().format(new Date()),
					this.messageConsumerName, MESSAGE_CONSUMED_COUNT, message
							.getJMSDestination().toString(),
					((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Getter for messageConsumerName<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @return the messageConsumerName
	 */
	public String getMessageConsumerName() {
		return this.messageConsumerName;
	}

	/**
	 * Setter for messageConsumerName<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @param messageConsumerName
	 *            the messageConsumerName to set
	 */
	public void setMessageConsumerName(String messageConsumerName) {
		this.messageConsumerName = messageConsumerName;
	}

}
