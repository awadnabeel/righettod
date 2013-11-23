package com.googlecode.righettod.jee6cdi.bean;

import org.apache.commons.codec.binary.Base64;

import com.googlecode.righettod.jee6cdi.qualifier.AuditProcessing;
import com.googlecode.righettod.jee6cdi.qualifier.Base64Format;

/**
 * Implementation of a bean providing Base 64 encoding/decoding features.<br>
 * The custom CDI annotation "[at]Base64Format" is used in order to specify to CDI container<br>
 * to inject this implementation if the injection order specify the annotation "[at]Base64Format".<br>
 * The custom CDI annotation "[at]AuditProcessing" is used in order to specify to CDI container to enable <br>
 * the CDI lifecycle interceptor implementation associated to this annotation.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@Base64Format
@AuditProcessing
public class EncoderBase64Impl implements Encoder {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String s) {
		return Base64.encodeBase64String(s.getBytes());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#decode(java.lang.String)
	 */
	@Override
	public String decode(String s) {
		return new String(Base64.decodeBase64(s.getBytes()));
	}
}
