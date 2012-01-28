package com.drighetto.spring25x.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Base test class defining generic members used in test subclasses including
 * the Spring TestContext
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 * <b>The version 4.4 at least of JUNIT is required to use the annotation
 * "RunWith" with the class
 * "org.springframework.test.context.junit4.SpringJUnit4ClassRunner"</b>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "/applicationContext-testing-base.xml"
// in the root of the classpath and TestContext is not inherited from superclass
// (no superclass here)
@ContextConfiguration(locations = { "/applicationContext-testing-base.xml" }, inheritLocations = false)
@TestExecutionListeners(inheritListeners = true, value = { DependencyInjectionTestExecutionListener.class })
public class BaseTestBean {

	/** Instance of tested bean */
	@Autowired(required = true)
	MyTestedBean myTestedBean = null;

	/**
	 * Test method
	 */
	@Test
	// Test is activated only if the "java.vendor" JRE properties is equals to
	// "Sun Microsystems Inc."
	@IfProfileValue(name = "java.vendor", value = "Sun Microsystems Inc.")
	public void myTestMethodOne() {
		System.out.printf("%s : %s\n", this.myTestedBean.toString(),
				this.myTestedBean.sayHello("Call from myTestMethodOne()"));
	}

	/**
	 * Setter for the attribute myTestedBean
	 * 
	 * @param myTestedBean
	 *            The new value
	 */
	public void setMyTestedBean(MyTestedBean myTestedBean) {
		this.myTestedBean = myTestedBean;
	}

}
