package com.righettod.jse6xmlsig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.util.Collections;
import java.util.Iterator;

import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.righettod.jse6xmlsig.external.KeyValueKeySelector;
import com.righettod.jse6xmlsig.util.KeyStoreUtil;

/**
 * Sample exploring how sign and valide a XML document part using "XML Sig"
 * through JSE6 API (based on article below).
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "http://today.java.net/pub/a/today/2006/11/21/xml-signature-with-jsr-105.html"
 * 
 */
public class Sample01 extends BaseSample {

	/** Xml source location */
	private static final String XML_SOURCE = "OriginalSource01.xml";

	/** Xml signature location */
	private static final String XML_SOURCE_SIGNED = "SignedSource01.xml";

	/** Altered Xml signature location */
	private static final String XML_SOURCE_SIGNED_ALTERED = "AlteredSignedSource01.xml";

	/**
	 * Sample entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Generate XML Signature file
		System.out.print("Generate Xml Signature file....");
		GenerateXmlSignatureFile();
		System.out.print("OK\nValidate Xml Signature file (valid case)....");
		ValidateXmlSignatureFile(XML_SOURCE_SIGNED);
		System.out.print("Validate Xml Signature file (invalid case)....");
		ValidateXmlSignatureFile(XML_SOURCE_SIGNED_ALTERED);
	}

	/**
	 * This method generate a XML signature file containg signature of our XML
	 * source.
	 * 
	 * @throws Exception
	 */
	private static void GenerateXmlSignatureFile() throws Exception {
		// Step 0 : Create XML document builder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);

		// Step 1: Load an XMLSignatureFactory instance. This factory class will
		// be responsible for constructing almost all the major objects we need
		// in working with XML Signature in JSR-105 APIs, except those related
		// to KeyInfo.
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());

		// Step 2: Decide on a digest method and create the reference object. We
		// use the XMLSignatureFactory instance created in the first step to
		// create both the DigestMethod and Reference objects.
		Reference ref = fac.newReference("#invoice", fac.newDigestMethod(DigestMethod.SHA256, null));

		// Step 3: Load invoice.xml and wrap it in an XMLObject object. Not all
		// signature generation processes require this step. XMLObject in
		// JSR-105 models the optional Object element we briefly discussed
		// before.
		Document XML = dbf.newDocumentBuilder().parse(new File(XML_SOURCE));
		Node invoice = XML.getDocumentElement();
		XMLStructure content = new DOMStructure(invoice);
		XMLObject obj = fac.newXMLObject(Collections.singletonList(content), "invoice", null, null);

		// Step 4: Create the SignedInfo object.
		SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.DSA_SHA1, null), Collections.singletonList(ref));

		// Step 5 : Retrieve private and public key from keystore
		KeyPair kp = KeyStoreUtil.getKeyPair(KEYSTORE, KEYSTORE_PASSWORD, PRIVATE_KEY_ALIAS_PASSWORD, PRIVATE_KEY_ALIAS);

		// Step 6: Create a KeyInfo object. This step is optional, just as
		// KeyInfo as an element in Signature element is optional.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		KeyValue kv = kif.newKeyValue(kp.getPublic());
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));

		// Step 7: Create an XMLSignature object. In JSR-105, the XMLSignature
		// interface models the Signature element of the W3C recommendation.
		XMLSignature signature = fac.newXMLSignature(si, ki, Collections.singletonList(obj), null, null);

		// Step 8: Instantiate a DOMSignContext object, and register the private
		// key with it. The XMLSignContext interface (which DOMSignContext
		// implements) contains context information for generating XML
		// signatures.
		Document doc = dbf.newDocumentBuilder().newDocument();
		DOMSignContext dsc = new DOMSignContext(kp.getPrivate(), doc);

		// Step 9: Sign. The sign() operation in the XMLSignature interface
		// signs the XMLSignature. Under the surface, the method carries out
		// several actions, including computing the digest values for all the
		// References based on the corresponding digest methods, and calculating
		// the signature value based on the signature method and the private
		// key. The signature value is captured by the embedded SignatureValue
		// class in the XMLSignature instance, and calling getSignatureValue()
		// of the XMLSignature instance will return the SignatureValue object
		// populated with the resulting value.
		signature.sign(dsc);

		// Final step : Save XML Signature to a XML file
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(XML_SOURCE_SIGNED)));
	}

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
	private static void ValidateXmlSignatureFile(String xmlLocation) throws Exception {
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
