package com.drighetto.junit.test;

import com.drighetto.junit.ProcessingBean;

import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Junit class to test "ProcessingBean" bean
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
@SuppressWarnings("unchecked")
public class TestProcessingBean {

	/** Instance of tested class */
	private ProcessingBean bean = null;

	/**
	 * Test case (class) initialization method, run once at test case execution
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@BeforeClass
	public static void testCaseInitialization() {
		System.out.println("Test case initialization...");		
	}

	/**
	 * Test case (class) finalization method, run once after test case execution
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@AfterClass
	public static void testCaseFinalization() {
		System.out.println("Test case finalization...");
	}

	/**
	 * Initialization method, before each test execution (set up)...
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@Before
	public void initialization() {
		System.out.println("Initialization...");
		this.bean = new ProcessingBean("Hello World !");
	}

	/**
	 * Finalization method, after each test execution (tear down)...
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@After
	public void finalization() {
		System.out.println("Finalization...");
		this.bean = null;
	}

	/**
	 * First test method for <b>ProcessingBean.displayMsg()</b> bean method
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@Test
	public void displayMsgOne() {
		System.out.println("Test method 1 : displayMsgOne()...");
		this.bean.displayMsg();
	}

	/**
	 * Second test method for <b>ProcessingBean.displayMsg()</b> bean method
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@Test
	public void displayMsgTwo() {
		System.out.println("Test method 2 : displayMsgTwo()...");
		this.bean.displayMsg();
	}

	/**
	 * Third test method for <b>ProcessingBean.displayMsg()</b> bean method<br>
	 * Explicity ingored with use of
	 * 
	 * @Ignore annotation !
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
	@Ignore
	public void displayMsgThree() {
		System.out.println("Test method 3 : displayMsgThree()...");
		this.bean.displayMsg();
	}

	/**
	 * Fourth test method for <b>ProcessingBean.displayMsg()</b> bean method<br>
	 * Fail explicity to show timeout functionalities !<br>
	 * <b>Execution timeout is set to 2 seconds</b>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         28 avr. 07<br>
	 */
//	@Test(timeout = 2000)
//	public void displayMsgFour() {
//		while (true) {/**/
//		}
//	}

	/**
	 * Test to show assertion using "org.junit.Assert.assertThat()" method<br>
	 * 
	 * @see http 
	 *      ://junit.org/junit/javadoc/4.5/org/hamcrest/core/package-frame.html
	 */
	@Test
	public void showAssertThat() {
		String localVar = null;
		// Assert True if "this.bean" object is a instance of "ProcessingBean"
		// class
		Assert.assertThat(this.bean, IsInstanceOf.instanceOf(ProcessingBean.class));
		// Assert True if at least one assert included in the "AnyOf" operator
		// is True
		Assert.assertThat(this.bean.getMessage(), AnyOf.anyOf(IsEqual.equalTo("Hello World !"), IsNot.not(IsEqual.equalTo("Hello World !"))));
		// Assert True if all assert included in the "AllOf" operator are True
		Assert.assertThat(this.bean.getMessage(), AllOf.allOf(IsEqual.equalTo("Hello World !"), Is.is("Hello World !")));
		// Assert True if "this.bean" is the same instance than
		// "this.bean.getInstance()"
		Assert.assertThat(this.bean, IsSame.sameInstance(this.bean.getInstance()));
		// Assert True if "localVar" is null
		Assert.assertThat(localVar, IsNull.nullValue());
		// Assert True if "localVar" is not null
		localVar = "value";
		Assert.assertThat(localVar, IsNull.notNullValue());
	}

	/**
	 * Test to show assumptions using "org.junit.Assume.assumeThat()" method
	 * 
	 */
	@Test
	public void showAssumptionsUsingAssumeThat() {
		// Execute this test only if the assumption below is verified, if the
		// assumption is not verified the test being marked as passing,
		// regardless of what the code below the assumption may assert (the test code below assumption is not executed).
		// Synthax is the same than the "org.junit.Assert.assertThat()" method
		Assume.assumeThat(System.getProperty("java.vm.vendor"), Is.is("Sun Microsystems Inc."));
		System.out.println("showAssumptionsUsingAssumeThat() : Assumption verified then test code is executed !");
	}
	
	/**
	 * Test to show assumptions using "org.junit.Assume.assumeTrue()" method
	 * 
	 */
	@Test
	public void showAssumptionsUsingAssumeTrue() {
		// Execute this test only if the assumption below is verified, if the
		// assumption is not verified the test being marked as passing,
		// regardless of what the code below the assumption may assert (the test code below assumption is not executed)
		Assume.assumeTrue("Sun Microsystems Inc.".equals(System.getProperty("java.vm.vendor")));
		System.out.println("showAssumptionsUsingAssumeTrue() : Assumption verified then test code is executed !");
	}
	
	/**
	 * Test to show assumptions using "org.junit.Assume.assumeNotNull()" method
	 * 
	 */
	@Test
	public void showAssumptionsUsingAssumeNotNull() {
		// Execute this test only if the assumption below is verified, if the
		// assumption is not verified the test being marked as passing,
		// regardless of what the code below the assumption may assert (the test code below assumption is not executed)
		Assume.assumeNotNull(this.bean);
		System.out.println("showAssumptionsUsingAssumeNotNull() : Assumption verified then test code is executed !");
	}
	
	/**
	 * Test to show assumptions using "org.junit.Assume.assumeNotNull()" method
	 * 
	 */
	@Test
	public void showAssumptionsUsingAssumeNoException() {
		// Execute this test only if the assumption below is verified, if the
		// assumption is not verified the test being marked as passing,
		// regardless of what the code below the assumption may assert (the test code below assumption is not executed)
		IllegalArgumentException exception = null;
		try{
			this.bean.getMessage();
			//Active this code to fail the assumption...
			//throw new IllegalArgumentException("test");
		}catch(IllegalArgumentException iae){
			exception = iae;
		}
		Assume.assumeNoException(exception);
		System.out.println("showAssumptionsUsingAssumeNoException() : Assumption verified then test code is executed !");
	}	

}
