package com.drighetto.essai.springactivemq.processor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
 * @author Dominique RIGHETTO<br>
 *         29 nov. 07<br>
 */
public class MyMessageProducer {

	/** Spring JMS Template */
	private JmsTemplate jmsTemplate;

	/** Destination Name */
	private String destinationName;

	/** Data to send */
	private String data = "";

	/**
	 * Constructor
	 */
	@SuppressWarnings("boxing")
	public MyMessageProducer() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("data.txt")));
			StringBuilder sb = new StringBuilder();
			while (br.ready()) {
				sb.append(br.readLine());
			}
			br.close();
			this.data = sb.toString();
			System.out.printf("Data loaded - Size : %s\n", this.data.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to send a persistant JMS message
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 */
	public void sendMessage() {
		try {
			MessageCreator messageCreator = new MessageCreator() {
				public Message createMessage(Session session)
						throws JMSException {
					TextMessage msg = null;
					try {
						// Create message
						msg = session.createTextMessage(MyMessageProducer.this
								.getData());
						msg.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
						msg.setJMSMessageID("ID:".concat(Long.toString(System
								.currentTimeMillis())));
					} catch (Exception e) {
						throw new JMSException(e.getMessage());
					}
					return msg;
				}
			};
			this.jmsTemplate.send(this.destinationName, messageCreator);
			System.out.printf("{%s} - New text message sent to %s\n",
					DateFormat.getDateTimeInstance().format(new Date()),
					this.destinationName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(4);
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

	/**
	 * Getter for the attribute data
	 * 
	 * @return The value of data
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Setter for the attribute data
	 * 
	 * @param data
	 *            The new value
	 */
	public void setData(String data) {
		this.data = data;
	}

}
