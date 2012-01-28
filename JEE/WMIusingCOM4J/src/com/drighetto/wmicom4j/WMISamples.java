package com.drighetto.wmicom4j;

import com.drighetto.wmicom4j.wmijtd.ClassFactory;
import com.drighetto.wmicom4j.wmijtd.ISWbemLocator;
import com.drighetto.wmicom4j.wmijtd.ISWbemObject;
import com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet;
import com.drighetto.wmicom4j.wmijtd.ISWbemServices;
import com4j.Com4jObject;

/**
 * Samples to show access WMI using COM4J Java Type Definition generated (See
 * README.TXT file for more informations)
 * 
 * <p>
 * Links extracted from the COM4J samples class for WMI
 * <ul>
 * <li>
 * http://msdn.microsoft.com/library/default.asp?url=/library/en-us/dnanchor/
 * html/anch_wmi.asp</li>
 * <li>
 * http://msdn.microsoft.com/library/default.asp?url=/library/en-us/wmisdk/wmi
 * /scripting_api_objects.asp</li>
 * <li>
 * http://www.microsoft.com/downloads/details.aspx?FamilyId=6430F853-1120-48D
 * B-8CC5-F2ABDC3ED314&displaylang=en</li>
 * <li>WQL : http://msdn.microsoft.com/en-us/library/aa394606%28VS.85%29.aspx</li>
 * <li>DCOM Security :
 * http://msdn.microsoft.com/en-us/library/aa393266%28VS.85%29.aspx</li>
 * </ul>
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class WMISamples {

	/**
	 * Samples
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		ISWbemServices wbemServices = null;
		try {
			/* Connecting to WMI repository on current machine */
			System.out.print("=> Connecting to WMI repository on current machine...");
			ISWbemLocator wbemLocator = ClassFactory.createSWbemLocator();
			// Connect to current machine
			wbemServices = wbemLocator.connectServer("localhost", "Root\\CIMv2", "", "", "", "", 0, null);
			// Connect to remote machine
			// wbemServices = wbemLocator.connectServer("192.168.0.3",
			// "Root\\CIMv2", "Dominique", "Dominique", "", "", 0, null);
			System.out.println("Connected !");

			/* Perform a query : What logical disks do we have ? */
			System.out.println("=> Perform a query : What logical disks do we have ?");
			// Select informations
			ISWbemObjectSet result = wbemServices.execQuery("Select * from Win32_LogicalDisk", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

			/* Perform a query : What USB drives do we have ? */
			System.out.println("=> Perform a query : What USB drives do we have ?");
			// Select informations
			result = wbemServices.execQuery("Select * from Win32_USBDevice", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

			/* Perform a query : What is the datetime ? */
			System.out.println("=> Perform a query : What is the datetime ?");
			// Select informations
			result = wbemServices.execQuery("Select * from Win32_LocalTime", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

			/* Perform a query : How many logon session do we have ? */
			System.out.println("=> Perform a query : How many logon session do we have ?");
			// Select informations
			result = wbemServices.execQuery("Select * from Win32_LogonSession", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

			/* Perform a query : How many share do we have ? */
			System.out.println("=> Perform a query : How many share do we have ?");
			// Select informations
			result = wbemServices.execQuery("Select * from Win32_Share", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

			/* Perform a query : How many network connection do we have ? */
			System.out.println("=> Perform a query : How many network connection do we have ?");
			// Select informations
			result = wbemServices.execQuery("Select * from Win32_NetworkConnection", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

			/* Perform a query : How many account SID do we have ? */
			System.out.println("=> Perform a query : How many account SID do we have ?");
			// Select informations
			result = wbemServices.execQuery("Select * from Win32_AccountSID", "WQL", 16, null);
			// Display query results
			for (Com4jObject obj : result) {
				ISWbemObject wo = obj.queryInterface(ISWbemObject.class);
				System.out.println(wo.getObjectText_(0));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("<= End of samples");
			// Release object
			if (wbemServices != null) {
				wbemServices.dispose();
			}
		}

	}

}
