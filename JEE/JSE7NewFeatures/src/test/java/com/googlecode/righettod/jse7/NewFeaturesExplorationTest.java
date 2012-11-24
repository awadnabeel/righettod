package com.googlecode.righettod.jse7;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;
import java.util.zip.DataFormatException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This class is used to explore new features of the JDK 1.7<br>
 * <br>
 * TODO : Add sample with Java DB embedded into JDK
 * 
 * @see "http://www.rapidprogramming.com/tutorial/List-Java-1-7-Features-418"
 * @see "http://www.slideshare.net/denizoguz/new-features-of-jdk-7#btnNext"
 * 
 * 
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings({ "boxing", "static-method" })
public class NewFeaturesExplorationTest {

	/**
	 * Rule to manage expected exception in test cases.
	 */
	@Rule
	public ExpectedException thrownRule = ExpectedException.none();

	/**
	 * Explore "Binary Literals".<br>
	 * The integral types namely byte, short, int and long can also be expressed with the binary number system.<br>
	 * To specify these integral types as binary literals, add the prefix 0B or 0b to number.
	 */
	@Test
	public void testFeatureBinaryLiterals() {
		System.out.println("*** Binary Literals ***");
		int intFromBinary = 0b101010101010;
		short shortFromBinary = (short) 0b1010000101000101;
		long longFromBinary = 0b1010000101000101101000010100010110100001010001011010000101000101L;
		System.out.printf("shortFromBinary = %s\n", shortFromBinary);
		System.out.printf("intFromBinary   = %s\n", intFromBinary);
		System.out.printf("longFromBinary  = %s\n", longFromBinary);
	}

	/**
	 * Explore "Underscores In Binary Literals".<br>
	 * "_" can be used in-between digits in any numeric literal.<br>
	 * "_" can be used to group the digits similar to what "," does when a bigger number is specified.<br>
	 * "_" can be specified only between digits and not in the beginning or end of the number.
	 */
	@Test
	public void testUnderscoresInNumberLiterals() {
		System.out.println("*** Underscores In Binary Literals ***");
		int aInt = 0b1010_1010_1010;
		long aLong = 9_223_783_036_967_937L;
		float aFloat = 3.14_15F;
		long aHexBytes = 0xFF_EC_DE_5E;
		long aHexWords = 0xCAFE_BFFE;
		System.out.printf("Int      = %s\n", aInt);
		System.out.printf("Long     = %s\n", aLong);
		System.out.printf("Float    = %s\n", aFloat);
		System.out.printf("HexBytes = %s\n", aHexBytes);
		System.out.printf("HexWords = %s\n", aHexWords);
	}

	/**
	 * Explore "Strings in Switch Statements". Case Sensitive!<br>
	 * String Object as Expression in Switch Statement.
	 */
	@Test
	public void testStringsInSwitchStatements() {
		System.out.println("*** Strings in Switch Statements ***");
		String day = new SimpleDateFormat("EEEE", Locale.US).format(new Date()).toUpperCase();
		switch (day) {
		case "MONDAY":
		case "TUESDAY":
		case "WEDNESDAY": {
			System.out.println("First part of the week.");
			break;
		}
		case "THUESDAY":
		case "FRIDAY":
		case "SATURDAY": {
			System.out.println("Second part of the week.");
			break;
		}
		default: {
			System.out.println("We are on Sunday.");
		}
		}
	}

	/**
	 * Explore "Automatic Resource Management".<br>
	 * New superinterface "java.lang.AutoCloseable".<br>
	 * All AutoCloseable(throws Exception) and by extension java.io.Closeable(throws IOException) types useable with try-with-resources.<br>
	 * Anything with a void close()method is a candidate.<br>
	 * See Slide n°15 of "http://www.slideshare.net/denizoguz/new-features-of-jdk-7#btnNext" for details about exception behaviors.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAutomaticResourceManagement() throws Exception {
		System.out.println("*** Automatic Resource Management ***");
		// Resource closing is managed automatically....
		File testFile = new File("test.txt");
		testFile.delete();
		System.out.printf("Test file exists : %s\n", testFile.exists());
		try (OutputStream os = new FileOutputStream(testFile)) {
			os.write("Hello".getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.printf("Test file exists : %s\n", testFile.exists());
	}

	/**
	 * Explore "Exception multi catch".<br>
	 */
	@Test
	public void testMultiCatch() {
		System.out.println("*** Exception Multi Catch ***");
		int x = Calendar.getInstance().get(Calendar.MINUTE);
		try {
			if (x <= 30) {
				throw new DataFormatException("Case n°1");
			}
			if (x > 30) {
				throw new TimeoutException("Case n°2");
			}
		}
		catch (DataFormatException | TimeoutException exp) {
			System.out.println(exp.getMessage());
		}
	}

	/**
	 * Explore "More Precise Rethrow".<br>
	 * - Prior to Java 7, this code would not compile, the types in throws would have to match the <br>
	 * types in catch ; "testMorePreciseRethrow()" would have to “throws Exception”<br>
	 * - Java 7 adds support for this as long as try block calls all the exceptions in the throws clause, <br>
	 * that the variable in the catch clause is the variable that is rethrown and the exceptions are not caught by another catch block
	 * 
	 * @throws DataFormatException
	 * @throws TimeoutException
	 */
	@Test
	public void testMorePreciseRethrow() throws DataFormatException, TimeoutException {
		System.out.println("*** More Precise Rethrow ***");
		int x = Calendar.getInstance().get(Calendar.MINUTE);
		try {
			if (x <= 30) {
				this.thrownRule.expect(DataFormatException.class);
				throw new DataFormatException("Case n°1");
			}
			if (x > 30) {
				this.thrownRule.expect(TimeoutException.class);
				throw new TimeoutException("Case n°2");
			}
		}
		catch (Exception exp) {
			throw exp;
		}
	}

	/**
	 * Explore "NIO2 File Navigation Helpers".<br>
	 * 
	 * @see "http://www.jmdoudoux.fr/java/dej/chap-nio2.htm"
	 * @see "http://www.javabeat.net/2012/07/obtaining-and-modifying-the-metadata-of-the-files-in-java-7-nio-2/"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNIO2FileNavigationHelpers() throws Exception {
		System.out.println("*** NIO2 File Navigation Helpers ***");
		// Get reference using legacy java File class in order to have the URI :)
		File f = new File("pom.xml");
		// From here use NIO 2
		Path filePathRef = Paths.get(f.toURI());
		// Do somes actions using "Files" new Helper and Path class
		System.out.printf("Final Parts of file path : %s\n", filePathRef.getName(filePathRef.getNameCount() - 2));
		UserPrincipal owner = Files.getOwner(filePathRef, LinkOption.NOFOLLOW_LINKS);
		System.out.printf("Owner of file '%s' is '%s'\n", filePathRef.toFile().getName(), owner.getName());
		FileStore store = Files.getFileStore(filePathRef);
		System.out.println("Storage infos");
		System.out.printf(">>> Store name is '%s'\n", store.name());
		System.out.printf(">>> Store is read-only '%s'\n", store.isReadOnly());
		System.out.printf(">>> Store type is '%s'\n", store.type());
		String fileContentType = Files.probeContentType(filePathRef);
		System.out.printf("File '%s' content type is '%s'\n", f.getName(), fileContentType);
		List<String> content = Files.readAllLines(filePathRef, Charset.defaultCharset());
		System.out.printf("File '%s' content is '%s'\n", f.getName(), content);
		BasicFileAttributes basicAttributes = Files.readAttributes(filePathRef, BasicFileAttributes.class);
		System.out.println("File infos");
		System.out.printf(">>> Creation Time: %s\n", basicAttributes.creationTime());
		System.out.printf(">>> Directory? %s\n", basicAttributes.isDirectory());
		System.out.printf(">>> File? %s\n", basicAttributes.isRegularFile());
		System.out.printf(">>> Last accessed on: %s\n", basicAttributes.lastAccessTime());
		System.out.printf(">>> Last modified on: %s\n", basicAttributes.lastModifiedTime());
		System.out.printf(">>> File size (bytes): %s\n", basicAttributes.size());
		System.out.printf(">>> File key : %s\n", basicAttributes.fileKey());
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		Path tempDirPathRef = Paths.get(tempDir.toURI());
		Path p = null;
		Iterator<Path> tmpIter = null;
		System.out.printf("Files in directory '%s'\n", tempDir.getName());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(tempDirPathRef, "*.tmp")) {
			tmpIter = stream.iterator();
			while (tmpIter.hasNext()) {
				p = tmpIter.next();
				System.out.println(p.toUri());
			}
		}
	}
}
