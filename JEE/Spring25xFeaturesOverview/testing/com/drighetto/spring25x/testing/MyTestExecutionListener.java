package com.drighetto.spring25x.testing;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Simple Test Execution Listener
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class MyTestExecutionListener extends AbstractTestExecutionListener {

	/**
	 * @see org.springframework.test.context.support.AbstractTestExecutionListener#afterTestMethod(org.springframework.test.context.TestContext)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		System.out
				.printf(
						"NamedTestExecutionListener.afterTestMethod : Instance -> %s ; Method -> %s\n\n$$$$$$$$$$$$\n\n",
						testContext.getTestInstance(), testContext
								.getTestMethod().getName());

	}

	/**
	 * @see org.springframework.test.context.support.AbstractTestExecutionListener#beforeTestMethod(org.springframework.test.context.TestContext)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		System.out
				.printf(
						"NamedTestExecutionListener.beforeTestMethod : Instance -> %s ; Method -> %s\n",
						testContext.getTestInstance(), testContext
								.getTestMethod().getName());
	}

	/**
	 * @see org.springframework.test.context.support.AbstractTestExecutionListener#prepareTestInstance(org.springframework.test.context.TestContext)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		System.out.printf(
				"NamedTestExecutionListener.prepareTestInstance : %s\n",
				testContext.getTestInstance());
	}

}
