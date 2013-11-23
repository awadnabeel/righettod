package com.googlecode.righettod.jee6cdi.bean;

import javax.enterprise.inject.Alternative;

import org.apache.commons.codec.DecoderException;

/**
 * Implementation of a bean providing Mocked encoding/decoding features for unit testing.<br>
 * The CDI annotation "[at]Alternative" is used in order to specify to CDI container<br>
 * to inject this implementation if the Alternative injection CDI feature is enabled.<br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/6/tutorial/doc/gjsdf.html"
 * 
 */
@Alternative
public class EncoderMockImpl implements Encoder {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String s) {
		System.out.println(">>> Use mocked encoder impl.");
		return s;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.jee6cdi.bean.Encoder#decode(java.lang.String)
	 */
	@Override
	public String decode(String s) throws DecoderException {
		System.out.println(">>> Use mocked decoder impl.");
		return s;
	}

}
