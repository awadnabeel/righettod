package com.googlecode.righettod.cip.type;

/**
 * Enumeration of possible information source to explore.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public enum InformationSource {

	/** Registry */
	WINDOWS_REGISTRY,

	/** Environement */
	ENVIRONMENT_VARIABLES,

	/** File system */
	FILESYSTEM,

	/** Cookies : IE */
	IE_COOKIES,

	/** Cookies : Firefox */
	FIREFOX_COOKIES,

	/** Cookies : Chrome */
	CHROME_COOKIES,

	/** Cookies : Safari */
	SAFARI_COOKIES;
}
