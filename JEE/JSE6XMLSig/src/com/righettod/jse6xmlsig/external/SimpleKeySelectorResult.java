package com.righettod.jse6xmlsig.external;

import java.security.Key;
import java.security.PublicKey;

import javax.xml.crypto.KeySelectorResult;

/**
 * 
 * Obtained from
 * http://today.java.net/pub/a/today/2006/11/21/xml-signature-with-
 * jsr-105.html#resources
 * 
 * @author Young Yang (http://www.java.net/pub/au/484)
 * 
 */
public class SimpleKeySelectorResult implements KeySelectorResult {
	private PublicKey pk;

	SimpleKeySelectorResult(PublicKey pk) {
		this.pk = pk;
	}

	@Override
	public Key getKey() {
		return this.pk;
	}
}