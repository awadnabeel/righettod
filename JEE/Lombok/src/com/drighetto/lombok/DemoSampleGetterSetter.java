package com.drighetto.lombok;

/**
 * Class to run the "SampleGetterSetter" sample class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class DemoSampleGetterSetter extends SampleGetterSetter {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) throws Exception {
		SampleGetterSetter sampleGetterSetter = new SampleGetterSetter();

		/* Public visibility */
		sampleGetterSetter.setProperty01("Hello World !");
		System.out.printf("Public    visibility property value : %s\n", sampleGetterSetter.getProperty01());

		/* Protected visibility */
		sampleGetterSetter.setProperty02(true);
		System.out.printf("Protected visibility property value : %s\n", sampleGetterSetter.isProperty02());

		/* Package visibility */
		sampleGetterSetter.setProperty03("Hello World !");
		System.out.printf("Packagage visibility property value : %s\n", sampleGetterSetter.getProperty03());

		/*
		 * Validate the presence of null-check for the public visibility
		 * property
		 */
		try {
			sampleGetterSetter.setProperty01(null);
		} catch (NullPointerException e) {
			System.out.println("Public visibility property null check enabled !");
		}

	}

}
