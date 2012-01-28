package com.drighetto.essai.bouncycastle.asn1;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DERGeneralizedTime;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTags;

import java.util.Date;

/**
 * Implementation of an simple ASN1 structure <br>
 * 
 * <pre>
 * Token ::= SEQUENCE {
 *                   userId ASN1OctetString,
 *                   derAppSpec DERApplicationSpecific,
 *                   derInteger DERInteger,
 *                   creationDate DERGeneralizedTime}
 * &lt;pre&gt;
 * 
 * &#064;author Dominique RIGHETTO
 * <br>
 * 11 mars 07
 * <br>
 */
public class Token extends ASN1Encodable {

	/** User ID */
	private ASN1OctetString userId;

	/** Simple DER ApplicationSpecific */
	private DERApplicationSpecific derAppSpec;

	/** Simple DER Integer */
	private DERInteger derInteger;

	/** Creation date of token */
	private DERGeneralizedTime creationDate;

	/**
	 * 
	 * Constructor with an ASN1 sequence in input
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 mars 07<br>
	 * @param sequence
	 *            ASN1 Sequence
	 */
	public Token(ASN1Sequence sequence) {
		this.userId = (ASN1OctetString) sequence.getObjectAt(0);
		this.creationDate = (DERGeneralizedTime) sequence.getObjectAt(1);
		this.derAppSpec = (DERApplicationSpecific) sequence.getObjectAt(2);
		this.derInteger = (DERInteger) sequence.getObjectAt(3);
	}

	/**
	 * 
	 * Constructor with direct java object in input
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         11 mars 07<br>
	 * @param userId
	 * @param creationDate
	 */
	public Token(byte[] userId, Date creationDate) {
		this.userId = new DEROctetString(userId);
		this.creationDate = new DERGeneralizedTime(creationDate);
	}

	/**
	 * Another constructor with direct java object in input
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         3 juil. 07<br>
	 * @param userId
	 * @param derAppSpec
	 * @param derInteger
	 * @param creationDate
	 */
	public Token(byte[] userId, byte[] derAppSpec, int derInteger,
			Date creationDate) {
		super();
		this.userId = new DEROctetString(userId);
		this.derAppSpec = new DERApplicationSpecific(DERTags.INTEGER,
				derAppSpec);
		this.derInteger = new DERInteger(derInteger);
		this.creationDate = new DERGeneralizedTime(creationDate);
	}

	/**
	 * @see org.bouncycastle.asn1.ASN1Encodable#toASN1Object() <br>
	 *      Produce an object suitable for writing to an ASN1/DEROutputStream<br>
	 *      Create our ASN1 sequence explained in comment header of this class
	 */
	@Override
	public DERObject toASN1Object() {
		ASN1EncodableVector encodableVector = new ASN1EncodableVector();

		encodableVector.add(this.userId);
		encodableVector.add(this.creationDate);
		encodableVector.add(this.derAppSpec);
		encodableVector.add(this.derInteger);

		return new DERSequence(encodableVector);
	}

	/**
	 * Getter for creationDate<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         3 juil. 07<br>
	 * @return the creationDate
	 */
	public DERGeneralizedTime getCreationDate() {
		return this.creationDate;
	}

	/**
	 * Getter for derAppSpec<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         3 juil. 07<br>
	 * @return the derAppSpec
	 */
	public DERApplicationSpecific getDerAppSpec() {
		return this.derAppSpec;
	}

	/**
	 * Getter for derInteger<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         3 juil. 07<br>
	 * @return the derInteger
	 */
	public DERInteger getDerInteger() {
		return this.derInteger;
	}

	/**
	 * Getter for userId<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         3 juil. 07<br>
	 * @return the userId
	 */
	public ASN1OctetString getUserId() {
		return this.userId;
	}

}
