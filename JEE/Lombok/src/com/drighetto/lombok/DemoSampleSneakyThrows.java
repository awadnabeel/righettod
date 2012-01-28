package com.drighetto.lombok;

/**
 * Class to run the "SneakyThrows" sample class
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class DemoSampleSneakyThrows {

	/**
	 * Enntry point
	 * 
	 * @param args
	 *        Command line
	 */
	public static void main(String[] args) {
		try {
			new SampleSneakyThrows().testException();
		} catch (Exception e) {
			System.out.printf("%s occur : [%s]", e.getClass().getName(), e.getMessage());
		}

	}

}
