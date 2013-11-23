package com.googlecode.righettod.jee6cdi.bean;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.googlecode.righettod.jee6cdi.qualifier.AuditProcessing;
import com.googlecode.righettod.jee6cdi.qualifier.HexadecimalFormat;

/**
 * Implementation of a bean providing Hexadecimal encoding/decoding features.<br>
 * The custom CDI annotation "[at]HexadecimalFormat" is used in order to specify to CDI container<br>
 * to inject this implementation if the injection order specify the annotation "[at]HexadecimalFormat".<br>
 * The custom CDI annotation "[at]AuditProcessing" is used in order to specify to CDI container to enable <br>
 * the CDI lifecycle interceptor implementation associated to this annotation.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@HexadecimalFormat
@AuditProcessing
public class EncoderHexadecimalImpl implements Encoder {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String s) {
		return Hex.encodeHexString(s.getBytes());
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
		return new String(Hex.decodeHex(s.toCharArray()));
	}
}
