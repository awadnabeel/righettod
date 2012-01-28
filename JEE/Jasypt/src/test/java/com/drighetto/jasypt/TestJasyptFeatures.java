package com.drighetto.jasypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.jasypt.digest.StandardByteDigester;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.jasypt.util.binary.StrongBinaryEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.ImageIcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * This test is used to show somes JASYPT features, in this class the SUN JCE
 * Security Provider is used.
 * 
 * @author Dominique RIGHETTO (dominique.righetto@gmail.com)
 * 
 */
public class TestJasyptFeatures {

	/** Text used in text encryption samples */
	private static final String TEXT_TO_ENCRYPT = "Hello World !!!!";

	/** Password used for encryption */
	private static final String PASSWORD_FOR_ENCRYPTION = "hello_world";

	/** Init method used to display java version */
	@BeforeClass
	public static void init() {
		System.out.printf("JAVA VERSION : %s\n", System.getProperty("java.version"));
	}

	/**
	 * This test show how encrypt a password using a basic encryptor, same stuff
	 * is provided for Number encryption, see BasicInteger* and BasicDecimal*
	 * classes...
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/password/
	 *      BasicPasswordEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testPasswordEncryptionUsingBasicEncryptor() throws Exception {
		// Declare a basic encryptor
		BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();
		// "Encrypt password", is not an real encryption because "Encryptor"
		// generate a digest. by default the MD5 algorithm is used
		String encryptedPassword = bpe.encryptPassword(TEXT_TO_ENCRYPT);
		System.out.printf("testPasswordEncryptionUsingBasicEncryptor : '%s' become '%s'\n", TEXT_TO_ENCRYPT, encryptedPassword);
		// Valid "encrypted" password
		Assert.assertTrue(bpe.checkPassword(TEXT_TO_ENCRYPT, encryptedPassword));
	}

	/**
	 * This test show how encrypt a password using a strong encryptor, same
	 * stuff is provided for Number encryption, see StrongInteger* and
	 * StrongDecimal* classes...
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/password/
	 *      StrongPasswordEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testPasswordEncryptionUsingStrongEncryptor() throws Exception {
		// Declare a strong encryptor
		StrongPasswordEncryptor pe = new StrongPasswordEncryptor();
		// "Encrypt password", is not an real encryption because "Encryptor"
		// generate a digest. by default the SHA-256 algorithm is used
		String encryptedPassword = pe.encryptPassword(TEXT_TO_ENCRYPT);
		System.out.printf("testPasswordEncryptionUsingStrongEncryptor : '%s' become '%s'\n", TEXT_TO_ENCRYPT, encryptedPassword);
		// Valid "encrypted" password
		Assert.assertTrue(pe.checkPassword(TEXT_TO_ENCRYPT, encryptedPassword));
	}

	/**
	 * This test show how encrypt a password using a configurable encryptor.
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/password/
	 *      ConfigurablePasswordEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testPasswordEncryptionUsingConfigurableEncryptor() throws Exception {
		// Declare a configurable encryptor
		ConfigurablePasswordEncryptor pe = new ConfigurablePasswordEncryptor();
		// Configure it
		pe.setAlgorithm("SHA-512");
		pe.setPlainDigest(false);
		pe.setStringOutputType("base64");
		// "Encrypt password", is not an real encryption because "Encryptor"
		// generate a digest
		String encryptedPassword = pe.encryptPassword(TEXT_TO_ENCRYPT);
		System.out.printf("testPasswordEncryptionUsingConfigurableEncryptor : '%s' become '%s'\n", TEXT_TO_ENCRYPT, encryptedPassword);
		// Valid "encrypted" password
		Assert.assertTrue(pe.checkPassword(TEXT_TO_ENCRYPT, encryptedPassword));
	}

	/**
	 * This test show how encrypt a text using a basic encryptor (using symetric
	 * key)
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/text/
	 *      BasicTextEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testTextEncryptionUsingBasicTextEncryptor() throws Exception {
		// Declare a basic encryptor
		BasicTextEncryptor te = new BasicTextEncryptor();
		// Set password to use
		te.setPassword(PASSWORD_FOR_ENCRYPTION);
		// Encrypt text. By default the algorithm used is PBEWithMD5AndDES
		String encryptedText = te.encrypt(TEXT_TO_ENCRYPT);
		System.out.printf("testTextEncryptionUsingBasicTextEncryptor : '%s' become '%s'\n", TEXT_TO_ENCRYPT, encryptedText);
		// Valid "encrypted" text
		Assert.assertEquals(te.decrypt(encryptedText), TEXT_TO_ENCRYPT);
	}

	/**
	 * This test show how encrypt a text using a strong encryptor (using
	 * symetric key)
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/text/
	 *      StrongTextEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testTextEncryptionUsingStrongTextEncryptor() throws Exception {
		// Declare a strong encryptor
		StrongTextEncryptor te = new StrongTextEncryptor();
		// Set password to use
		te.setPassword(PASSWORD_FOR_ENCRYPTION);
		// Encrypt text. By default the algorithm used is PBEWithMD5AndTripleDES
		String encryptedText = te.encrypt(TEXT_TO_ENCRYPT);
		System.out.printf("testTextEncryptionUsingStrongTextEncryptor : '%s' become '%s'\n", TEXT_TO_ENCRYPT, encryptedText);
		// Valid "encrypted" text
		Assert.assertEquals(te.decrypt(encryptedText), TEXT_TO_ENCRYPT);
	}

	/**
	 * This test show how encrypt a binary data using a basic encryptor (using
	 * symetric key)
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/binary/
	 *      BasicBinaryEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testBinaryEncryptionUsinBasicBinaryEncryptor() throws Exception {
		FileOutputStream fos = null;
		try {
			// Load binary data from a image file
			byte[] data = IOUtils.toByteArray(this.getClass().getResourceAsStream("/jasypt.png"));
			// Declare a basic encryptor
			BasicBinaryEncryptor be = new BasicBinaryEncryptor();
			// Set password to use
			be.setPassword(PASSWORD_FOR_ENCRYPTION);
			// Encrypt data. By default the algorithm used is PBEWithMD5AndDES
			byte[] encryptedData = be.encrypt(data);
			// Valid "encrypted" data
			byte[] decryptedData = be.decrypt(encryptedData);
			Assert.assertArrayEquals(data, decryptedData);
			// Create a image file with the decrypted data in order to validate
			// that data are not corrupted
			File f = File.createTempFile("JASYPT", ".png");
			fos = new FileOutputStream(f);
			IOUtils.write(decryptedData, fos);
			System.out.printf("testBinaryEncryptionUsinBasicBinaryEncryptor : Image file validation copy saved to '%s'\n", f.getAbsolutePath());
			// We load validation image copy to validate it
			new ImageIcon(f.getAbsolutePath());
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * This test show how encrypt a binary data using a strong encryptor (using
	 * symetric key)
	 * 
	 * @see http://www.jasypt.org/api/jasypt/apidocs/org/jasypt/util/binary/
	 *      StrongBinaryEncryptor.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testBinaryEncryptionUsinStrongBinaryEncryptor() throws Exception {
		FileOutputStream fos = null;
		try {
			// Load binary data from a image file
			byte[] data = IOUtils.toByteArray(this.getClass().getResourceAsStream("/jasypt.png"));
			// Declare a strong encryptor
			StrongBinaryEncryptor be = new StrongBinaryEncryptor();
			// Set password to use
			be.setPassword(PASSWORD_FOR_ENCRYPTION);
			// Encrypt data. By default the algorithm used is
			// PBEWithMD5AndTripleDES
			byte[] encryptedData = be.encrypt(data);
			// Valid "encrypted" data
			byte[] decryptedData = be.decrypt(encryptedData);
			Assert.assertArrayEquals(data, decryptedData);
			// Create a image file with the decrypted data in order to validate
			// that data are not corrupted
			File f = File.createTempFile("JASYPT", ".png");
			fos = new FileOutputStream(f);
			IOUtils.write(decryptedData, fos);
			System.out.printf("testBinaryEncryptionUsinStrongBinaryEncryptor : Image file validation copy saved to '%s'\n", f.getAbsolutePath());
			// We load validation image copy to validate it
			new ImageIcon(f.getAbsolutePath());
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * This test show how generate a digest for String or a Byte data
	 * 
	 * @see http://www.jasypt.org/getting-started-standard.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testDigesters() throws Exception {
		/* BYTE Digester */
		// Create a byte digester
		StandardByteDigester byteDigester = new StandardByteDigester();
		// Configure it
		byteDigester.setAlgorithm("SHA-512");
		byteDigester.setSaltSizeBytes(128);
		// Load binary data from a image file
		byte[] data = IOUtils.toByteArray(this.getClass().getResourceAsStream("/jasypt.png"));
		// Generate a digest
		byte[] digest = byteDigester.digest(data);
		// Display it
		String str = String.valueOf(Hex.encodeHex(digest));
		System.out.printf("testDigesters : Byte digest '%s'\n", str);
		/* STRING Digester */
		// Create a String digester
		StandardStringDigester stringDigester = new StandardStringDigester();
		// Configure it
		stringDigester.setAlgorithm("SHA-512");
		stringDigester.setSaltSizeBytes(128);
		// Generate a digest
		str = stringDigester.digest(TEXT_TO_ENCRYPT);
		// Display it
		System.out.printf("testDigesters : String digest '%s'\n", str);
	}

	/**
	 * This test show how to encrypt properties in a properties files
	 * 
	 * @see http://www.jasypt.org/encrypting-configuration.html
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testPropertiesFilePropertiesEncryption() throws Exception {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			// Create a encryptor
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			// Configure it
			encryptor.setAlgorithm("PBEWithMD5AndDES");
			encryptor.setPassword(PASSWORD_FOR_ENCRYPTION);
			encryptor.setStringOutputType("base64");
			encryptor.setKeyObtentionIterations(5);
			// Create a properties file accessor that will use encryptor defined
			// above to decrypt value. Note that this class do not encrypt value
			// when we use setProperty() method, we must encrypt ourself the
			// property value an put
			// encrypted property value using the syntax "ENC(encrypted_value)"
			// in the property container. If the encrypted value is not surrounded
			// by "ENC()" the EncryptableProperties object assume that the value isn't
			// encrypted and read it as is...
			Properties properties = new EncryptableProperties(encryptor);
			// Fill properties container
			// --Encrypt value
			String encryptedValue = encryptor.encrypt(TEXT_TO_ENCRYPT);
			// --Add value to the properties container
			properties.setProperty("PASSWORD", "ENC(" + encryptedValue + ")");
			// --Add a non encrypted propety
			properties.setProperty("LOGIN", "MY_LOGIN");
			// Save properties to a file
			File f = File.createTempFile("JASYPT", ".properties");
			fos = new FileOutputStream(f);
			properties.store(fos, "Sample properties file");
			System.out.printf("testPropertiesFilePropertiesEncryption : Properties file saved to '%s'\n", f.getAbsolutePath());
			// Clear all properties and reload properties file
			properties.clear();
			Assert.assertNull(properties.getProperty("PASSWORD"));
			Assert.assertNull(properties.getProperty("LOGIN"));
			fis = new FileInputStream(f);
			properties.load(fis);
			// Valid properties stored value, the property decryption value is
			// realized on the fly the EncryptableProperties object...
			Assert.assertEquals(TEXT_TO_ENCRYPT, properties.getProperty("PASSWORD"));
			Assert.assertEquals("MY_LOGIN", properties.getProperty("LOGIN"));
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(fis);
		}

	}
}
