package com.drighetto.spring25x.autowired;

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
			System.out.println("*********************");
			System.out.println("*  AUTOWIRE SAMPLE  *");
			System.out.println("*********************");
			// Create Spring context with the AbstractApplicationContext
			AbstractApplicationContext springCtx = new ClassPathXmlApplicationContext(
					"applicationContext-autowired.xml");

			// Get the processor beans
			DBProcessorV1 processor1 = (DBProcessorV1) springCtx
					.getBean("dbProcessorV1");
			DBProcessorV2 processor2 = (DBProcessorV2) springCtx
					.getBean("dbProcessorV2");

			// Call method
			System.out.println("=======DBProcessorV1=======");
			processor1.displayDBContent();
			processor1.displaySpringCtxBeanCount();
			System.out.println("=======DBProcessorV2=======");
			processor2.displayDBContent();
			processor2.displaySpringCtxBeanCount();
			processor2.displaySimplePojoCreation();

			// Add a shutdown hook for the above context because we are in a
			// non web application, in a web application a Spring context
			// shutdown hook registration is not needed because is alreay
			// included...
			springCtx.registerShutdownHook();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
