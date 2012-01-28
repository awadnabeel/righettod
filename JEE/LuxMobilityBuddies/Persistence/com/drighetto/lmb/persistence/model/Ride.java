package com.drighetto.lmb.persistence.model;

import java.io.Serializable;

/**
 * Value object storing ride informations.<br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Ride implements Serializable {

	/** Serial VUID */
	private static final long serialVersionUID = -8984943587168945906L;

	/** Ride short name, ie : "A6 > A1" */
	private String shortName = "";

	/** Ride display name, ie "Bertrange > Howald" */
	private String displayName = "";

	/** Ride current delay in minutes */
	private int current = 0;

	/** Ride average delay in minutes */
	private int average = 0;

	/** Ride maximum delay in minutes */
	private int maximum = 0;

	/**
	 * Constructor
	 * 
	 * @param shortName
	 *        Ride short name
	 * @param displayName
	 *        ide display name
	 * @param current
	 *        Ride current delay in minutes
	 * @param average
	 *        Ride average delay in minutes
	 * @param maximum
	 *        Ride maximum delay in minutes
	 */
	public Ride(String shortName, String displayName, int current, int average, int maximum) {
		super();
		this.shortName = shortName;
		this.displayName = displayName;
		this.current = current;
		this.average = average;
		this.maximum = maximum;
	}

	/**
	 * Getter
	 * 
	 * @return the shortName
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * Setter
	 * 
	 * @param shortName
	 *        the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Getter
	 * 
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Setter
	 * 
	 * @param displayName
	 *        the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Getter
	 * 
	 * @return the current
	 */
	public int getCurrent() {
		return this.current;
	}

	/**
	 * Setter
	 * 
	 * @param current
	 *        the current to set
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * Getter
	 * 
	 * @return the average
	 */
	public int getAverage() {
		return this.average;
	}

	/**
	 * Setter
	 * 
	 * @param average
	 *        the average to set
	 */
	public void setAverage(int average) {
		this.average = average;
	}

	/**
	 * Getter
	 * 
	 * @return the maximum
	 */
	public int getMaximum() {
		return this.maximum;
	}

	/**
	 * Setter
	 * 
	 * @param maximum
	 *        the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.shortName == null) ? 0 : this.shortName.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Ride)) {
			return false;
		}
		Ride other = (Ride) obj;
		if (this.shortName == null) {
			if (other.shortName != null) {
				return false;
			}
		} else if (!this.shortName.equals(other.shortName)) {
			return false;
		}
		return true;
	}

}
