package com.drighetto.lombok;

import lombok.EqualsAndHashCode;

/**
 * Sample POJO in order to show the use of the LOMBOK annotations :
 * <ul>
 * <li>@EqualsAndHashCode</li>
 * </ul>
 * <a href="http://projectlombok.org/features/EqualsAndHashCode.html">EqualsAndHashCode
 * documentation page</a> <br>
 * <br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@EqualsAndHashCode
public class SampleEqualsAndHashCode {

	/** Single field that will be used in the Equals() and HashCode() methods */
	private String myMessage01 = "Hello";

	/** Single field that will be used in the Equals() and HashCode() methods */
	private String myMessage02 = "World !!!";

}
