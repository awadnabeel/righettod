package com.drighetto.lombok;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.Cleanup;

/**
 * Sample class in order to show the use of the LOMBOK annotations :
 * <ul>
 * <li>@Cleanup</li>
 * </ul>
 * <a href="http://projectlombok.org/features/Cleanup.html">Cleanup
 * documentation page</a> <br>
 * <br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SampleCleanup {

	/**
	 * Method to write data to the file specifed in the constructor
	 * 
	 * @param data
	 *        Data to write
	 * @param targetFile
	 *        Target output file
	 * @throws IOException
	 */
	public void writeData(String data, File targetFile) throws IOException {
		// This annotation is only allowed on local variable...
		@Cleanup
		FileOutputStream stream = new FileOutputStream(targetFile, false);
		stream.write(data.getBytes());
	}

}
