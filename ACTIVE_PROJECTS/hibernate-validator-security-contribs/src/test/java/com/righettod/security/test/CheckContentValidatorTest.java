package com.righettod.security.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for class "CheckContentValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class CheckContentValidatorTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Test case for whitelist validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck01CaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData1("aaaBBBccc");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for whitelist validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck01CaseKO() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData1("aaaB1BBccc");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data1", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for whitelist validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck02CaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData2("aaa123CCC");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for whitelist validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck02CaseKO() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData2("aaa123CCC,$%");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data2", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for character continuous repetition limit validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCharacterRepetitionLimitCheck01CaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData3("aaa(B-B..Bccc");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for character continuous repetition limit validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCharacterRepetitionLimitCheck01CaseKO() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData3("aaa(B---B..B'ccc");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data3", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for character continuous repetition limit + whitelist validation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultiCheck01CaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData4("aaa(B-B..Bccc");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for whitelist validation using locale
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck03CaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData5("12345");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for whitelist validation using locale
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck03CaseKO() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData5("12AAA345");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data5", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for whitelist validation using wrong locale. <br>
	 * In this test, if processing is ok, the whitelist used must be the "default identifier" <br>
	 * coming from "default bundle" in which specified character are allowed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck04CaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData6("-'!?[]().");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for whitelist validation using wrong locale. <br>
	 * In this test, if processing is ok, the whitelist used must be the "default identifier" <br>
	 * coming from "default bundle" in which specified character is not allowed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWhitelistCheck04CaseKO() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData6("******");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data6", constraintViolations.iterator().next().getPropertyPath().toString());
	}

}
