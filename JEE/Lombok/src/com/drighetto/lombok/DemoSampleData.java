package com.drighetto.lombok;

/**
 * Class to run the "SampleData" sample class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class DemoSampleData {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SampleData data01 = new SampleData(123456);
		SampleData data02 = new SampleData(123456);
		SampleData data03 = new SampleData(654321);

		/* Fill objects */
		data01.setName("MyFN");
		data02.setName(data01.getName());
		data03.setName(data02.getName());

		/*
		 * Test the equality of three objects.
		 */
		System.out.printf("Object 1 and Object 2 are equals : %s\n", data01.equals(data02));
		System.out.printf("Object 1 and Object 3 are equals : %s\n", data01.equals(data03));

		/* Print objects HashCodes */
		System.out.printf("Object 1 HashCode                : %s\n", data01.hashCode());
		System.out.printf("Object 2 HashCode                : %s\n", data02.hashCode());
		System.out.printf("Object 3 HashCode                : %s\n", data03.hashCode());
	}

}
