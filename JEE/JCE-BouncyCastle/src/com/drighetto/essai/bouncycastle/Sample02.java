package com.drighetto.essai.bouncycastle;

import com.drighetto.essai.bouncycastle.cipher.AsymmetricKeyCipher;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 *         11 mars 07<br>
 */
public class Sample02 {

	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 mars 07<br>
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] msg = "Dominiqu".getBytes();
		byte[] tempEncrypt = null;
		byte[] tempDecrypt = null;
		AsymmetricKeyCipher asymmetricKeyCipher = null;
		String tmpStr = null;
		/*
		 * Sample : Encrypt and decrypt a message with asymmetric key algorithm
		 * (check equality before and after)
		 */
		System.out.println("---[Bouncy Castle Sample]---");
		try {
			/* Cipher Sample */
			System.out.println("[Cipher Sample]");
			asymmetricKeyCipher = new AsymmetricKeyCipher();
			System.out.println("**Sample 1 : Encryption**");
			System.out.println("Original message : [" + new String(msg) + "]");
			System.out.println("Size             : [" + new String(msg).length() + "]");
			tempEncrypt = asymmetricKeyCipher.encrypt(msg);
			System.out.println("**Sample 2 : Decryption**");
			tempDecrypt = asymmetricKeyCipher.decrypt(tempEncrypt);
			tmpStr = new String(tempDecrypt);
			System.out.println("message : [" + tmpStr + "]");
			System.out.println("Size    : [" + tmpStr.length() + "]");
			if (new String(msg).equals(tmpStr)) {
				System.out.println("OK - Message is equal before and after encryption/decryption !");
			} else {
				System.err.println("KO - Message isn't equal before and after encryption/decryption !");
			}
			System.out.println("\n--");
			System.out.println(".:ALL IS OK:.");
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			System.out.println("----------------------------");
		}

	}

}
