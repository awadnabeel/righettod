package com.cychop.til.enums;

import com.cychop.til.exceptions.DataValueException;

/**
 * This enumeration contains the driveways the Traffic Info Lux project manages.
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 * 
 */
public enum Driveway {
	A1(1), A3(3), A4(4), A6(6), A13(13);

	Driveway(int runwayNumber) {
		this.drivewayId = runwayNumber;
	}

	private int drivewayId;

	/**
	 * Getter for the <code>drivewayId</code> (eg. <i>A13</i>'s ID will be
	 * <i>13</i>)
	 * 
	 * @return <code>drivewayId</code>
	 */
	public int getDrivewayId() {
		return this.drivewayId;
	}

	/**
	 * Determines which driveway to use based on its ID (eg. <i>13</i> will give
	 * driveway <i>A13</i>)
	 * 
	 * @param id
	 *        the ID of the driveway
	 * @return the driveway corresponding to the input ID
	 * @throws DataValueException
	 *         if the ID has no corresponding driveway in the scope of the
	 *         Traffic Info Lux project
	 */
	public static Driveway fromId(int id) throws DataValueException {
		Driveway driveway;

		switch (id) {
		case 1:
			driveway = Driveway.A1;
			break;
		case 3:
			driveway = Driveway.A3;
			break;
		case 4:
			driveway = Driveway.A4;
			break;
		case 6:
			driveway = Driveway.A6;
			break;
		case 13:
			driveway = Driveway.A13;
			break;
		default:
			String errorMsg = "The runway A" + id + " is unknown";
			throw new DataValueException(errorMsg);
		}

		return driveway;
	}

	/**
	 * Method to check if a driveway is a member of the enumeration
	 * 
	 * @param value
	 *        the driveway name as string
	 * @return TRUE only if the driveway is a member of the enumeration
	 */
	public static boolean isMember(String value) {
		for (Driveway d : values()) {
			if (d.name().equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

}
