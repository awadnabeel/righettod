package com.drighetto.essai.bouncycastle.certificate;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * Root certificate VO
 * 
 * @author Dominique RIGHETTO<br>
 *         20 oct. 07<br>
 */
public class RootCertificateVO {

	/** Root certificate */
	private X509Certificate rootCertificate;

	/** Root certificate private key */
	private PrivateKey rootCertificatePrivateKey;

	/**
	 * Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @param rootCertificate
	 * @param rootCertificatePrivateKey
	 */
	public RootCertificateVO(X509Certificate rootCertificate,
			PrivateKey rootCertificatePrivateKey) {
		super();
		this.rootCertificate = rootCertificate;
		this.rootCertificatePrivateKey = rootCertificatePrivateKey;
	}

	/**
	 * Getter for rootCertificate<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @return the rootCertificate
	 */
	public X509Certificate getRootCertificate() {
		return this.rootCertificate;
	}

	/**
	 * Getter for rootCertificatePrivateKey<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @return the rootCertificatePrivateKey
	 */
	public PrivateKey getRootCertificatePrivateKey() {
		return this.rootCertificatePrivateKey;
	}

}
