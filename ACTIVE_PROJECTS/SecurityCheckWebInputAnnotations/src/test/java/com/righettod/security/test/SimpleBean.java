package com.righettod.security.test;

import com.righettod.security.annotation.CheckContent;

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

}
