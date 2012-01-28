package com.drighetto.webdav;

/**
 * Access informations
 * 
 * @author Dominique Righetto<br>
 *         27-avr.-07
 * 
 */
public interface Constant {
	String EXCHANGE_HOST = "cjmbx01.ad.curia.europa.eu";

	String APPLICATION_USER_ACCOUNT_NAME = "CURIA\\drig";

	String APPLICATION_USER_PASSWORD = "cT5nd75B";

	String PREFIX = "Exchange";

	//String MAILBOX_NAME = "tbo";//BAL of Thomas Boibessot....
    String MAILBOX_NAME = "drig";//My BAL....
	
	String DAV_URL = "http://" + EXCHANGE_HOST + "/" + PREFIX + "/" + MAILBOX_NAME + "/";
	
	int DEBUG_LEVEL = 1;
	
}
