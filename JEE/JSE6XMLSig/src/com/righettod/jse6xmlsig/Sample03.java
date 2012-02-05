package com.righettod.jse6xmlsig;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.util.Collections;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
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

import com.righettod.jse6xmlsig.util.KeyStoreUtil;

/**
 * Sample exploring how sign and valide a document using "XML Sig" <br>
 * <br>
 * This sample use "Detached Signatures" XML signature type with a remote binary
 * file
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * 
 */
public class Sample03 extends BaseSample {

	/** Xml signature location */
	private static final String XML_SOURCE_SIGNED = "SignedSource03.xml";

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
		System.out.print("OK\nValidate Xml Signature file....");
		ValidateXmlSignatureFile(XML_SOURCE_SIGNED);
	}

	/**
	 * This method generate a XML signature file containg signature of our PDF
	 * source.
	 * 
	 * @throws Exception
	 */
	private static void GenerateXmlSignatureFile() throws Exception {
		// Step 1 : Create a DOM XMLSignatureFactory that will be used to
		// generate the enveloped signature.
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());

		// Step 2 : Create a Reference to a remote PDF file
		// and also specify the SHA256 digest algorithm
		Reference ref = fac.newReference("http://www.actstudent.org/plan/pdf/sample.pdf", fac.newDigestMethod(DigestMethod.SHA256, null));

		// Step 3 : Create the SignedInfo.
		SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.DSA_SHA1, null), Collections.singletonList(ref));

		// Step 4 : Create the Document that will hold the resulting
		// XMLSignature
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true); // must be set
		Document doc = dbf.newDocumentBuilder().newDocument();

		// Step 5a : Retrieve private and public key from keystore
		KeyPair kp = KeyStoreUtil.getKeyPair(KEYSTORE, KEYSTORE_PASSWORD, PRIVATE_KEY_ALIAS_PASSWORD, PRIVATE_KEY_ALIAS);

		// Step 5b: Create a KeyInfo object. This step is optional, just as
		// KeyInfo as an element in Signature element is optional.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		KeyValue kv = kif.newKeyValue(kp.getPublic());
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));

		// Step 5c : Create a DOMSignContext and specify the DSA PrivateKey and
		// location of the resulting XMLSignature's parent element.
		DOMSignContext dsc = new DOMSignContext(kp.getPrivate(), doc);

		// Step 6 : Create the XMLSignature, but don't sign it yet.
		XMLSignature signature = fac.newXMLSignature(si, ki);

		// Step 7 : Marshal, generate, and sign the enveloped signature.
		signature.sign(dsc);

		// Final step : Save XML Signature to a XML file
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(XML_SOURCE_SIGNED)));
	}

}
