package com.drighetto.junit.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.experimental.max.MaxCore;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.File;
import java.util.Map;

/**
 * Test class to show new Junit 4.6 new experimental feature
 * 
 * @see http://sourceforge.net/project/shownotes.php?release_id=675664&group_id=15278
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
@SuppressWarnings("boxing")
public class TestNewExperimentalFeatures {

	/**
	 * Show MaxCore feature : `MaxCore` remembers the results of previous test
	 * runs in order to run new tests out of order
	 */
	@Test
	public void showMaxCore() throws Exception {
		// Define a file to store tests run history
		File maxCoreFile = new File("junit-maxcore-history.max");
		// Load previous test runs informations
		MaxCore maxCoreTestPrevisousRun = MaxCore.storedLocally(maxCoreFile);
		// Display previous tests run informations
		System.out.println("**** PREVIOUS RUN INFOS ****");
		for (Map.Entry<String, Long> testData : maxCoreTestPrevisousRun.fHistory.fDurations.entrySet()) {
			System.out.printf("=> Test '%s' - Duration '%s' ms\n", testData.getKey(), testData.getValue());
		}
		// Run tests using the MaxCore in order to obtains tests run history
		// informations saved to the file
		System.out.println("**** NEW RUN ****");
		Result result = maxCoreTestPrevisousRun.run(TestProcessingBean.class);
		System.out.println("**** NEW RUN INFOS ****");
		System.out.printf("%s Test executed in %s ms\n", result.getRunCount(), result.getRunTime());
		System.out.printf("%s Test ignored\n", result.getIgnoreCount());
		System.out.printf("%s Test failed\n", result.getFailureCount());
		maxCoreTestPrevisousRun.fHistory.save();
	}

	/**
	 * Show scheduling strategies feature : This feature allows you to specify a
	 * model of the `Computer` that runs your tests. Currently, the only
	 * built-in Computers are the default, serial runner, and two runners
	 * provided in the `ParallelRunner` class:<br>
	 * `ParallelRunner.classes()`, which runs classes in parallel, and<br>
	 * `ParallelRunner.methods()`, which runs classes and methods in parallel.<br>
	 */
	@Test
	public void showSchedulingStrategies() {
		//Run the tests suite "TestProcessingBean" with methods in parallel
		long start = System.currentTimeMillis();
		Result result= JUnitCore.runClasses(ParallelComputer.methods(),TestProcessingBean.class);
		long delayInParallel = System.currentTimeMillis() - start;
		//Validate that execution has not encountered any error
		Assert.assertTrue(result.wasSuccessful());
		//Run the tests suite "TestProcessingBean" normally without parallel
		start = System.currentTimeMillis();
		result= JUnitCore.runClasses(TestProcessingBean.class);
		long delayNoParallel = System.currentTimeMillis() - start;		
		//Validate that execution has not encountered any error
		Assert.assertTrue(result.wasSuccessful());
		//Display delay
		System.out.printf("Delay with parallel    : %s ms\n",delayInParallel);
		System.out.printf("Delay without parallel : %s ms\n",delayNoParallel);
	}

}
