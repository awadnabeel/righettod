package com.drighetto.lombok;

import java.io.File;

/**
 * Class to run the "SampleCleanup" sample class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class DemoSampleCleanup {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SampleCleanup sampleCleanup = new SampleCleanup();

		/*
		 * Write sample data into a file many time in order to validate that the
		 * stream is really closed when the methods processing is finished...
		 */
		File testFile = new File("test.txt");
		for (int i = 1; i < 1000000; i++) {
			sampleCleanup.writeData("Hello World !!!", testFile);
		}
		testFile.delete();
	}

}
