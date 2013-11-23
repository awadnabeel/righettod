package com.googlecode.righettod.jee6cdi;

import javax.inject.Inject;

import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.righettod.jee6cdi.bean.Encoder;
import com.googlecode.righettod.jee6cdi.bean.EncoderMockImpl;
import com.googlecode.righettod.jee6cdi.bean.EncoderPlainTextImpl;

/**
 * Test cases for implementation of Encoder.<br>
 * Runs the tests with CDI-Unit.<br>
 * <b>The bad thing, at time the sample is wote, is that using CDI-Unit or JBoss Arquillian <br>
 * you must declare, into your unit test case configuration, all the implementation classes that will <br>
 * be injected into your test cases.<br>
 * Here is achieved using CDI-Unit annotation "[at]AdditionalClasses" in order declare the Encoder implementation <br>
 * that is annoted with CDI "[at]Default".</b><br>
 * We activate the Alternative CDI feature in order to instruct CDI container to inject mocked Encoder <br>
 * implementation that is annoted using CDI annotation "[at]Alternative".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://jglue.org/cdi-unit-user-guide/"
 * @see "http://docs.oracle.com/javaee/6/tutorial/doc/gjsdf.html"
 * 
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(EncoderPlainTextImpl.class)
@ActivatedAlternatives(EncoderMockImpl.class)
public class EncoderTestCase {

	/** Tested instance */
	private @Inject
	Encoder instance;

	/**
	 * Test case
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCase() throws Exception {
		String s = "Hello";
		String enc = this.instance.encode(s);
		String dec = this.instance.decode(enc);
		Assert.assertEquals(s, dec);
	}

}
