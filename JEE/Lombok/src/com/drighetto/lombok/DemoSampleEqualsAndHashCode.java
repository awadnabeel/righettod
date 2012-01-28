package com.drighetto.lombok;

/**
 * Class to run the "SampleEqualsAndHashCode" sample class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class DemoSampleEqualsAndHashCode {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SampleEqualsAndHashCode sampleEqualsAndHashCode01 = new SampleEqualsAndHashCode();
		SampleEqualsAndHashCode sampleEqualsAndHashCode02 = new SampleEqualsAndHashCode();
		/*
		 * Test the equality of two objects. There equals here because the
		 * "equals()" methods compare equality on all non-static non-transient
		 * attributes values and in our case the both objects have the sames
		 * values for their attributes...
		 */
		System.out.printf("Are Equals        : %s\n", sampleEqualsAndHashCode01.equals(sampleEqualsAndHashCode02));
		/* Print objects HashCodes */
		System.out.printf("Object 1 HashCode : %s\n", sampleEqualsAndHashCode01.hashCode());
		System.out.printf("Object 2 HashCode : %s\n", sampleEqualsAndHashCode02.hashCode());
	}

}
