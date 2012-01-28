package com.drighetto.wmicom4j;

import com.drighetto.wmicom4j.wmijtd.ClassFactory;
import com.drighetto.wmicom4j.wmijtd.ISWbemLocator;
import com.drighetto.wmicom4j.wmijtd.ISWbemObject;
import com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet;
import com.drighetto.wmicom4j.wmijtd.ISWbemServices;
import com4j.Com4jObject;

import org.junit.Test;

/**
 * Junit test to valid COM4J API stability
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class StabilityTest {

	/**
	 * This have to goal to valid that a application using COM4J JTD generated
	 * classes can run during a long period without throwing a OutOfMemory
	 * exception
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	@SuppressWarnings("boxing")
	public void stabilityTest() throws Exception {
		int iterationCount = 100000;
		//Perform several iteration
		for(int i = 0 ; i < iterationCount ; i++){
			System.out.printf("==> Iteration [%s / %s]\n",i,iterationCount);
			System.out.print("=> Connecting to WMI repository on current machine...");
			ISWbemLocator wbemLocator = ClassFactory.createSWbemLocator();
			/* Step 1 : Connect to current machine*/
			ISWbemServices wbemServices = wbemLocator.connectServer("localhost", "Root\\CIMv2", "", "", "", "", 0, null);
			System.out.println("Connected !");
			/*Perform a query*/
			// Select informations
			ISWbemObjectSet result = wbemServices.execQuery("Select * from Win32_USBDevice", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				wo.getObjectText_(0);
			}
			System.out.println("=> Test OK !");
			/*Release object*/
			wbemServices.dispose();		
			System.out.println("=> Object released !");
		}
	}

}
