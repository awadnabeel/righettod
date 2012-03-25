package com.righettod.security.test;

import com.righettod.security.annotation.CheckContent;
import com.righettod.security.annotation.NoTag;

/**
 * Simple bean to test annotation
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SimpleBean {

	/** only-alpha */
	@CheckContent(whitelistIdentifier = "only-alpha")
	private String data1 = null;

	/** no-special-character */
	@CheckContent(whitelistIdentifier = "no-special-character")
	private String data2 = null;

	/** check on character repetition only */
	@CheckContent(continuousRepetitionLimitationMapJsonExpr = "{\"(\":1,\"-\":1,\".\":3,\"'\":1}")
	private String data3 = null;

	/** with-special-character + check on character repetition only */
	@CheckContent(whitelistIdentifier = "with-special-character", continuousRepetitionLimitationMapJsonExpr = "{\"(\":1,\"-\":1,\".\":3,\"'\":1}")
	private String data4 = null;

	/** only-number with locale specified (bundle for this locale exists) */
	@CheckContent(whitelistIdentifier = "only-number", whitelistLocale = "lu")
	private String data5 = null;

	/** only-number with locale specified (bundle for this locale do not exists) */
	@CheckContent(whitelistIdentifier = "only-number", whitelistLocale = "de")
	private String data6 = null;

	/** noTag */
	@NoTag
	private String data7 = null;

	/**
	 * Getter
	 * 
	 * @return the data6
	 */
	public String getData6() {
		return this.data6;
	}

	/**
	 * Setter : only-number with locale specified (bundle for this locale do not exists)
	 * 
	 * @param data6
	 *        the data6 to set
	 */
	public void setData6(String data6) {
		this.data6 = data6;
	}

	/**
	 * Getter
	 * 
	 * @return the data5
	 */
	public String getData5() {
		return this.data5;
	}

	/**
	 * Setter : only-number with locale specified (bundle for this locale exists)
	 * 
	 * @param data5
	 *        the data5 to set
	 */
	public void setData5(String data5) {
		this.data5 = data5;
	}

	/**
	 * Getter
	 * 
	 * @return the data1
	 */
	public String getData1() {
		return this.data1;
	}

	/**
	 * Setter : only-alpha
	 * 
	 * @param data1
	 *        the data1 to set
	 */
	public void setData1(String data1) {
		this.data1 = data1;
	}

	/**
	 * Getter
	 * 
	 * @return the data2
	 */
	public String getData2() {
		return this.data2;
	}

	/**
	 * Setter : no-special-character
	 * 
	 * @param data2
	 *        the data2 to set
	 */
	public void setData2(String data2) {
		this.data2 = data2;
	}

	/**
	 * Getter
	 * 
	 * @return the data3
	 */
	public String getData3() {
		return this.data3;
	}

	/**
	 * Setter : check on character repetition only
	 * 
	 * @param data3
	 *        the data3 to set
	 */
	public void setData3(String data3) {
		this.data3 = data3;
	}

	/**
	 * Getter
	 * 
	 * @return the data4
	 */
	public String getData4() {
		return this.data4;
	}

	/**
	 * Setter : with-special-character + check on character repetition only
	 * 
	 * @param data4
	 *        the data4 to set
	 */
	public void setData4(String data4) {
		this.data4 = data4;
	}

	/**
	 * Getter
	 * 
	 * @return the data7
	 */
	public String getData7() {
		return this.data7;
	}

	/**
	 * Setter : No Tag
	 * 
	 * @param data7
	 *        the data7 to set
	 */
	public void setData7(String data7) {
		this.data7 = data7;
	}

}
