package com.drighetto.essai.bouncycastle;

import com.drighetto.essai.bouncycastle.certificate.CertificateGenerator;
import com.drighetto.essai.bouncycastle.certificate.RootCertificateVO;
import com.drighetto.essai.bouncycastle.keystore.KeyStoreUtil;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 *         20 oct. 07<br>
 */
public class Sample04 {

	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 mars 07<br>
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		CertificateGenerator certGenerator = new CertificateGenerator();
		KeyStoreUtil keyStoreUtil = new KeyStoreUtil();

		/*
		 * Sample : Create a ROOT and a NORMALIZE certificates and display their
		 * informations
		 */
		System.out.println("---[Bouncy Castle Sample]---");
		try {
			System.out.println("[Certificate Sample]");
			// Create the ROOT certificate
			System.out.print("Generate a ROOT certificate...");
			RootCertificateVO rootCertificateVO = certGenerator
					.createRootCertificate();
			X509Certificate rootCertificate = rootCertificateVO
					.getRootCertificate();
			System.out.println("OK");
			System.out.println("Certificate content :");
			System.out.println(rootCertificate.toString());
			System.out.print("Certificate is currently valid : ");
			try {
				rootCertificate.checkValidity();
				System.out.println("YES");
			} catch (CertificateExpiredException cee) {
				System.out.printf("NO : %s\n", cee.getMessage());
			} catch (CertificateNotYetValidException cnye) {
				System.out.printf("NO : %s\n", cnye.getMessage());
			}

			System.out.println("--");
			System.out.println("------");
			System.out.println("--");

			// Create the NORMALIZE certificate
			System.out.print("Generate a NORMALIZE certificate...");
			X509Certificate normalizeCertificate = certGenerator
					.createNormalizeCertificate(rootCertificateVO);
			System.out.println("OK");
			System.out.println("Certificate content :");
			System.out.println(normalizeCertificate.toString());
			System.out.print("Certificate is currently valid : ");
			try {
				normalizeCertificate.checkValidity();
				System.out.println("YES");
			} catch (CertificateExpiredException cee) {
				System.out.printf("NO : %s\n", cee.getMessage());
			} catch (CertificateNotYetValidException cnye) {
				System.out.printf("NO : %s\n", cnye.getMessage());
			}

			System.out.println("--");
			System.out.println("------");
			System.out.println("--");

			// Write certificates objects to files
			System.out.print("Write ROOT certificate object to a file...");
			certGenerator.writeToFile(rootCertificate,
					"RootCertificateTest.cer");
			System.out.println("OK");
			System.out.print("Write NORMALIZE certificate object to a file...");
			certGenerator.writeToFile(normalizeCertificate,
					"NormalizeCertificateTest.cer");
			System.out.println("OK");

			System.out.println("--");
			System.out.println("------");
			System.out.println("--");

			// Store certificates to key store
			System.out.printf(
					"Store certificates to key store (KS Size : %s)\n",
					KeyStoreUtil.getKeyStore().size());
			List<X509Certificate> certificates = new ArrayList<X509Certificate>();
			certificates.add(rootCertificate);
			certificates.add(normalizeCertificate);
			System.out.print("Storing...");
			keyStoreUtil.saveKeyStore(certificates);
			System.out.printf("OK (KS Size : %s)\n", KeyStoreUtil.getKeyStore()
					.size());
			System.out.print("Loading...");
			keyStoreUtil.loadKeyStore();
			System.out.printf("OK (KS Size : %s)\n", KeyStoreUtil.getKeyStore()
					.size());
			System.out.printf("KS Type    : %s\n", KeyStoreUtil.getKeyStore()
					.getType());
			System.out.println("KS Content : ");
			Enumeration<String> ksAliases = KeyStoreUtil.getKeyStore()
					.aliases();
			int i = 0;
			while (ksAliases.hasMoreElements()) {
				X509Certificate currentCert = (X509Certificate) KeyStoreUtil
						.getKeyStore().getCertificate(ksAliases.nextElement());
				System.out.printf("-> Certificate[%s] : %s\n", i, currentCert
						.getSubjectDN().getName());
				i++;
			}

			System.out.println("\n\n.:ALL IS OK:.");

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			System.out.println("----------------------------");
		}

	}

}
