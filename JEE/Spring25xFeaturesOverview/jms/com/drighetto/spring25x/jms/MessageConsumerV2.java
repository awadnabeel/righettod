package com.drighetto.spring25x.jms;

import java.text.DateFormat;
import java.util.Date;

/**
 * Message consumer implementation using a simple pojo...
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class MessageConsumerV2 {

	/** Message consumed counter */
	private static int MESSAGE_CONSUMED_COUNT = 0;

	/** Message consumer name */
	private String messageConsumerName;

	/**
	 * Message processing method, in our exemple the message is a TEXT Message
	 * thus the input parameter is a String <br>
	 * <b>http://static.springframework.org/spring/docs/2.5.x/reference/jms.html#jms-receiving-async-message-listener-adapter</b>
	 * 
	 * @param message
	 *            message
	 */
	@SuppressWarnings("boxing")
	public void processMessage(String message) {
		try {
			MESSAGE_CONSUMED_COUNT++;
			System.out.printf("{%s} - %s New message [%s] : [%s]\n", DateFormat
					.getDateTimeInstance().format(new Date()),
					this.messageConsumerName, MESSAGE_CONSUMED_COUNT, message);
		} catch (Exception e) {
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
