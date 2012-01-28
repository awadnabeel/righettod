package com.drighetto.essai.bouncycastle.keystore;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * KeyStore I/O actions
 * 
 * @author Dominique RIGHETTO<br>
 *         21 oct. 07<br>
 */
public class KeyStoreUtil {

	/** KeyStore type */
	private static final String KEY_STORE_TYPE = "JKS";

	/** KeyStore password */
	private static final char[] KEY_STORE_PASSWORD = "JKES-PASSWORD"
			.toCharArray();

	/** KeyStore store file path */
	private static final String KEY_STORE_FILE_PATH = "KeyStoreTest.jks";

	/** KeyStore object */
	private static KeyStore KEY_STORE;

	// Static initialization
	static {
		try {
			// Add BouncyCastle Provider
			Security.addProvider(new BouncyCastleProvider());
			// Create keystore instance
			KEY_STORE = KeyStore.getInstance(KEY_STORE_TYPE);
			// Init the key store
			KEY_STORE.load(null, KEY_STORE_PASSWORD);
		} catch (KeyStoreException kse) {
			kse.printStackTrace();
			System.exit(1);
		} catch (NoSuchAlgorithmException nse) {
			nse.printStackTrace();
			System.exit(2);
		} catch (CertificateException ce) {
			ce.printStackTrace();
			System.exit(3);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(4);
		}
	}

	/**
	 * Method to load and return the KeyStore instance
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         21 oct. 07<br>
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public void loadKeyStore() throws NoSuchAlgorithmException,
			CertificateException, IOException {
		FileInputStream fis = new FileInputStream(KEY_STORE_FILE_PATH);
		KEY_STORE.load(fis, KEY_STORE_PASSWORD);
		fis.close();
	}

	/**
	 * Method to store the KeyStore
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         21 oct. 07<br>
	 * @param certificates
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public void saveKeyStore(List<X509Certificate> certificates)
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException {
		for (X509Certificate cert : certificates) {
			KEY_STORE.setCertificateEntry(cert.getSubjectDN().getName(), cert);
		}
		FileOutputStream fos = new FileOutputStream(KEY_STORE_FILE_PATH);
		KEY_STORE.store(fos, KEY_STORE_PASSWORD);
		fos.close();
	}

	/**
	 * Getter for KEY_STORE<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         21 oct. 07<br>
	 * @return the kEY_STORE
	 */
	public static KeyStore getKeyStore() {
		return KEY_STORE;
	}

}
