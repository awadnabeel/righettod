package com.drighetto.jcifs.test;

import java.io.File;
import java.io.FileWriter;

import jcifs.smb.SmbFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.drighetto.jcifs.FileProcessor;

/**
 * Test class for TestFileProcessor
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
@SuppressWarnings( { "unused", "boxing" })
public class TestFileProcessor {

	/** Target share URL */
	private static String SHARE_URL_01 = "smb://xpsp2-49f76c132/sharefolder/";

	/** Target share URL */
	private static String SHARE_URL_02 = "smb://xpsp2-49f76c132/sharefolder/TEMP/";

	/** Root directory for INPUT */
	private static SmbFile ROOT_DIRECTORY_INPUT = null;

	/** Root directory for OUTPUT */
	private static SmbFile ROOT_DIRECTORY_OUTPUT = null;

	/** Temporary local directory */
	private static File TEMP_DIRECTORY = new File("tempTestDir");

	/**
	 * Prepare test
	 */
	@BeforeClass
	public static void prepare() {
		try {
			// Delete temp directory
			FileUtils.deleteQuietly(TEMP_DIRECTORY);
			// Connect to root directory for INPUT data
			ROOT_DIRECTORY_INPUT = FileProcessor.connectToRootDirectoryWithAuthentication(SHARE_URL_01, "Workgroup",
					"GUEST", "");
			// Connect to root directory for OUTPUT data
			ROOT_DIRECTORY_OUTPUT = FileProcessor.connectToRootDirectoryWithAuthentication(SHARE_URL_02, "Workgroup",
					"GUEST", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for the method "listFiles"
	 */
	@Test
	public void listFilesTest() {
		try {
			FileProcessor.listFiles(ROOT_DIRECTORY_INPUT);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test method for the method "downloadFiles"
	 */
	@Test
	public void downloadFilesTest() {
		try {
			FileProcessor.downloadFiles(ROOT_DIRECTORY_INPUT, "*.dot", TEMP_DIRECTORY, true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test method for the method "uploadFiles"
	 */
	@Test
	public void uploadFilesTest() {
		try {
			FileProcessor.uploadFiles(TEMP_DIRECTORY, ROOT_DIRECTORY_OUTPUT, true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Scability API test sample
	 */
	@Test
	public void scabilityTest() {
		StopWatch sw = new StopWatch();
		FileWriter fw = null;
		try {
			// Create a CSV file with test result
			File csvFile = new File("ScabilityTestResult.csv");
			fw = new FileWriter(csvFile, false);
			// Perform a fixed number a work iteration including download and
			// upload tasks
			StringBuilder data = new StringBuilder();
			int iterationCount = 5000;
			for (int i = 0; i < iterationCount; i++) {
				System.out.printf("Iteration count : %s/%s\n", i, iterationCount);
				sw.start();
				// Download step
				FileProcessor.downloadFiles(ROOT_DIRECTORY_INPUT, "*.dot", TEMP_DIRECTORY, false);
				// Upload step
				FileProcessor.uploadFiles(TEMP_DIRECTORY, ROOT_DIRECTORY_OUTPUT, false);
				// Collect timing data
				sw.stop();
				data.delete(0, data.length());
				data.append(i).append(";").append(sw.getTime()).append("\n");
				fw.write(data.toString());
				fw.flush();
				sw.reset();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} finally {
			IOUtils.closeQuietly(fw);
		}
	}

}
