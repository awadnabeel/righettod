package com.drighetto.spring25x.jms;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class Main {

	/**
	 * Entry point
	 * 
	 * @param args
	 *            Command line
	 */
	public static void main(String[] args) {
		try {
			System.out.println("**************************");
			System.out.println("* JMS SAMPLE ON ACTIVEMQ *");
			System.out.println("**************************");

			// Create Spring context with the AbstractApplicationContext
			AbstractApplicationContext springCtx = new ClassPathXmlApplicationContext(
					"applicationContext-jms.xml");

			// Get a message producer
			MessageProducer messageProducer = (MessageProducer) springCtx
					.getBean("myMessageProducer");

			// Launch a Production/Consumption infinite cycle....
			while (true) {
				// Produce a message every 1 second
				messageProducer.sendMessage("Hello Dom");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
