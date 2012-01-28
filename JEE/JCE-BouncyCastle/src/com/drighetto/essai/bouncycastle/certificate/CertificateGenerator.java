package com.drighetto.essai.bouncycastle.certificate;

import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V1CertificateGenerator;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;

import javax.security.auth.x500.X500Principal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * Simple certificate (root & normalize)
 * 
 * @author Dominique RIGHETTO<br>
 *         20 oct. 07<br>
 */
public class CertificateGenerator {

	/**
	 * Algorithm used
	 */
	private static final String ALGORITHM = "RSA";

	/** Security Provider */
	private static final String PROVIDER = "BC";

	/** Signature algorithm used to sign certificate */
	private static final String SIGNATURE_ALGORITHM = "SHA256WithRSAEncryption";

	/**
	 * Encryption strengh, 128 bits is allowed without security patch
	 * application, higher strengh require security patch application !
	 */
	private static final int KEY_SIZE = 512;

	/** Key pair generator */
	private static KeyPairGenerator KEY_PAIR_GENERATOR;

	// Static initialization
	static {
		try {
			// Add BouncyCastle Provider
			Security.addProvider(new BouncyCastleProvider());
			// Generate public and private keys
			// ---Create a KeyPair generator
			KEY_PAIR_GENERATOR = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
			KEY_PAIR_GENERATOR.initialize(KEY_SIZE);
		} catch (NoSuchProviderException nspe) {
			nspe.printStackTrace();
			System.exit(1);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			System.exit(2);
		}
	}

	/**
	 * Method to generate a ROOT certificate
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * 
	 * @return a Root X509Certificate VO
	 * 
	 * @throws InvalidKeyException
	 * @throws NoSuchProviderException
	 * @throws SecurityException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalStateException
	 * @throws CertificateEncodingException
	 */
	public RootCertificateVO createRootCertificate() throws InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException, CertificateEncodingException, IllegalStateException, NoSuchAlgorithmException {

		X509V1CertificateGenerator certGen = new X509V1CertificateGenerator();
		KeyPair keyPair = KEY_PAIR_GENERATOR.generateKeyPair();

		// Fill certificate informations...
		certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
		certGen.setIssuerDN(new X500Principal("CN=Test Root Certificate Issuer DN"));
		certGen.setNotBefore(new Date(System.currentTimeMillis() - 50000));
		certGen.setNotAfter(new Date(System.currentTimeMillis() + 50000));
		certGen.setSubjectDN(new X500Principal("CN=Test Root Certificate subject DN"));
		certGen.setPublicKey(keyPair.getPublic());
		certGen.setSignatureAlgorithm(SIGNATURE_ALGORITHM);

		// Generate certificate...
		return new RootCertificateVO(certGen.generate(keyPair.getPrivate(), PROVIDER), keyPair.getPrivate());
	}

	/**
	 * Method to generate a NORMALIZE certificate
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @param rootCertificateInfo CA certificate (ROOT certificate)
	 * 
	 * @return a Normalize X509Certificate
	 * 
	 * @throws CertificateParsingException
	 * @throws CertificateEncodingException
	 * @throws InvalidKeyException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 */
	public X509Certificate createNormalizeCertificate(RootCertificateVO rootCertificateInfo) throws CertificateParsingException, CertificateEncodingException, InvalidKeyException, IllegalStateException, NoSuchProviderException,
			NoSuchAlgorithmException, SignatureException {
		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
		KeyPair keyPair = KEY_PAIR_GENERATOR.generateKeyPair();

		// Fill certificate informations...
		certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
		certGen.setIssuerDN(rootCertificateInfo.getRootCertificate().getSubjectX500Principal());
		certGen.setNotBefore(new Date(System.currentTimeMillis()));
		certGen.setNotAfter(new Date(System.currentTimeMillis() + 50000));
		certGen.setSubjectDN(new X500Principal("CN=Test Normalize Certificate subject DN"));
		certGen.setPublicKey(keyPair.getPublic());
		certGen.setSignatureAlgorithm(SIGNATURE_ALGORITHM);

		// Add CA authority key identifier extension information
		certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false, new AuthorityKeyIdentifierStructure(rootCertificateInfo.getRootCertificate()));

		// Add subject key identifier
		certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false, new SubjectKeyIdentifierStructure(keyPair.getPublic()));

		// Add key usage (usage of the certificate)
		certGen.addExtension(X509Extensions.KeyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.dataEncipherment));

		// Generate certificate and sign it with the ROOT certificate private
		// key...
		return certGen.generate(rootCertificateInfo.getRootCertificatePrivateKey(), PROVIDER);

	}

	/**
	 * Method to generate a NORMALIZE certificate
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @param rootCertificateInfo CA certificate (ROOT certificate)
	 * 
	 * @return a certificate custom container storing the certificate and is
	 *         private key
	 * 
	 * @throws CertificateParsingException
	 * @throws CertificateEncodingException
	 * @throws InvalidKeyException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 */
	public CertificateVO createNormalizeCertificate2(RootCertificateVO rootCertificateInfo) throws CertificateParsingException, CertificateEncodingException, InvalidKeyException, IllegalStateException, NoSuchProviderException,
			NoSuchAlgorithmException, SignatureException {

		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
		KeyPair keyPair = KEY_PAIR_GENERATOR.generateKeyPair();

		// Fill certificate informations...
		certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
		certGen.setIssuerDN(rootCertificateInfo.getRootCertificate().getSubjectX500Principal());
		certGen.setNotBefore(new Date(System.currentTimeMillis()));
		certGen.setNotAfter(new Date(System.currentTimeMillis() + 50000));
		certGen.setSubjectDN(new X500Principal("CN=Test Normalize Certificate subject DN"));
		certGen.setPublicKey(keyPair.getPublic());
		certGen.setSignatureAlgorithm(SIGNATURE_ALGORITHM);

		// Add CA authority key identifier extension information
		certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false, new AuthorityKeyIdentifierStructure(rootCertificateInfo.getRootCertificate()));

		// Add subject key identifier
		certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false, new SubjectKeyIdentifierStructure(keyPair.getPublic()));

		// Add key usage (usage of the certificate)
		certGen.addExtension(X509Extensions.KeyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.dataEncipherment));

		// Generate certificate and sign it with the ROOT certificate private
		// key...
		X509Certificate cert = certGen.generate(rootCertificateInfo.getRootCertificatePrivateKey(), PROVIDER);
		// Return a VO containing the normal certificate and is private key
		return new CertificateVO(cert, keyPair.getPrivate());

	}

	/**
	 * Write certificate to a file
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @param certificate
	 * @param filePath
	 * @throws CertificateEncodingException
	 * @throws IOException
	 */
	public void writeToFile(X509Certificate certificate, String filePath) throws CertificateEncodingException, IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(certificate.getEncoded());
		fos.flush();
		fos.close();
	}

}
