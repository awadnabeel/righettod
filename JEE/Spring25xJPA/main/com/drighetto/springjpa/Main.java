package com.drighetto.springjpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drighetto.springjpa.services.Processor;

/**
 * Project entry point
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
	@SuppressWarnings( { "unused", "boxing" })
	public static void main(String[] args) {
		try {

			// Create the Spring context
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			// Get the actions provider
			Processor processor = (Processor) applicationContext
					.getBean("myService");

			// Call actions...
			int delay = 5000;
			System.out.println("****READ****");
			processor.displayDeveloper();
			processor.displayDeveloperInfo();
			System.out
					.printf(
							"\nWaiting %sms for the demo between each CRUD actions...\n",
							delay);
			Thread.sleep(delay);
			System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$$$$\n\n\n");
			System.out.println("****CREATE****");
			processor.addDeveloper();
			System.out
					.printf(
							"\nWaiting %sms for the demo between each CRUD actions...\n",
							delay);
			Thread.sleep(delay);
			System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$$$$\n\n\n");
			System.out.println("****DELETE****");
			processor.removeDeveloper();
			System.out
					.printf(
							"\nWaiting %sms for the demo between each CRUD actions...\n",
							delay);
			Thread.sleep(delay);
			System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$$$$\n\n\n");
			System.out.println("****UPDATE****");
			processor.updateDeveloper("JPA-MODIFIED");
			System.out
					.printf(
							"\nWaiting %sms for the demo between each CRUD actions...\n",
							delay);
			Thread.sleep(delay);
			System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$$$$\n\n\n");
			System.out.println("****RESET DATA INITIAL STATE****");
			processor.updateDeveloper("RIGHETTO");
			System.out.println("=> OK !");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
