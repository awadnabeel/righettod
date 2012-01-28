package com.drighetto.essai.bouncycastle.signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Implementation of a signer and a signature checker
 * 
 * @author Dominique RIGHETTO<br>
 *         14 oct. 07<br>
 * 
 */
public class CustomSigner {

	/**
	 * Algorithm used
	 */
	private static final String ALGORITHM = "DSA";

	/** Security Provider */
	private static final String PROVIDER = "BC";

	/**
	 * Encryption strengh, 128 bits is allowed without security patch
	 * application, higher strengh require security patch application !
	 */
	private static final int KEY_SIZE = 512;

	/** Key Pair */
	private static KeyPair KEY_PAIR = null;

	/** Signature object */
	private static Signature SIGNATURE = null;

	// Static initialization
	static {
		try {
			// Add BouncyCastle Provider
			Security.addProvider(new BouncyCastleProvider());
			// Generate public and private keys
			// ---Create a KeyPair generator
			KeyPairGenerator generator = KeyPairGenerator.getInstance(
					ALGORITHM, PROVIDER);
			generator.initialize(KEY_SIZE);
			// ---Create keys and signature
			KEY_PAIR = generator.generateKeyPair();
			SIGNATURE = Signature.getInstance(ALGORITHM, PROVIDER);
		} catch (NoSuchProviderException nspe) {
			nspe.printStackTrace();
			System.exit(1);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			System.exit(2);
		}
	}

	/**
	 * Method to sign a message
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         14 oct. 07<br>
	 * @param message
	 * @return the message signature (checksum)
	 * @throws SignatureException
	 * @throws InvalidKeyException
	 */
	public byte[] signMessage(byte[] message) throws SignatureException,
			InvalidKeyException {
		// Generate a signature
		SIGNATURE.initSign(KEY_PAIR.getPrivate());
		// Set message to sign
		SIGNATURE.update(message);
		// Sign message
		return SIGNATURE.sign();

	}

	/**
	 * Method to check the signature of a message
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         14 oct. 07<br>
	 * @param message
	 * @param signatureBytes
	 * @return a flag indicating the signature validity
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public boolean isValid(byte[] message, byte[] signatureBytes)
			throws InvalidKeyException, SignatureException {
		// Initialize verification
		SIGNATURE.initVerify(KEY_PAIR.getPublic());
		// Set message to check
		SIGNATURE.update(message);
		// Perform signature check
		return SIGNATURE.verify(signatureBytes);
	}
}
