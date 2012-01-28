package com.drighetto.jcifs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.StopWatch;

/**
 * Class that process files using JCIFS API
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
@SuppressWarnings("boxing")
public abstract class FileProcessor {

	/**
	 * Initialize JCIFS properties
	 * 
	 * cf http://jcifs.samba.org/src/docs/api/overview-summary.html#scp
	 */
	static {
		// Stream encoding
		jcifs.Config.setProperty("jcifs.encoding", "UTF-8");
	}

	/**
	 * Method to obtain a file reference to the root directory without
	 * authentication
	 * 
	 * @param smbURL Samba URL of the target share
	 * @return a file reference through a JCIFS Samba file object
	 * @throws Exception
	 */
	public static SmbFile connectToRootDirectoryWithoutAuthentication(String smbURL) throws Exception {
		StopWatch sw = new StopWatch();

		// Connect to share using the samba URL specified
		sw.start();
		SmbFile root = new SmbFile(smbURL);
		// Force connection with the share to be established before the first
		// use of the file reference object
		root.connect();
		sw.stop();
		System.out.printf("CONNECTION DELAY   : %s\n", sw.getTime());

		return root;
	}

	/**
	 * Method to obtain a file reference to the root directory with
	 * authentication
	 * 
	 * @param smbURL Samba URL of the target share
	 * @param domain Network domain
	 * @param login Login
	 * @param password Password
	 * @return a file reference through a JCIFS Samba file object
	 * @throws Exception
	 */
	public static SmbFile connectToRootDirectoryWithAuthentication(String smbURL, String domain, String login,
			String password) throws Exception {
		StopWatch sw = new StopWatch();

		// Create authentication object
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, login, password);
		// Connect to share using the samba URL specified
		sw.start();
		SmbFile root = new SmbFile(smbURL, auth);
		// Force connection with the share to be established before the first
		// use of the file reference object
		root.connect();
		sw.stop();
		System.out.printf("CONNECTION DELAY   : %s\n", sw.getTime());

		return root;
	}

	/**
	 * Method to list content of a directory
	 * 
	 * @param root File reference to the root directory
	 * @throws Exception
	 */
	public static void listFiles(SmbFile root) throws Exception {
		StopWatch sw = new StopWatch();
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

		// Parse files collection
		sw.start();
		for (SmbFile file : root.listFiles()) {
			System.out.printf("FILENAME           : %s\n", file.getName());
			System.out.printf("PATH               : %s\n", file.getPath());
			System.out.printf("UNC                : %s\n", file.getUncPath());
			System.out.printf("TYPE               : %s\n", file.getType());
			System.out.printf("LENGTH             : %s\n", file.length());
			System.out.printf("IS FILE            : %s\n", file.isFile());
			System.out.printf("IS DIRECTORY       : %s\n", file.isDirectory());
			System.out.printf("IS HIDDEN          : %s\n", file.isHidden());
			System.out.printf("IS READEABLE       : %s\n", file.canRead());
			System.out.printf("IS WRITEABLE       : %s\n", file.canWrite());
			System.out.printf("SHARE              : %s\n", file.getShare());
			System.out.printf("MODIFICATION DATE  : %s\n", df.format(file.lastModified()));
			sw.stop();
			System.out.printf("DISPLAY DELAY      : %s", sw.getTime());
			sw.reset();
			sw.start();
			System.out.print("\n----------------\n");
		}

	}

	/**
	 * Method to download files according to a wildcard selection
	 * 
	 * @param root File reference to the root directory
	 * @param wildcard Files wildcard selection expression
	 * @param localTargetDirectory Local target directory
	 * @param displayInfo Flag to activate the displaying of processing
	 *            informations
	 * @throws Exception
	 */
	public static void downloadFiles(SmbFile root, String wildcard, File localTargetDirectory, boolean displayInfo)
			throws Exception {
		StopWatch sw = new StopWatch();

		// Check target directory existence
		if (!localTargetDirectory.exists()) {
			localTargetDirectory.mkdir();
		}
		// Parse file collection
		for (SmbFile file : root.listFiles(wildcard)) {
			// Tranfer ONLY files
			if (file.isDirectory()) {
				continue;
			}
			sw.start();
			// Transfer current file
			OutputStream os = new FileOutputStream(localTargetDirectory + "/" + file.getName());
			InputStream is = file.getInputStream();
			IOUtils.copy(file.getInputStream(), os);
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(is);
			// Display timing informations
			sw.stop();
			if (displayInfo) {
				System.out.println("COPY");
				System.out.printf("   IN    : %s\n", file.getPath());
				System.out.printf("   OUT   : %s\n", localTargetDirectory + "/" + file.getName());
				System.out.printf("   DELAY : %s\n", sw.getTime());
				System.out.print("----------------\n");
			}
			sw.reset();
		}
	}

	/**
	 * Method to upload files
	 * 
	 * @param localSourceDirectory Local source directory
	 * @param root File reference to the target root directory
	 * @param displayInfo Flag to activate the displaying of processing
	 *            informations
	 * @throws Exception
	 */
	public static void uploadFiles(File localSourceDirectory, SmbFile root, boolean displayInfo) throws Exception {
		StopWatch sw = new StopWatch();

		// Check target directory existence
		if (!root.exists()) {
			root.mkdir();
		}
		// Parse file collection
		for (File file : localSourceDirectory.listFiles()) {
			// Tranfer ONLY files
			if (file.isDirectory()) {
				continue;
			}
			sw.start();
			// Transfer current file
			String targetURL = root.getPath() + file.getName();
			SmbFile targetFile = new SmbFile(targetURL);
			OutputStream os = targetFile.getOutputStream();
			InputStream is = new FileInputStream(file);
			IOUtils.copy(is, os);
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(is);
			// Display timing informations
			sw.stop();
			if (displayInfo) {
				System.out.println("COPY");
				System.out.printf("   IN    : %s\n", file.getPath());
				System.out.printf("   OUT   : %s\n", targetFile.getPath());
				System.out.printf("   DELAY : %s\n", sw.getTime());
				System.out.print("----------------\n");
			}
			sw.reset();
		}

	}
}
