package com.drighetto.essai.bouncycastle.cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Implementation of encryption/decryption using JCE and asymmetric key to
 * cipher (encrypt) and decipher (decrypt) a short text with a size inferior to
 * the key size.
 * 
 * In case of a long text (with a total size superior to the key size) see the
 * sample in the class "AsymmetricKeyLongTextCipher" in the same package !
 * 
 * @author Dominique RIGHETTO<br>
 *         13 oct. 07<br>
 */
public class AsymmetricKeyCipher {

	/**
	 * Algorithm/Mode/Padding used : Use of RSA limit message to a size of 8
	 */
	private static final String ALGORITHM = "RSA/None/NoPadding";

	/** Security Provider */
	private static final String PROVIDER = "BC";

	/**
	 * Encryption strengh
	 */
	private static final int KEY_SIZE = 128;

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
	public AsymmetricKeyCipher() {
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
	public byte[] encrypt(byte[] message) throws InvalidKeyException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		byte[] encryptedBytes = null;
		Cipher cipher = null;

		// Get Cipher instance
		cipher = Cipher.getInstance(ALGORITHM, PROVIDER);

		// Initialize cipher for encryption
		cipher.init(Cipher.ENCRYPT_MODE, PRIVATE_KEY);

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
	public byte[] decrypt(byte[] message) throws InvalidKeyException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		byte[] decryptedBytes = null;
		Cipher cipher = null;

		// Get Cipher instance
		cipher = Cipher.getInstance(ALGORITHM, PROVIDER);

		// Initialize cipher for encryption
		cipher.init(Cipher.DECRYPT_MODE, PUBLIC_KEY);

		// Decrypt message
		decryptedBytes = cipher.doFinal(message);

		return decryptedBytes;
	}

}
