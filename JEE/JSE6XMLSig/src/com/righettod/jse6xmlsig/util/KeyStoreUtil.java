package com.righettod.jse6xmlsig.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * Class provding helper for KeyStore data access
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public abstract class KeyStoreUtil {

	/**
	 * Extract public and private key from a keystore
	 * 
	 * @param store
	 *        Keystore location
	 * @param sPass
	 *        Keystore access password
	 * @param kPass
	 *        keypair access password
	 * @param alias
	 *        keypair identification alias in keystore
	 * @return a keypair containing public and private key
	 * @throws CertificateException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair getKeyPair(String store, String sPass, String kPass, String alias) throws CertificateException, IOException, UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException {
		KeyStore ks = loadKeyStore(store, sPass);
		Key key = null;
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		if (ks.containsAlias(alias)) {
			key = ks.getKey(alias, kPass.toCharArray());
			if (key instanceof PrivateKey) {
				Certificate cert = ks.getCertificate(alias);
				publicKey = cert.getPublicKey();
				privateKey = (PrivateKey) key;
				return new KeyPair(publicKey, privateKey);
			}
		}
		return null;
	}

	/**
	 * Load a keystore from a file
	 * 
	 * @param store
	 *        Keystore location
	 * @param sPass
	 *        Keystore access password
	 * @return A keystore object
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	private static KeyStore loadKeyStore(String store, String sPass) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore myKS = KeyStore.getInstance("JKS");
		FileInputStream fis = new FileInputStream(store);
		myKS.load(fis, sPass.toCharArray());
		fis.close();
		return myKS;
	}

}
