package com.drighetto.lmb.persistence.type;

/**
 * Rides for Howald Tunnel
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public enum Rides {
	/** Bertrange > Howald */
	BERTRANGE_HOWALD("A6 > A1"),
	/** Irrgarten > Howald */
	IRRGARTEN_HOWALD("A1 > A6"),
	/** Berchem > Howald */
	BERCHEM_HOWALD("A3 > A1");

	/** Driveways used for the ride */
	private String driveWayCode = null;

	/**
	 * Constructor
	 * 
	 * @param driveWayCode
	 *        Code
	 */
	private Rides(String driveWayCode) {
		this.driveWayCode = driveWayCode;
	}

	/**
	 * Getter
	 * 
	 * @return the driveWayCode
	 */
	public String getDriveWayCode() {
		return this.driveWayCode;
	}

}
