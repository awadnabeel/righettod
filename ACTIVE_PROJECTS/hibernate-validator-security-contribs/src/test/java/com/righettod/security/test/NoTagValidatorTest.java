package com.righettod.security.test;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for class "NoTagValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class NoTagValidatorTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Test case for valid case
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData7("Hello World !!!");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for invalid case
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCaseKO01() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData7("Hello <World !!!");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data7", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCaseKO02() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData7(URLEncoder.encode("Hello <World !!!", Charset.defaultCharset().name()));
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data7", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCaseKO03() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData7("Hello World> !!!");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data7", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCaseKO04() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData7(URLEncoder.encode("Hello World> !!!", Charset.defaultCharset().name()));
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data7", constraintViolations.iterator().next().getPropertyPath().toString());
	}

}
