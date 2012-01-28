package com.drighetto.spring25x.jmx;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point<br>
 * Sample with the using of Java5 JMX annotations
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class MainSampleWithAnnotationsSmallConfig {

	/**
	 * Entry point
	 * 
	 * @param args
	 */
	@SuppressWarnings( { "unused", "boxing" })
	public static void main(String[] args) {
		try {
			System.out.println("******************");
			System.out.println("*  JMX SAMPLE    *");
			System.out.println("*  ANNOTATION    *");
			System.out.println("*  SMALL CONFIG  *");
			System.out.println("******************");

			// Create Spring context with the AbstractApplicationContext
			AbstractApplicationContext springCtx = new ClassPathXmlApplicationContext(
					"applicationContext-jmx-annotations-smallconfig.xml");

			// Wait in order to use the JConsole to access to MBean server...
			long waitTime = 90000000;
			System.out
					.printf(
							"Wait %sMS in order to use the JConsole to access to MBean server...",
							waitTime);
			Thread.sleep(waitTime);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
