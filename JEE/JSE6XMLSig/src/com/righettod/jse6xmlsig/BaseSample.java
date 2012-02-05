package com.righettod.jse6xmlsig;

import java.io.FileInputStream;
import java.util.Iterator;

import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.righettod.jse6xmlsig.external.KeyValueKeySelector;

/**
 * Class containing common sample member
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class BaseSample {
	/** Keystore location */
	protected static final String KEYSTORE = "MyKeyStore.jks";

	/** Keystore access password */
	protected static final String KEYSTORE_PASSWORD = "sp1234";

	/** PK alias in Keystore */
	protected static final String PRIVATE_KEY_ALIAS = "myalias";

	/** PK alias access password */
	protected static final String PRIVATE_KEY_ALIAS_PASSWORD = "kp1234";

	/**
	 * This method validate a XML signature file containg signature of our XML
	 * source.
	 * 
	 * @param xmlLocation
	 *        Location of the XML to validate
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected static void ValidateXmlSignatureFile(String xmlLocation) throws Exception {
		// Step 0 : Create XML document builder and load XML containing XML
		// signature to validate
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(xmlLocation));

		// Step 1: Load an XMLSignatureFactory instance. This factory class will
		// be responsible for constructing almost all the major objects we need
		// in working with XML Signature in JSR-105 APIs, except those related
		// to KeyInfo.
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());

		// Step 3 : Find all Xml Signature element into the provided XML
		// document (here for sample use only the first)
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element!");
		}

		// Step 4: Create a DOMValidateContext instance (extract public key from
		// the "KeyInfo" bloc using overrided KeySelector impl.)
		DOMValidateContext valContext = new DOMValidateContext(new KeyValueKeySelector(), nl.item(0));

		// Step 5: Unmarshal the Signature node into an XMLSiganture object.
		XMLSignature signature = fac.unmarshalXMLSignature(valContext);

		// Step 6 : Validate signature
		boolean isValid = signature.validate(valContext);
		if (isValid) {
			System.out.println("OK");
		} else {
			System.out.println("KO (Signature failed core validation)");
			boolean sv = signature.getSignatureValue().validate(valContext);
			System.out.println("----> Signature validation status: " + sv);
			// Check the validation status of each Reference
			Iterator i = signature.getSignedInfo().getReferences().iterator();
			for (int j = 0; i.hasNext(); j++) {
				boolean refValid = ((Reference) i.next()).validate(valContext);
				System.out.println("----> Reference (" + j + ") validation status: " + refValid);
			}
		}
	}
}
