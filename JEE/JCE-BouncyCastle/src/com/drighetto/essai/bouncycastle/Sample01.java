package com.drighetto.essai.bouncycastle;

import com.drighetto.essai.bouncycastle.asn1.Token;
import com.drighetto.essai.bouncycastle.cipher.SymmetricKeyCipher;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.Date;

/**
 * Launch Class
 * 
 * @author Dominique RIGHETTO<br>
 *         11 mars 07<br>
 */
public class Sample01 {

	/**
	 * Sample Entry point
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 mars 07<br>
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		Token token1 = null;
		Token token2 = null;
		byte[] userId = "Dominique_Dominique".getBytes();
		byte[] tempEncrypt = null;
		byte[] tempDecrypt = null;
		byte[] msgToCrypt = null;
		byte[] tempEncodedBase64 = null;
		byte[] tempDecodedBase64 = null;
		byte[] tempBytes = null;
		byte[] myBytes = new byte[15];
		Date creationDate = new Date();
		ASN1Sequence sequenceASN1 = null;
		ASN1InputStream inASN1 = null;
		SymmetricKeyCipher symmetricKeyCipher = null;
		int i = 0;
		int myInt = 30;

		/*
		 * Sample : Creating a ASN1 structure, Encrypt and decrypt it with
		 * symmetric key algorithm (check equality before and after), Encode and
		 * decode it in Base64 (check equality before and after).
		 */
		System.out.println("---[Bouncy Castle Sample]---");
		try {
			/* ASN1 Sample */
			myBytes[0] = 1;
			myBytes[1] = 2;
			myBytes[2] = 4;
			myBytes[8] = -3;
			System.out.println("[ASN1 Sample]");
			System.out
					.println("**Sample 1 : Conctructor with direct java object**");
			token1 = new Token(userId, myBytes, myInt, creationDate);
			inASN1 = new ASN1InputStream(token1.getEncoded());
			System.out.println("DumpAsString on Java Object");
			System.out.println(ASN1Dump.dumpAsString(token1));
			System.out.println("DumpAsString on Object from ASN1InputStream");
			System.out.println(ASN1Dump.dumpAsString(inASN1.readObject()));
			// -
			System.out.println("**Sample 2 : Conctructor with ASN1 sequence**");
			sequenceASN1 = ASN1Sequence.getInstance(token1.toASN1Object());
			token2 = new Token(sequenceASN1);
			inASN1 = new ASN1InputStream(token2.getEncoded());
			System.out.println("DumpAsString on Java Object");
			System.out.println(ASN1Dump.dumpAsString(token2));
			System.out.println("DumpAsString on Object from ASN1InputStream");
			System.out.println(ASN1Dump.dumpAsString(inASN1.readObject()));
			System.out.println("--");
			System.out.println("------");
			System.out.println("--\n");
			/* $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ */
			/* Cipher Sample */
			System.out.println("[Cipher Sample]");
			symmetricKeyCipher = new SymmetricKeyCipher();
			token1 = new Token(userId, myBytes, myInt, creationDate);
			msgToCrypt = token1.getEncoded();
			System.out.println("Token As String before encryption : ");
			System.out.println(ASN1Dump.dumpAsString(token1));
			System.out.println("**Sample 1 : Encryption**");
			System.out.println("Original bytes : ");
			System.out.print("[");
			for (i = 0; i < msgToCrypt.length; i++) {
				System.out.print(msgToCrypt[i]);
				System.out.print(";");
			}
			System.out.println("]");
			tempEncrypt = symmetricKeyCipher.encrypt(msgToCrypt);
			System.out.println("Encrypted bytes : ");
			System.out.print("[");
			for (i = 0; i < tempEncrypt.length; i++) {
				System.out.print(tempEncrypt[i]);
				System.out.print(";");
			}
			System.out.println("]");
			System.out.println("Encrypted bytes in HEX :");
			System.out.println(new String(Hex.encode(tempEncrypt)));
			System.out.println("**Sample 2 : Decryption**");
			tempDecrypt = symmetricKeyCipher.decrypt(tempEncrypt);
			System.out.println("Decrypted bytes : ");
			System.out.print("[");
			for (i = 0; i < tempDecrypt.length; i++) {
				System.out.print(tempDecrypt[i]);
				System.out.print(";");
			}
			System.out.println("]\n");
			System.out.println("Token As String after decryption : ");
			sequenceASN1 = ASN1Sequence.getInstance(ASN1Object
					.fromByteArray(tempDecrypt));
			token2 = new Token(sequenceASN1);
			System.out.println(ASN1Dump.dumpAsString(token2));
			if (ASN1Dump.dumpAsString(token1).equals(
					ASN1Dump.dumpAsString(token2))) {
				System.out
						.println("OK - Token is equal before and after encryption/decryption !");
			} else {
				System.err
						.println("KO - Token isn't equal before and after encryption/decryption !");
			}
			System.out.println("\n--");
			System.out.println("------");
			System.out.println("--\n");
			/* $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ */
			/* Base64 Sample */
			System.out.println("[Base64 Sample]");
			tempBytes = token2.getEncoded();
			System.out.println("Original bytes : ");
			System.out.print("[");
			for (i = 0; i < tempBytes.length; i++) {
				System.out.print(tempBytes[i]);
				System.out.print(";");
			}
			System.out.println("]");
			tempEncodedBase64 = Base64.encode(tempBytes);
			System.out.println("Base 64 encoded bytes : ");
			System.out.print("[");
			for (i = 0; i < tempEncodedBase64.length; i++) {
				System.out.print(tempEncodedBase64[i]);
				System.out.print(";");
			}
			System.out.println("]");
			tempDecodedBase64 = Base64.decode(tempEncodedBase64);
			System.out.println("Base 64 decoded bytes : ");
			System.out.print("[");
			for (i = 0; i < tempDecodedBase64.length; i++) {
				System.out.print(tempDecodedBase64[i]);
				System.out.print(";");
			}
			System.out.println("]");
			System.out.println("Token As String after decoding : ");
			sequenceASN1 = ASN1Sequence.getInstance(ASN1Object
					.fromByteArray(tempDecodedBase64));
			token2 = new Token(sequenceASN1);
			System.out.println(ASN1Dump.dumpAsString(token2));
			System.out.println("\n--");
			System.out.println("------");
			System.out.println("--\n");
			/* $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ */
			/* DER Objects Access */
			System.out.printf("USER ID          : {%s}\n", new String(token2
					.getUserId().getOctets()));
			System.out.printf("DER APP SPEC[%s] : {", token2.getDerAppSpec()
					.getContents().length);
			for (byte b : token2.getDerAppSpec().getContents()) {
				System.out.printf(" %s ", b);
			}
			System.out.println("}");
			System.out.printf("DER INTEGER      : {%s}\n", token2
					.getDerInteger().getValue());
			System.out.printf("CREATION DATE    : {%s}\n", token2
					.getCreationDate().getDate());
			System.out.println("\n\n");
			System.out.println(".:ALL IS OK:.");
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			System.out.println("----------------------------");
		}

	}

}
