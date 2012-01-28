package com.cychop.til.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.cychop.til.exceptions.DataValueException;

/**
 * Lists all trips displayed on the traffic information signs above Luxembourg's
 * driveways
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 */
public enum Trip {
	I1(1, Driveway.A6, "belgium", "luxSud", 10),
	I2(2, Driveway.A6, "belgium", "france", 19),
	I3(3, Driveway.A3, "france", "luxSud", 8),
	I4(4, Driveway.A3, "france", "belgium", 19),
	I5(5, Driveway.A6, "strassen", "belgium", 6),
	I6(6, Driveway.A3, "luxSud", "france", 8),
	I7(7, Driveway.A6, "belgium", "luxEst", 16),
	I8(8, Driveway.A4, "esch", "luxHol", 7),
	I9(9, Driveway.A4, "esch", "luxSud", 9),
	I10(10, Driveway.A4, "esch", "luxEst", 13),
	I11(11, Driveway.A3, "france", "luxEst", 12),
	I12(12, Driveway.A13, "germany", "luxSud", 18),
	I13(13, Driveway.A13, "germany", "luxEst", 22),
	I14(14, Driveway.A13, "germany", "belgium", 28),
	I15(15, Driveway.A1, "germany", "luxEst", 20),
	I16(16, Driveway.A1, "germany", "luxSud", 19),
	I17(17, Driveway.A1, "germany", "france", 25),
	I18(18, Driveway.A1, "luxEst", "luxSud", 7),
	I19(19, Driveway.A1, "luxEst", "france", 13),
	I20(20, Driveway.A1, "luxEst", "belgium", 16);

	private static final String BUNDLE_PATH = "com.cychop.til.localization.pointNames";

	// * CONSTRUCTOR *//
	private Trip(int id, Driveway driveway, String startPoint, String endinPoint, int optimalDuration) {
		this.id = id;
		this.driveway = driveway;
		this.startPoint = startPoint;
		this.endinPoint = endinPoint;
		this.optimalDuration = optimalDuration;
	}

	// * INSTANCE VARS *//
	private int id;
	private Driveway driveway;
	private String startPoint;
	private String endinPoint;
	private int optimalDuration;

	// * ACCESSORS *//
	/**
	 * Getter for <code>id</code>
	 * 
	 * @return <code>id</code>
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Getter for <code>driveway</code>
	 * 
	 * @return <code>driveway</code>
	 */
	public Driveway getDriveway() {
		return this.driveway;
	}

	/**
	 * Getter for <code>startPoint</code>
	 * 
	 * @return <code>startPoint</code>
	 */
	public String getStartPoint() {
		return this.getStartPoint(new Locale("en"));
	}

	/**
	 * Localized getter for <code>startPoint</code>
	 * 
	 * @param lg
	 *            the language in which the label should be
	 * @return <code>startPoint</code>
	 */
	public String getStartPoint(Locale lg) {
		ResourceBundle localizer = ResourceBundle.getBundle(BUNDLE_PATH, lg);
		return localizer.getString(this.startPoint);
	}

	/**
	 * Getter for <code>endinPoint</code>
	 * 
	 * @return <code>endinPoint</code>
	 */
	public String getEndinPoint() {
		return this.getEndinPoint(new Locale("en"));
	}

	/**
	 * Localized getter for <code>endinPoint</code>
	 * 
	 * @param lg
	 *            the language in which the label should be
	 * @return <code>endinPoint</code>
	 */
	public String getEndinPoint(Locale lg) {
		ResourceBundle localizer = ResourceBundle.getBundle(BUNDLE_PATH, lg);
		return localizer.getString(this.endinPoint);
	}

	/**
	 * Getter for <code>optimalDuration</code>
	 * 
	 * @return <code>optimalDuration</code>
	 */
	public int getOptimalDuration() {
		return this.optimalDuration;
	}

	// * STATIC UTILITIES *//
	/**
	 * returns a <code>Trip</code> corresponding to an <code>id</code> passed as
	 * a parameter.
	 * 
	 * @param id
	 *            the integer ID for the trip (99 of "&99A" at the beginning of
	 *            each line in the CITA file)
	 * @return the trip associated to the input ID
	 */
	public static Trip fromId(int id) throws DataValueException {
		Trip returnTrip = null;

		switch (id) {
		case 1:
			returnTrip = I1;
			break;
		case 2:
			returnTrip = I2;
			break;
		case 3:
			returnTrip = I3;
			break;
		case 4:
			returnTrip = I4;
			break;
		case 5:
			returnTrip = I5;
			break;
		case 6:
			returnTrip = I6;
			break;
		case 7:
			returnTrip = I7;
			break;
		case 8:
			returnTrip = I8;
			break;
		case 9:
			returnTrip = I9;
			break;
		case 10:
			returnTrip = I10;
			break;
		case 11:
			returnTrip = I11;
			break;
		case 12:
			returnTrip = I12;
			break;
		case 13:
			returnTrip = I13;
			break;
		case 14:
			returnTrip = I14;
			break;
		case 15:
			returnTrip = I15;
			break;
		case 16:
			returnTrip = I16;
			break;
		case 17:
			returnTrip = I17;
			break;
		case 18:
			returnTrip = I18;
			break;
		case 19:
			returnTrip = I19;
			break;
		case 20:
			returnTrip = I20;
			break;
		default:
			throw new DataValueException(
					"There is no trip corresponding to ID #" + id + "!");
		}

		return returnTrip;
	}

	/**
	 * Method to obtains the list of trips for a Driveway
	 * 
	 * @param driveway
	 *            The specified driveway
	 * @return A list of trip using this driveway
	 */
	public static List<Trip> fromDriveway(Driveway driveway) {
		if (driveway == null) {
			return null;
		}
		List<Trip> trips = new ArrayList<Trip>();
		for (Trip t : values()) {
			if (t.getDriveway() == driveway) {
				trips.add(t);
			}
		}
		return trips;
	}
}
