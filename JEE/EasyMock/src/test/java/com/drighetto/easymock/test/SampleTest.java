package com.drighetto.easymock.test;

import com.drighetto.easymock.Currency;
import com.drighetto.easymock.ExchangeRate;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Sample test class that will use EasyMock and JUnit to show samples of uses<br>
 * <br>
 * To get a Mock Object, we need to :<br>
 * <ul>
 * <li>1. create a Mock Object for the interface we would like to simulate,</li>
 * <li>2. record the expected behavior,</li>
 * <li>3. switch the Mock Object to replay state.</li>
 * </ul>
 * <br>
 * By default, mocks are not thread-safe. They also checks that they are indeed
 * used in only one thread. To synchronize it, call the makeThreadSafe method.
 * Note that all mocks create with a given IMocksControl will be synchronized
 * with one another. <br>
 * <br>
 * Note :<br>
 * "Les attentes (expect) indiquent quelles méthodes devraient être appelées sur les simulacres quand l'objet de test est exécuté."
 * <br>
 * "Les simulacres (mock) utilisent la vérification du comportement"</br><br>
 * 
 * @see http://easymock.org/EasyMock2_4_Documentation.html
 * 
 * @see http://bruno-orsier.developpez.com/mocks-arent-stubs/
 * 
 * @see http://www.ibm.com/developerworks/java/library/j-easymock.html
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
@SuppressWarnings("boxing")
public class SampleTest {

	/**
	 * Test that valid a case using EasyMock to simulate a exchange rate
	 * provider
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void test01() throws Exception {
		// Define a exchange rate provider with a mock
		ExchangeRate exchangeRateMock = EasyMock.createMock(ExchangeRate.class);
		// Define what is expected to valid the test (configure the mock object
		// created above)
		EasyMock.expect(exchangeRateMock.getRate("USD", "EUR")).andReturn(1.5);
		// Initialize the mock object before to use it
		EasyMock.replay(exchangeRateMock);
		// Define a reference object to valid the test
		Currency cRefObj = new Currency(3.75, "EUR");
		// Define a test object
		Currency cTestObj = new Currency(2.50, "USD");
		// Run the test using the mock as exchange rate provider
		Currency cTestResult = cTestObj.toEuros(exchangeRateMock);
		// Make a assertion to valid the test
		Assert.assertEquals(cRefObj, cTestResult);
	}

	/**
	 * Test that valid a case using EasyMock to simulate a exchange rate
	 * provider but this time check also method call order on mock (check
	 * behavior)
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void test02() throws Exception {
		// Define a exchange rate provider with a mock, we use the
		// "createStrictMock()" method to activate the method call order
		// checking
		ExchangeRate exchangeRateMock = EasyMock.createStrictMock(ExchangeRate.class);
		// Define what is expected to valid the test (configure the mock object
		// created above)
		EasyMock.expect(exchangeRateMock.getRate("USD", "EUR")).andReturn(1.5);
		// Define that the "getRate()" method must call exactly one time !
		EasyMock.expectLastCall().once();
		// Initialize the mock object before to use it
		EasyMock.replay(exchangeRateMock);
		// Define a reference object to valid the test
		Currency cRefObj = new Currency(3.75, "EUR");
		// Define a test object
		Currency cTestObj = new Currency(2.50, "USD");
		// Run the test using the mock as exchange rate provider
		Currency cTestResult = cTestObj.toEuros(exchangeRateMock);
		// Make a assertion to valid the test
		Assert.assertEquals(cRefObj, cTestResult);
		// We verify the method call order on the mock
		EasyMock.verify(exchangeRateMock);
	}

	/**
	 * Test that valid a case using EasyMock to simulate a exchange rate
	 * provider but this time : <br>
	 * 
	 * - Check also method call order on mock (check behavior)<br>
	 * - Not hard code mock parameters values, replace them by parameters values
	 * expectation constraints<br>
	 * 
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void test03() throws Exception {
		// Define a exchange rate provider with a mock, we use the
		// "createStrictMock()" method to activate the method call order
		// checking
		ExchangeRate exchangeRateMock = EasyMock.createStrictMock(ExchangeRate.class);
		// Define what is expected to valid the test (configure the mock object
		// created above), we set that method "getRate()" can be call with any
		// not null String parameter value and will always return "1.5" value
		EasyMock.expect(exchangeRateMock.getRate((String) EasyMock.notNull(), (String) EasyMock.notNull())).andReturn(1.5);
		// Define that the "getRate()" method must call exactly one time !
		EasyMock.expectLastCall().once();
		// Initialize the mock object before to use it
		EasyMock.replay(exchangeRateMock);
		// Define a reference object to valid the test
		Currency cRefObj = new Currency(3.75, "EUR");
		// Define a test object
		Currency cTestObj = new Currency(2.50, "USD");
		// Run the test using the mock as exchange rate provider
		Currency cTestResult = cTestObj.toEuros(exchangeRateMock);
		// Make a assertion to valid the test
		Assert.assertEquals(cRefObj, cTestResult);
		// We verify the method call order on the mock
		EasyMock.verify(exchangeRateMock);
	}

	/**
	 * Test that valid a case using EasyMock to simulate a exchange rate
	 * provider but this time : <br>
	 * 
	 * - Not hard code mock parameters values, replace them by parameters values
	 * expectation constraints<br>
	 * - We throw a exception in a expected invocation case
	 * 
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void test04() throws IOException {
		// Define a exchange rate provider with a mock
		ExchangeRate exchangeRateMock = EasyMock.createMock(ExchangeRate.class);
		// Define what is expected to valid the test (configure the mock object
		// created above), we set that method "getRate()" can be call with any
		// String parameter value for the second parameter and NULL for the
		// first and will always throw a IOException
		// (and thus the method result will be always NULL)
		EasyMock.expect(exchangeRateMock.getRate((String) EasyMock.isNull(), (String) EasyMock.anyObject())).andThrow(new IOException());
		// Initialize the mock object before to use it
		EasyMock.replay(exchangeRateMock);
		// Define a test object
		Currency cTestObj = new Currency(2.50, null);
		// Run the test using the mock as exchange rate provider
		Currency cTestResult = cTestObj.toEuros(exchangeRateMock);
		// Make a assertion to valid the test
		Assert.assertNull(cTestResult);
		// We verify the method call order on the mock
		EasyMock.verify(exchangeRateMock);
	}

	/**
	 * Test in order to show methods invocation flow definition in the
	 * expectation using a XML SAX content handler
	 * 
	 * @throws java.lang.Exception
	 */
	public void test05() throws Exception {
		// Define an XML source
		String doc = "<root>\n  Hello World!\n</root>";
		// Define a SAX content handler mock with method call order checking
		ContentHandler mock = EasyMock.createStrictMock(ContentHandler.class);
		// Define what is expected to valid the test (configure the mock object
		// created above)
		// ---Method "setDocumentLocator()" can be call 0 or 1 time with the
		// given parameter constraint
		mock.setDocumentLocator((Locator) EasyMock.anyObject());
		EasyMock.expectLastCall().times(0, 1);
		// ---Method "startDocument()" must be called exactly 1 time
		mock.startDocument();
		// ---Method "startElement()" must be called exactly 1 time with the
		// given parameters values
		mock.startElement(EasyMock.eq(""), EasyMock.eq("root"), EasyMock.eq("root"), (Attributes) EasyMock.anyObject());
		// ---Method "characters()" must be called at least 1 time with the
		// given parameters constraints
		mock.characters((char[]) EasyMock.anyObject(), EasyMock.anyInt(), EasyMock.anyInt());
		EasyMock.expectLastCall().atLeastOnce();
		// ---Method "endElement()" must be called exactly 1 time with the given
		// parameters values
		mock.endElement(EasyMock.eq(""), EasyMock.eq("root"), EasyMock.eq("root"));
		// ---Method "endDocument()" must be called exactly 1 time
		mock.endDocument();
		// Initialize the mock object before to use it
		EasyMock.replay(mock);

		// Run the test using the mock
		XMLReader parser = XMLReaderFactory.createXMLReader();
		parser.setContentHandler(mock);
		parser.parse(new InputSource(new ByteArrayInputStream(doc.getBytes("UTF-8"))));

		// We verify the method call order on the mock
		EasyMock.verify(mock);
	}

}
