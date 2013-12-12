package com.googlecode.righettod.cip;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.righettod.cip.vo.ClientInformation;

/**
 * Test cases for class "com.googlecode.righettod.cip.WindowsDataGrabber"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class WindowsDataGrabberTestCase {

	/** Tested instance */
	private DataGrabber instance = new WindowsDataGrabber();

	/**
	 * Test Windows registry reading.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegistry() throws Exception {
		List<ClientInformation> data = this.instance.grabFromRegistry();
		Assert.assertNotNull(data);
		Assert.assertFalse(data.isEmpty());
	}

	/**
	 * Test Windows file system reading.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileSystem() throws Exception {
		List<ClientInformation> data = this.instance.grabFromFileSystem();
		Assert.assertNotNull(data);
		Assert.assertFalse(data.isEmpty());
	}

}
