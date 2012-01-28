package com.cychop.til.duration;

import java.util.Locale;
import java.util.logging.Logger;

import com.cychop.til.enums.Trip;
import com.cychop.til.exceptions.DataStructureException;
import com.cychop.til.exceptions.DataValueException;

/**
 * This class is used to represent the durations displayed by the traffic
 * information signs upon Luxembourg's driveways. The variables of a
 * <code>Duration</code> are the following:<br />
 * <ul>
 * <li>The <code>Trip</code> defines the travel from the information sign to the
 * announced location.
 * <li>The <code>duration</code> is the duration displayed by the sign (in
 * minutes).
 * </ul>
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.1
 */
public class Duration implements Comparable<Duration> {

	private static Logger log;
	static {
		log = Logger.getLogger(Duration.class.getName());
	}

	protected Trip trip;
	protected short duration;

	/**
	 * Constructs a <code>Duration</code> using the basic elements that define
	 * it.
	 * 
	 * @param trip
	 *            the travel from the information sign to the announced
	 *            location.
	 * @param startPoint
	 *            the location of the traffic information sign
	 * @param endinPoint
	 *            the destination displayed on the traffic information sign
	 * @param duration
	 *            the duration announced on the traffic information sign
	 */
	public Duration(Trip trip, short duration) {
		super();
		this.trip = trip;
		this.duration = duration;
	}

	// * ACCESSORS *//
	/**
	 * Getter for <code>trip</code>
	 * 
	 * @return <code>trip</code>
	 */
	public Trip getTrip() {
		return this.trip;
	}

	/**
	 * Getter for <code>duration</code>
	 * 
	 * @return <code>duration</code>
	 */
	public short getDuration() {
		return this.duration;
	}

	/**
	 * Setter for <code>duration</code>
	 * 
	 * @param duration
	 *            the new duration
	 */
	public void setDuration(short duration) {
		this.duration = duration;
	}

	// * METHODS * //
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.toString(new Locale("en"));
	}

	/**
	 * A localized implementation of the <code>toString</code> method
	 * 
	 * @param lg the language in which the label should be
	 * @return a <code>String</code> representation of the instance
	 */
	public String toString(Locale lg) {
		String returnString;
		returnString = String.valueOf(this.trip.getDriveway().getDrivewayId())
				+ ",";
		returnString += this.trip.getStartPoint(lg) + ","
				+ this.trip.getEndinPoint(lg) + ",";
		returnString += this.duration;
		return returnString;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equal = false;
		equal = (obj instanceof Duration)
				&& (obj.hashCode() == this.hashCode());
		return equal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.getTrip().getId();
	}

	// * STATIC UTILITIES *//
	/**
	 * Creates a <code>Duration</code> instance from a line in the CITA source
	 * file.
	 * 
	 * @param line
	 *            a line obtained from the CITA source file.
	 * @return a <code>Duration</code> instance containing the information from
	 *         the input.
	 * @throws DataValueException
	 *             if the data seems inappropriate.
	 * @throws DataStructureException
	 *             if the structure of the file seems inappropriate.
	 */
	public static Duration fromCita(String line) throws DataValueException,
			DataStructureException {
		// Declaring the variables
		Trip trip;
		short duration = 0;

		// initializing the trip
		// the trip ID is contained at beginning of line, between "&" and "A"
		try {
			int tripId = Integer.parseInt(line.substring(1, line.indexOf('A')));
			trip = Trip.fromId(tripId);
		} catch (NumberFormatException nfe) {
			throw new DataStructureException(
					"Could not get the trip ID; line: " + line);
		} catch (StringIndexOutOfBoundsException sie) {
			throw new DataStructureException(
					"Could not get the trip ID; line: " + line);
		}

		// initializing the trip duration
		line = line.substring(line.indexOf(" ")).trim();
		try {
			duration = Short.parseShort(line.substring(0, line.indexOf(" ")));
		} catch (StringIndexOutOfBoundsException sie) {
			duration = noTimeDisplayedTreatment(line);
			if (duration == 0) {
				return null;
			}
		} catch (NumberFormatException nfe) {
			duration = noTimeDisplayedTreatment(line);
			if (duration == 0) {
				return null;
			}
		}

		return new Duration(trip, duration);
	}

	/**
	 * Returns a duration code when no duration is given.
	 * 
	 * @param line
	 *            the final part of the CITA line (normally the time)
	 * @return -1 when the traffic flow is <code>FLUID</code>, else 0
	 */
	private static short noTimeDisplayedTreatment(String line) {
		short duration;

		if ("FLUIDE".equals(line.trim())) {
			duration = -1;
		} else {
			// This line cannot be read ==> corrective measures must be
			// taken
			log.warning("Could not parse the following: " + line);
			duration = 0;
		}
		return duration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Duration arg0) {
		int comparison;

		// order by drivewayNumber, startPoint, endinPoint
		if (this.trip.getDriveway() == arg0.getTrip().getDriveway()) {
			if (this.trip.getStartPoint().equals(arg0.getTrip().getStartPoint())) {
				if (this.trip.getEndinPoint().equals(arg0.getTrip().getEndinPoint())) {
					comparison = 0;
				} else {
					comparison = this.trip.getEndinPoint()
							.compareToIgnoreCase(arg0.getTrip().getEndinPoint());
				}
			} else {
				comparison = this.trip.getStartPoint()
						.compareToIgnoreCase(arg0.getTrip().getStartPoint());
			}
		} else {
			comparison = this.trip.getDriveway().getDrivewayId() - arg0.getTrip().getDriveway().getDrivewayId();
		}

		return comparison;
	}
}
