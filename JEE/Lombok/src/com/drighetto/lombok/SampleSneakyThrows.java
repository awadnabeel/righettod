package com.drighetto.lombok;

import java.io.IOException;

import lombok.SneakyThrows;

/**
 * Sample class in order to show the use of the LOMBOK annotations :
 * <ul>
 * <li>@SneakyThrows</li>
 * </ul>
 * <a href="http://projectlombok.org/features/SneakyThrows.html">SneakyThrows
 * documentation page</a> <br>
 * <br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SampleSneakyThrows {

	/**
	 * Test method
	 */
	@SneakyThrows(IOException.class)
	public void testException() {
		throw new IOException("test exception");
	}

}
