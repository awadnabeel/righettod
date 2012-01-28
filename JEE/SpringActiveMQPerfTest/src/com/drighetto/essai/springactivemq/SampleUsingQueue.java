package com.drighetto.essai.springactivemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drighetto.essai.springactivemq.processor.MyMessageProducer;

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

			long i = 0;
			while (true) {
				myMessageProducer.sendMessage();
				System.out
						.printf(
								"Msg Count :%s - FreeMemory : %s - MaxMemory : %s - TotalMemory : %s\n",
								i, Runtime.getRuntime().freeMemory(), Runtime
										.getRuntime().maxMemory(), Runtime
										.getRuntime().totalMemory());
				i++;
				Thread.sleep(20);
			}

			// Wait for let consumers work....
			// Thread.sleep(100000);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}

	}

}
