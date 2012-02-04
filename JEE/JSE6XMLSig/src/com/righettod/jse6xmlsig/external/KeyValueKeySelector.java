package com.righettod.jse6xmlsig.external;

import java.security.KeyException;
import java.security.PublicKey;
import java.util.List;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.XMLValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * 
 * Obtained from
 * http://today.java.net/pub/a/today/2006/11/21/xml-signature-with-
 * jsr-105.html#resources
 * 
 * @author Young Yang (http://www.java.net/pub/au/484)
 * 
 */
public class KeyValueKeySelector extends KeySelector {
	@SuppressWarnings("rawtypes")
	@Override
	public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
		if (keyInfo == null) {
			throw new KeySelectorException("KeyInfo object is null!");
		}
		SignatureMethod sm = (SignatureMethod) method;
		List list = keyInfo.getContent();

		for (int i = 0; i < list.size(); i++) {
			XMLStructure xmlStructure = (XMLStructure) list.get(i);
			if (xmlStructure instanceof KeyValue) {
				PublicKey pk = null;
				try {
					pk = ((KeyValue) xmlStructure).getPublicKey();
				} catch (KeyException ke) {
					throw new KeySelectorException(ke);
				}
				// check if the signature algorithm is compatible
				// with the public algorithm
				if (!algEquals(sm.getAlgorithm(), pk.getAlgorithm())) {
					throw new KeySelectorException("Signature Algorithm in not compatible with key algorithm!");
				}
				// check if the purpose is for verify
				if (purpose != KeySelector.Purpose.VERIFY) {
					throw new KeySelectorException("The public key is for validation only in XML signature!");
				}
				// check if the XMLCryptoContext is a XMLValidateContext
				if (!(context instanceof XMLValidateContext)) {
					throw new KeySelectorException("The context must be for validation!");
				}
				return new SimpleKeySelectorResult(pk);
			}
		}
		throw new KeySelectorException("No KeyValue element found in KeyInfo!");
	}

	@SuppressWarnings("static-method")
	private boolean algEquals(String sigAlg, String keyAlg) {
		if (keyAlg.equalsIgnoreCase("DSA") && sigAlg.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) {
			return true;
		} else if (keyAlg.equalsIgnoreCase("RSA") && sigAlg.equalsIgnoreCase(SignatureMethod.RSA_SHA1)) {
			return true;
		} else {
			return false;
		}
	}
}