package com.drighetto.essai.bouncycastle;

import com.drighetto.essai.bouncycastle.cipher.AsymmetricKeyLongTextCipher;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 */
public class Sample07 {
	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Sample : Encrypt and decrypt a long message (with a total size
		 * superior to the key size) with asymmetric key algorithm (check
		 * equality before and after)
		 */

		System.out.println("---[Bouncy Castle Sample]---");
		try {
			/* Cipher Sample */
			System.out.println("[Cipher Sample]");
			AsymmetricKeyLongTextCipher asymmetricKeyCipher = new AsymmetricKeyLongTextCipher();
			String longText = RandomStringUtils.randomAlphanumeric(5000);
			System.out.println("**Sample 1 : Encryption**");
			System.out.println("Key size         : [" + asymmetricKeyCipher.getKeySize() + "]");
			System.out.println("Original message : [" + longText + "]");
			System.out.println("Size             : [" + longText.length() + "]");
			byte[] tempEncrypt = asymmetricKeyCipher.encrypt(longText.getBytes());
			System.out.println("**Sample 2 : Decryption**");
			byte[] tempDecrypt = asymmetricKeyCipher.decrypt(tempEncrypt);
			String tmpStr = new String(tempDecrypt);
			System.out.println("message          : [" + tmpStr + "]");
			System.out.println("Size             : [" + tmpStr.length() + "]");
			if (longText.equals(tmpStr)) {
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
