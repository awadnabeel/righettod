package com.drighetto.springjruby;

import java.io.IOException;

/**
 * Interface defining some actions
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public interface ActionDefinition {

	/**
	 * Method to write content to a file
	 * 
	 * @param content
	 *            Content
	 * @param filename
	 *            Name of the target file with the full path
	 * @throws IOException
	 */
	void writeContent(String content, String filename) throws IOException;

	/**
	 * Method to read the content to a file
	 * 
	 * @param filename
	 *            Name of the target file with the full path
	 * @return The file content as a String
	 * @throws IOException
	 */
	String readContent(String filename) throws IOException;

	/**
	 * Method to obtain the last method called
	 * 
	 * @return a message
	 */
	String obtainLastMethodCalled();

}
