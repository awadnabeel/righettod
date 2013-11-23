package com.googlecode.righettod.jee6cdi.bean;

import javax.enterprise.inject.Default;

import org.apache.commons.codec.DecoderException;

import com.googlecode.righettod.jee6cdi.qualifier.AuditProcessing;

/**
 * Implementation of a bean providing no encoding/decoding features.<br>
 * The CDI annotation "[at]Default" is used in order to specify to CDI container that this implementation<br>
 * must be injected if no qualifier is specified on injection order.<br>
 * The custom CDI annotation "[at]AuditProcessing" is used in order to specify to CDI container to enable <br>
 * the CDI lifecycle interceptor implementation associated to this annotation.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/6/tutorial/doc/gjbck.html"
 * 
 */
@Default
@AuditProcessing
public class EncoderPlainTextImpl implements Encoder {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String s) {
		return s;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws DecoderException
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#decode(java.lang.String)
	 */
	@Override
	public String decode(String s) throws DecoderException {
		return s;
	}
}
