package com.drighetto.essai.bouncycastle.cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAKey;

/**
 * Implementation of encryption/decryption using JCE and asymmetric key to
 * cipher (encrypt) and decipher (decrypt) a long text because RSA algorithm
 * work on block that cannot be superior to the key size
 * 
 * <br>
 * <br>
 * <b>Asymetric algorithm are not recommended to encrypt long text, prefer
 * symetric algorithm...</b><br>
 * Concept : Use asymetric algorithm to protect symetric shared key !
 * 
 * @author Dominique RIGHETTO<br>
 *         13 oct. 07<br>
 */
public class AsymmetricKeyLongTextCipher {

	/**
	 * Algorithm/Mode/Padding used
	 */
	private static final String ALGORITHM = "RSA/ECB/PKCS1Padding";

	/** Security Provider */
	private static final String PROVIDER = "BC";

	/**
	 * Encryption strengh
	 */
	private static final int KEY_SIZE = 2048;

	/** Public Key */
	private static Key PUBLIC_KEY = null;

	/** Private Key */
	private static Key PRIVATE_KEY = null;

	// Static initialization
	static {
		try {
			// Add BouncyCastle Provider
			Security.addProvider(new BouncyCastleProvider());
			// Generate public and private keys
			// ---Create a KeyPair generator
			KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM.split("/")[0].trim(), PROVIDER);
			generator.initialize(KEY_SIZE);
			// ---Create keys
			KeyPair keyPair = generator.generateKeyPair();
			PUBLIC_KEY = keyPair.getPublic();
			PRIVATE_KEY = keyPair.getPrivate();
		} catch (NoSuchProviderException nspe) {
			nspe.printStackTrace();
			System.exit(1);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			System.exit(2);
		}
	}

	/**
	 * 
	 * Default constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 */
	public AsymmetricKeyLongTextCipher() {
		super();
	}

	/**
	 * Encryption method
	 * 
	 * @param message
	 * @return Encrypted bytes
	 * @throws Exception
	 * @throws IOException
	 */
	public byte[] encrypt(byte[] message) throws IOException, Exception {
		byte[] data = null;

		// Create a cipher instance
		Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
		// Initialize the cipher instance with the processing mode and the
		// key used
		cipher.init(Cipher.ENCRYPT_MODE, PUBLIC_KEY);
		// Cipher data by block of 63 bytes
		ByteArrayInputStream input = new ByteArrayInputStream(message);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[63];
		byte[] dataTmp = null;
		int bufferLength = 0;
		while ((bufferLength = input.read(buffer)) != -1) {
			dataTmp = cipher.doFinal(copyBytes(buffer, bufferLength));
			output.write(dataTmp);
		}
		// Get final ciphered data
		data = output.toByteArray();
		// Release stream (no really matter with ByteArray*Stream objects
		// because there memory stream)
		input.close();
		output.close();

		return data;
	}

	/**
	 * Decryption method
	 * 
	 * @param message
	 * @return Decrypted bytes
	 * @throws Exception
	 * @throws IOException
	 */
	public byte[] decrypt(byte[] message) throws IOException, Exception {
		byte[] data = null;

		// Create a cipher instance
		Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
		// Initialize the cipher instance with the processing mode and the
		// key used
		cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
		// Decipher data by block according to the key size
		ByteArrayInputStream input = new ByteArrayInputStream(message);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int keySize = ((RSAKey) PRIVATE_KEY).getModulus().bitLength();
		byte[] buffer = new byte[keySize / 8];
		byte[] dataTmp = null;
		int bufferLength = 0;
		while ((bufferLength = input.read(buffer)) != -1) {
			dataTmp = cipher.doFinal(copyBytes(buffer, bufferLength));
			output.write(dataTmp);
		}
		// Get final deciphered data
		data = output.toByteArray();
		// Release stream (no really matter with ByteArray*Stream objects
		// because there memory stream)
		input.close();
		output.close();

		return data;
	}

	/**
	 * Method to copy bytes to a new array
	 * 
	 * @param arr Input byte array
	 * @param length Byte array length
	 * @return A byte array
	 * @throws Exception
	 */
	private byte[] copyBytes(byte[] arr, int length) throws Exception {
		byte[] newArr = null;
		if (arr.length == length) {
			newArr = arr;
		} else {
			newArr = new byte[length];
			System.arraycopy(arr, 0, newArr, 0, length);
		}
		return newArr;
	}

	/**
	 * Get the key size
	 * 
	 * @return the key size
	 */
	public int getKeySize() {
		return ((RSAKey) PRIVATE_KEY).getModulus().bitLength();
	}

}
