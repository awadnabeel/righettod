package com.drighetto.lombok;

/**
 * Class to run the "SampleToString" sample class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class DemoSampleToString {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SampleToString sampleToString = new SampleToString();

		/* Print the toString() methods return */
		System.out.printf("SampleToString.toString() :\n%s\n", sampleToString.toString());

	}

}
