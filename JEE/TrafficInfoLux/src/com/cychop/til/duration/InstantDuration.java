package com.cychop.til.duration;

import java.util.Locale;

import com.cychop.til.enums.TrafficFlow;
import com.cychop.til.enums.Trip;

/**
 * This class extends <code>Duration</code> by adding the possibility to display
 * the density of traffic flow. See <code>Duration</code> for more information.
 * 
 * @author Cyrille Chopelet (cyrille.chopelet@mines-nancy.org)
 * @version 1.1
 * 
 */
public class InstantDuration extends Duration {

	private TrafficFlow flow;

	// * CONSTRUCTORS *//
	/**
	 * Constructs an <code>InstantaneousDuration</code> instance using the basic
	 * elements that define it.
	 * 
	 * @param trip
	 *            the travel from the information sign to the announced
	 *            location.
	 * @param duration
	 *            the duration announced on the traffic information sign
	 * @param flow
	 *            the current traffic flow conditions
	 */
	public InstantDuration(Trip trip, short duration, TrafficFlow flow) {
		super(trip, duration);
		this.flow = flow;
	}

	/**
	 * Constructs an <code>InstantaneousDuration</code> instance using a
	 * Duration
	 * 
	 * @param mother
	 *            the <code>Duration</code> instance that is used as base for
	 *            construction
	 * @param flow
	 *            the traffic flow conditions used to enhance the
	 *            <code>Duration</code> object passed as argument
	 */
	public InstantDuration(Duration mother, TrafficFlow flow) {
		this(mother.trip, mother.duration, flow);
	}

	/**
	 * Constructs an <code>InstantaneousDuration</code> instance using a
	 * Duration
	 * 
	 * @param mother
	 *            the <code>Duration</code> instance that is used as base for
	 *            construction
	 */
	public InstantDuration(Duration mother) {
		this(mother.trip, mother.duration, TrafficFlow.UNKNOWN);
		
		this.flow = TrafficFlow.fromTime(mother.trip.getOptimalDuration(), mother.duration);
	}

	// * ACCESSORS *//
	/**
	 * Getter for <code>flow</code>
	 * 
	 * @return the current traffic flow conditions
	 */
	public TrafficFlow getFlow() {
		return this.flow;
	}

	/**
	 * Setter for <code>flow</code>
	 * 
	 * @param flow
	 *            the current traffic flow conditions
	 */
	public void setFlow(TrafficFlow flow) {
		this.flow = flow;
	}

	/* METHODS */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return (super.toString() + "," + this.flow.getDensity());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(Locale lg) {
		return (super.toString(lg) + "," + this.flow.getDensity());
	}
}
