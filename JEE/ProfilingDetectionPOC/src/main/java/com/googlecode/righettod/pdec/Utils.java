package com.googlecode.righettod.pdec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

/**
 * Utilities shared methods.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Utils {

	/**
	 * Create an unique ID from an HTTP request.
	 * 
	 * @param hReq HTTP request source.
	 * @return the generated ID.
	 * @see "http://www.coderanch.com/t/134311/Security/java-security-MessageDigest-methods-digest"
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateID(HttpServletRequest hReq) throws NoSuchAlgorithmException {
		StringBuilder buffer = new StringBuilder();
		// Take sender IP address
		buffer.append(hReq.getRemoteAddr());
		// Take some key HTTP headers
		buffer.append(hReq.getHeader("Accept"));
		buffer.append(hReq.getHeader("Accept-Encoding"));
		buffer.append(hReq.getHeader("Accept-Language"));
		buffer.append(hReq.getHeader("Connection"));
		buffer.append(hReq.getHeader("User-Agent"));
		// Generate digest
		MessageDigest md = MessageDigest.getInstance("SHA1");
		String digest = DatatypeConverter.printHexBinary(md.digest(buffer.toString().getBytes()));
		return digest;
	}

}
