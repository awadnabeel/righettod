package com.drighetto.essai.bouncycastle;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 */
public class Sample05 {

	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		FileInputStream certFis = null;

		/*
		 * Sample : Load a certificate from a file and display is public key
		 * length
		 */
		System.out.println("---[Bouncy Castle Sample]---");
		try {
			System.out.println("[Certificate Sample]");
			// Install BouncyCastle security provider if it's not already
			// installed
			if (Security.getProperty("BC") == null) {
				Security.addProvider(new BouncyCastleProvider());
			}
			// Load the certificate from a file
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", Security.getProvider("BC"));
			File certFile = new File("NormalizeCertificateTest.cer");
			certFis = new FileInputStream(certFile);
			X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(certFis);
			System.out.printf("Certificate [DN=%s] loaded from  file [%s]\n", certificate.getSubjectDN().getName(), certFile.getAbsolutePath());
			System.out.printf("Certificate informations :\n%s\n", certificate.toString());
			// Get public key and get is size
			RSAKey pKey = (RSAKey) certificate.getPublicKey();
			System.out.printf("Certificate [DN=%s] public key have a size of %s Bits \n", certificate.getSubjectDN().getName(), pKey.getModulus().bitLength());
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			// Release stream
			if (certFis != null) {
				try {
					certFis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("----------------------------");
		}

	}

}
