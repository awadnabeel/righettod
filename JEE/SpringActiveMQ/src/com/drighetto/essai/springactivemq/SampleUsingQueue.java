package com.drighetto.essai.springactivemq;

import com.drighetto.essai.springactivemq.processor.MyMessageProducer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Example using QUEUE
 * 
 * @author Dominique RIGHETTO<br>
 *         29 nov. 07<br>
 */
public class SampleUsingQueue {

	/**
	 * Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         29 nov. 07<br>
	 * @param args
	 *            Command line
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		try {
			// Create spring context
			ApplicationContext appCtx = new ClassPathXmlApplicationContext(
					"applicationContext-Queue.xml");

			// Get JMS template for message sending
			MyMessageProducer myMessageProducer = (MyMessageProducer) appCtx
					.getBean("myMessageProducer");

			int i = 0;
			String text = "";
			while (i < 50000) {
				text = "Hello World " + System.currentTimeMillis();
				myMessageProducer.sendMessage(text);
				System.out.printf("Msg Count :%s - Text : %s\n", i, text);
				i++;
				Thread.sleep(1000);
			}

			// Wait for let consumers work....
			// Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
