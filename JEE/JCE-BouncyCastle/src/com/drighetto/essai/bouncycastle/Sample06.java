package com.drighetto.essai.bouncycastle;

import com.drighetto.essai.bouncycastle.certificate.CertificateGenerator;
import com.drighetto.essai.bouncycastle.certificate.CertificateVO;
import com.drighetto.essai.bouncycastle.certificate.RootCertificateVO;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 */
public class Sample06 {

	/** Password to access to the keystore */
	private static final String KEYSTORE_ACCESS_PASSWORD = "pass1";

	/** Private key identification alias in the keystore */
	private static final String NORMAL_CERTIFICATE_PRIVATEKEY_IDENTIFICATION_ALIAS = "pkAlias";

	/** Password to access to the private key in the keystore */
	private static final String NORMAL_CERTIFICATE_PRIVATEKEY_ACCESS_PASSWORD = "pkAccessPwd";

	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {

		File keystoreTemporaryStorageFile = null;
		FileOutputStream keystoreTemporaryStorageFileOS = null;
		FileInputStream keystoreTemporaryStorageFileIS = null;

		/*
		 * Sample : Generate a root and a normal certificate, store the normal
		 * certificate private key in a keystore and retrieve it using a file on
		 * the filesystem
		 */

		System.out.println("---[Bouncy Castle Sample]---");
		try {
			keystoreTemporaryStorageFile = File.createTempFile("keystore_", ".jks");
			keystoreTemporaryStorageFileOS = new FileOutputStream(keystoreTemporaryStorageFile);
			keystoreTemporaryStorageFileIS = new FileInputStream(keystoreTemporaryStorageFile);

			System.out.println("[Certificate and Keystore Sample]");
			// Install BouncyCastle security provider if it's not already
			// installed
			if (Security.getProperty("BC") == null) {
				Security.addProvider(new BouncyCastleProvider());
			}

			/* Generate a root and a normal certificate for this sample */
			CertificateGenerator certificateGenerator = new CertificateGenerator();
			RootCertificateVO rootCertificateInfo = certificateGenerator.createRootCertificate();
			CertificateVO certificateContainer = certificateGenerator.createNormalizeCertificate2(rootCertificateInfo);

			/*
			 * Create a keystore of type "JKS" using the "SUN" security provider
			 * because BouncyCastle do not support this type of keystore and
			 * then store the normal certificate private key into it
			 */
			System.out.printf("Create the keystore to the temporary file '%s'\n", keystoreTemporaryStorageFile.getAbsolutePath());
			// --Initialize the keystore in order to set the keystore access
			// password
			KeyStore keyStoreForWrite = KeyStore.getInstance("JKS", Security.getProvider("SUN"));
			keyStoreForWrite.load(null, KEYSTORE_ACCESS_PASSWORD.toCharArray());
			// --Create a certificates array representing the normal certificate
			// identification/validation chain
			Certificate[] validationChain = { rootCertificateInfo.getRootCertificate(), certificateContainer.getCertificate() };
			// --Save the normal certificate private key in the keystore
			System.out.printf("Save the normal certificate private key below into it :\n%s\n", certificateContainer.getCertificatePrivateKey());
			keyStoreForWrite.setKeyEntry(NORMAL_CERTIFICATE_PRIVATEKEY_IDENTIFICATION_ALIAS, certificateContainer.getCertificatePrivateKey(), NORMAL_CERTIFICATE_PRIVATEKEY_ACCESS_PASSWORD.toCharArray(), validationChain);
			// --Save the keystore to a temporary file used for this sample
			keyStoreForWrite.store(keystoreTemporaryStorageFileOS, KEYSTORE_ACCESS_PASSWORD.toCharArray());
			System.out.printf("WriteMode -> Keystore security provider : %s - Keystore instance : %s\n\n", keyStoreForWrite.getProvider().getName(), keyStoreForWrite);

			/* Reread the normal certificate private key from the keystore file */
			System.out.printf("Read the keystore from the temporary file '%s'\n", keystoreTemporaryStorageFile.getAbsolutePath());
			// --Load the keystore from the temporary file
			KeyStore keyStoreForRead = KeyStore.getInstance("JKS", Security.getProvider("SUN"));
			keyStoreForRead.load(keystoreTemporaryStorageFileIS, KEYSTORE_ACCESS_PASSWORD.toCharArray());
			// --Get the private key using is alias and is key access
			// password
			PrivateKey pk = (PrivateKey) keyStoreForRead.getKey(NORMAL_CERTIFICATE_PRIVATEKEY_IDENTIFICATION_ALIAS, NORMAL_CERTIFICATE_PRIVATEKEY_ACCESS_PASSWORD.toCharArray());
			// --Display it
			System.out.printf("Normal certificate private key readed from the keystore :\n'%s'\n\n", pk);
			System.out.printf("ReadMode -> Keystore security provider : %s - Keystore instance : %s\n", keyStoreForRead.getProvider().getName(), keyStoreForRead);
			System.out.printf("Private key writed and readed are equals ? : %s\n", certificateContainer.getCertificatePrivateKey().equals(pk));
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			// Release I/O stream
			if (keystoreTemporaryStorageFileOS != null) {
				try {
					keystoreTemporaryStorageFileOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (keystoreTemporaryStorageFileIS != null) {
				try {
					keystoreTemporaryStorageFileIS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (keystoreTemporaryStorageFile != null) {
				keystoreTemporaryStorageFile.delete();
			}
			System.out.println("----------------------------");
		}

	}
}
