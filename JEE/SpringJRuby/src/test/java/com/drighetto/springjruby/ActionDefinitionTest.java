package com.drighetto.springjruby;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test class for the interface "ActionDefinition" implementation
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class ActionDefinitionTest {

	/** Spring application context */
	private static AbstractApplicationContext applicationContext = null;

	/**
	 * Method to initialize the test suite
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void initialization() throws Exception {

		System.out.print("\nInitialize the Spring application context...");
		// Initialize the Spring application context
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		System.out.println("OK");

	}

	/**
	 * Method to finalize the test suite
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void finalization() throws Exception {

		System.out.print("\nClose the Spring application context...");
		// Close the Spring application context
		applicationContext.close();
		System.out.println("OK");

	}

	/**
	 * Test method
	 * 
	 * @throws Exception
	 */
	@Test
	public void actionDefinitionTesterWithExternalScript() throws Exception {

		// Check and get the bean from the Spring context
		System.out.print("\nCheck and get the bean from the Spring context...");
		assertNotNull(applicationContext);
		ActionDefinition actionDefinition = (ActionDefinition) applicationContext
				.getBean("actionDefinition");
		System.out.println("OK");

		// Test basic values
		System.out.print("Test basic values...");
		assertNotNull(actionDefinition);
		assertEquals("No method called", actionDefinition
				.obtainLastMethodCalled());
		System.out.println("OK");

		// Launch a first processing and check values
		System.out.print("Launch a first processing and check values...");
		File filename = new File("TestFile.txt");
		String content = "Test Content !!!";
		actionDefinition.writeContent(content, filename.getAbsolutePath());
		String tmp = "writeContent() on " + filename.getAbsolutePath();
		assertEquals(tmp, actionDefinition.obtainLastMethodCalled());
		System.out.println("OK");

		// Launch a second processing and check values
		System.out.print("Launch a second processing and check values...");
		content = actionDefinition.readContent(filename.getAbsolutePath());
		// Note : We add a '\n' a the end of the text because JRuby seem to add
		// a new line after each line inserted in a file
		assertEquals("Test Content !!!\n", content);
		tmp = "readContent() on " + filename.getAbsolutePath();
		assertEquals(tmp, actionDefinition.obtainLastMethodCalled());
		System.out.println("OK");

		// Delete file
		System.out.print("Temporary test file deleted...");
		System.out.println(filename.delete());

	}

	/**
	 * Test method
	 * 
	 * @throws Exception
	 */
	@Test
	public void actionDefinitionTesterWithInternalScript() throws Exception {

		// Check and get the bean from the Spring context
		System.out.print("\nCheck and get the bean from the Spring context...");
		assertNotNull(applicationContext);
		ActionDefinition actionDefinition = (ActionDefinition) applicationContext
				.getBean("actionDefinitionInline");
		System.out.println("OK");

		// Test basic values
		System.out.print("Test basic values...");
		assertNotNull(actionDefinition);
		assertEquals("No method called", actionDefinition
				.obtainLastMethodCalled());
		System.out.println("OK");

		// Launch a first processing and check values
		System.out.print("Launch a first processing and check values...");
		File filename = new File("TestFile.txt");
		String content = "Test Content !!!";
		actionDefinition.writeContent(content, filename.getAbsolutePath());
		String tmp = "writeContent() on " + filename.getAbsolutePath();
		assertEquals(tmp, actionDefinition.obtainLastMethodCalled());
		System.out.println("OK");

		// Launch a second processing and check values
		System.out.print("Launch a second processing and check values...");
		content = actionDefinition.readContent(filename.getAbsolutePath());
		// Note : We add a '\n' a the end of the text because JRuby seem to add
		// a new line after each line inserted in a file
		assertEquals("Test Content !!!\n", content);
		tmp = "readContent() on " + filename.getAbsolutePath();
		assertEquals(tmp, actionDefinition.obtainLastMethodCalled());
		System.out.println("OK");

		// Delete file
		System.out.print("Temporary test file deleted...");
		System.out.println(filename.delete());

	}

}
