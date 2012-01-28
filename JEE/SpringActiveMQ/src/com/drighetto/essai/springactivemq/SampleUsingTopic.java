package com.drighetto.essai.springactivemq;

import com.drighetto.essai.springactivemq.processor.MyMessageProducer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Example using TOPIC
 * 
 * @author Dominique RIGHETTO<br>
 *         29 nov. 07<br>
 */
public class SampleUsingTopic {

	/**
	 * Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @param args
	 *            Command line
	 */
	public static void main(String[] args) {
		try {
			// Create spring context
			ApplicationContext appCtx = new ClassPathXmlApplicationContext(
					"applicationContext-Topic.xml");

			// Get JMS template for message sending
			MyMessageProducer myMessageProducer = (MyMessageProducer) appCtx
					.getBean("myMessageProducer");

			// Processing send/receive
			while (true) {
				myMessageProducer.sendMessage("Hello World "
						+ System.currentTimeMillis());
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
