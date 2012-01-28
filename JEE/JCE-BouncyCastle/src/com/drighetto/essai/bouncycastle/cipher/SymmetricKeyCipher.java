package com.drighetto.essai.bouncycastle.cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Implementation of encryption/decryption using JCE and symmetric key
 * 
 * @author Dominique RIGHETTO<br>
 *         12 mars 07<br>
 */
public class SymmetricKeyCipher {

	/** Algorithm/Mode/Padding used */
	private static final String ALGORITHM = "AES/ECB/PKCS7Padding";

	/** Security Provider */
	private static final String PROVIDER = "BC";

	/**
	 * Encryption strengh, 128 bits is allowed without security patch
	 * application, higher strengh require security patch application !
	 */
	private static final int KEY_SIZE = 128;

	/** Symmetric Key */
	private static Key CIPHER_KEY;

	// Static initialization
	static {
		try {
			// Add BouncyCastle Provider
			Security.addProvider(new BouncyCastleProvider());
			// Generate random key
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM
					.split("/")[0].trim(), PROVIDER);
			generator.init(KEY_SIZE);
			CIPHER_KEY = generator.generateKey();
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
	 *         12 mars 07<br>
	 */
	public SymmetricKeyCipher() {
		super();
	}

	/**
	 * Encryption method
	 * 
	 * @param message
	 * @return Encrypted bytes
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IllegalStateException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] encrypt(byte[] message) throws InvalidKeyException,
			IllegalStateException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException {
		byte[] encryptedBytes = null;
		Cipher cipher = null;

		// Get Cipher instance
		cipher = Cipher.getInstance(ALGORITHM, PROVIDER);

		// Initialize cipher for encryption
		cipher.init(Cipher.ENCRYPT_MODE, CIPHER_KEY);

		// Encrypt message
		encryptedBytes = cipher.doFinal(message);

		return encryptedBytes;
	}

	/**
	 * Decryption method
	 * 
	 * @param message
	 * @return Decrypted bytes
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IllegalStateException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] decrypt(byte[] message) throws InvalidKeyException,
			IllegalStateException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException {
		byte[] decryptedBytes = null;
		Cipher cipher = null;

		// Get Cipher instance
		cipher = Cipher.getInstance(ALGORITHM, PROVIDER);

		// Initialize cipher for encryption
		cipher.init(Cipher.DECRYPT_MODE, CIPHER_KEY);

		// Decrypt message
		decryptedBytes = cipher.doFinal(message);

		return decryptedBytes;
	}

}
