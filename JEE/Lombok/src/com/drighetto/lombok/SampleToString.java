package com.drighetto.lombok;

import lombok.ToString;

/**
 * Sample POJO in order to show the use of the LOMBOK annotations :
 * <ul>
 * <li>@ToString</li>
 * </ul>
 * <a href="http://projectlombok.org/features/ToString.html">ToString
 * documentation page</a> <br>
 * <br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@ToString(includeFieldNames = true)
public class SampleToString {

	/** Single field that will be used in the ToString() generated methods */
	private String myMessage01 = "Hello";

	/** Single field that will be used in the ToString() generated methods */
	private String myMessage02 = "World !!!";

}
