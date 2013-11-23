package com.googlecode.righettod.jee6cdi.bean;

import org.apache.commons.codec.DecoderException;

/**
 * Interface to define a contract for Encoder implementation.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public interface Encoder {

	/**
	 * Encode
	 * 
	 * @param s String to encode
	 * @return Encoded String
	 */
	String encode(String s);

	/**
	 * Decode
	 * 
	 * @param s String to decode
	 * @return Decoded String
	 * @throws DecoderException
	 */
	String decode(String s) throws DecoderException;

}