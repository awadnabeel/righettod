package com.drighetto.spring25x.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Simple class defining a test case
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 * <b>The version 4.4 at least of JUNIT is required to use the annotation
 * "RunWith" with the class
 * "org.springframework.test.context.junit4.SpringJUnit4ClassRunner"</b>
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "/applicationContext-testing.xml"
// in the root of the classpath and TestContext is inherited from superclass
// (BaseTestBean class here)
@ContextConfiguration(locations = { "/applicationContext-testing.xml" }, inheritLocations = true)
@TestExecutionListeners(inheritListeners = true, value = {
		DependencyInjectionTestExecutionListener.class,
		MyTestExecutionListener.class })
public class MyTestBean extends BaseTestBean {

	/**
	 * Test method
	 * 
	 * <b>Using Spring's ExpectedException annotation in conjunction with JUnit
	 * 4's Test(expected=...) configuration would lead to an unresolvable
	 * conflict. Developers must therefore choose one or the other when
	 * integrating with JUnit 4, in which case it is generally preferable to use
	 * the explicit JUnit 4 configuration.</b>
	 */
	@Test
	@ExpectedException(IllegalArgumentException.class)
	// Indicates that the annotated test method must be executed repeatedly. The
	// number of times that the test method is to be executed is specified in
	// the annotation.
	// Note that the scope of execution to be repeated includes execution of the
	// test method itself as well as any set up or tear down of the test
	// fixture.
	@Repeat(3)
	public void myTestMethodThree() {
		System.out.printf("%s : %s\n", this.myTestedBean.toString(),
				this.myTestedBean.sayHello("23"));
	}

	/**
	 * Test method
	 */
	@Test
	// The presence of the DirtiesContext annotation on a test method indicates
	// that the underlying Spring container is 'dirtied' during the execution of
	// of the test method, and thus must be rebuilt after the test method
	// finishes execution (regardless of whether the test passed or not).
	@DirtiesContext
	@Repeat(2)
	public void myTestMethodTwo() {
		System.out.printf("%s : %s\n", this.myTestedBean.toString(),
				this.myTestedBean.sayHello("Call from myTestMethodTwo()"));
	}

}
