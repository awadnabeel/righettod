package com.cychop.til.enums;

/**
 * Represents the density of the traffic flow
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.0
 * 
 */
public enum TrafficFlow {
	UNKNOWN(0), FLUID(1), DENSE(2), SATURATED(3);

	// * CONSTANTS * //
	private static float MAX_COEF_FLUID = 1.25f;
	private static float MAX_COEF_DENSE = 1.80f;

	// * CONSTRUCTORS * //
	TrafficFlow(int density) {
		this.density = density;
	}

	// * INSTANCE VARS * //
	private int density;
	private float criterium;

	// * ACCESSORS * //
	/**
	 * Getter for <code>density</code>
	 * 
	 * @return <code>density</code>
	 */
	public int getDensity() {
		return this.density;
	}

	/**
	 * Getter for <code>criterium</code>
	 * 
	 * @return <code>criterium</code>
	 */
	public float getCriterium() {
		return this.criterium;
	}

	/**
	 * Returns the value corresponding to <code>density</code>
	 * 
	 * @param density
	 *            an int representing the density of the current traffic flow
	 * @return the enum value corresponding to <code>density</code>
	 */
	public static TrafficFlow fromDensity(int density) {
		TrafficFlow flow;

		switch (density) {
		case 1:
			flow = TrafficFlow.FLUID;
			break;
		case 2:
			flow = TrafficFlow.DENSE;
			break;
		case 3:
			flow = TrafficFlow.SATURATED;
			break;
		case 0:
		default:
			flow = TrafficFlow.UNKNOWN;
			break;
		}

		return flow;
	}

	/**
	 * Returns the density according to the current duration of the trip.
	 * 
	 * @param optimalDuration
	 *            the duration in optimal driving conditions
	 * @param actualDuration
	 *            the time required to do the trip now
	 * @return the enum value corresponding to the input data
	 */
	public static TrafficFlow fromTime(int optimalDuration, int actualDuration) {
		TrafficFlow flow = SATURATED;

		if (actualDuration <= MAX_COEF_FLUID * optimalDuration) {
			flow = FLUID;
		} else if (actualDuration <= MAX_COEF_DENSE * optimalDuration) {
			flow = DENSE;
		} // else SATURATED => see 'flow' initialization

		return flow;
	}
}
