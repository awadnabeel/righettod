package com.drighetto.essai.bouncycastle;

import com.drighetto.essai.bouncycastle.signature.CustomSigner;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 *         14 oct. 07<br>
 */
public class Sample03 {

	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 mars 07<br>
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] msg = "Dominiqu".getBytes();
		byte[] signBytes = null;
		CustomSigner customSigner = null;
		/*
		 * Sample : Sign a message and check message signature after with
		 * asymmetric key algorithm
		 */
		System.out.println("---[Bouncy Castle Sample]---");
		try {
			/* Signer Sample */
			System.out.println("[Signer Sample]");
			customSigner = new CustomSigner();
			System.out.printf("Sign message : [%s]\n", new String(msg));
			signBytes = customSigner.signMessage(msg);
			System.out
					.println("Sign message OK, check now signature validty...");
			if (customSigner.isValid(msg, signBytes)) {
				System.out.println("OK - Message signature is valid !");
			} else {
				System.err.println("KO - Message signature is not valid ");
			}
			System.out.println(".:ALL IS OK:.");
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			System.out.println("----------------------------");
		}

	}

}
