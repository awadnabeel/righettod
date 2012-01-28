package com.drighetto.essai.bouncycastle.certificate;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * Certificate VO
 * 
 * @author Dominique RIGHETTO<br>
 *         20 oct. 07<br>
 */
public class CertificateVO {

	/** Certificate */
	private X509Certificate certificate;

	/** Certificate private key */
	private PrivateKey certificatePrivateKey;

	/**
	 * Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @param certificate
	 * @param certificatePrivateKey
	 */
	public CertificateVO(X509Certificate certificate, PrivateKey certificatePrivateKey) {
		super();
		this.certificate = certificate;
		this.certificatePrivateKey = certificatePrivateKey;
	}

	/**
	 * Getter for certificate<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @return the certificate
	 */
	public X509Certificate getCertificate() {
		return this.certificate;
	}

	/**
	 * Getter for certificatePrivateKey<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         20 oct. 07<br>
	 * @return the certificatePrivateKey
	 */
	public PrivateKey getCertificatePrivateKey() {
		return this.certificatePrivateKey;
	}

}
