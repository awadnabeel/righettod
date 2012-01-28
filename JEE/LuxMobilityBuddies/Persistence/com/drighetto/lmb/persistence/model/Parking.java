package com.drighetto.lmb.persistence.model;

import java.io.Serializable;

/**
 * Value object storing main parking informations.<br>
 * 
 * 
 * @see "http://www.vdl.lu/Technical+Info+Parking+Feed.html"
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * 
 */
public class Parking implements Serializable, Comparable<Parking> {

	/**
	 * Serial VUID
	 */
	private static final long serialVersionUID = 6478320780809828385L;

	/** Parking identification name */
	private String name = "";
	/** Address of incoming side of the parking */
	private String address = "";
	/** Location in Luxemburg town */
	private String zone = "";
	/** Total capacity */
	private String capacity = "";
	/** Place available */
	private String available = "";
	/** Parking is open */
	private String open = "";
	/** Filling status */
	private String fillingStatus = "";
	/** Filling trend of the parking */
	private String fillingTrend = "";

	/**
	 * Constructor
	 * 
	 * @param name
	 *        Parking identification name
	 */
	public Parking(String name) {
		super();
		this.name = name;
	}

	/**
	 * Constructor
	 */
	public Parking() {
		super();
	}

	/**
	 * Getter
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter
	 * 
	 * @param name
	 *        the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Setter
	 * 
	 * @param address
	 *        the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter
	 * 
	 * @return the zone
	 */
	public String getZone() {
		return this.zone;
	}

	/**
	 * Setter
	 * 
	 * @param zone
	 *        the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * Getter
	 * 
	 * @return the capacity
	 */
	public String getCapacity() {
		return this.capacity;
	}

	/**
	 * Setter
	 * 
	 * @param capacity
	 *        the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	/**
	 * Getter
	 * 
	 * @return the available
	 */
	public String getAvailable() {
		return this.available;
	}

	/**
	 * Setter
	 * 
	 * @param available
	 *        the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}

	/**
	 * Getter
	 * 
	 * @return the fillingStatus
	 */
	public String getFillingStatus() {
		return this.fillingStatus;
	}

	/**
	 * Setter
	 * 
	 * @param fillingStatus
	 *        the fillingStatus to set
	 */
	public void setFillingStatus(String fillingStatus) {
		this.fillingStatus = fillingStatus;
	}

	/**
	 * Getter
	 * 
	 * @return the fillingTrend
	 */
	public String getFillingTrend() {
		return this.fillingTrend;
	}

	/**
	 * Setter
	 * 
	 * @param fillingTrend
	 *        the fillingTrend to set
	 */
	public void setFillingTrend(String fillingTrend) {
		this.fillingTrend = fillingTrend;
	}

	/**
	 * Getter
	 * 
	 * @return the open
	 */
	public String getOpen() {
		return this.open;
	}

	/**
	 * Setter
	 * 
	 * @param open
	 *        the open to set
	 */
	public void setOpen(String open) {
		this.open = open;
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
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		if (!(obj instanceof Parking)) {
			return false;
		}
		Parking other = (Parking) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Parking o) {
		if (this.name == null) {
			return -1;
		}
		if (o == null) {
			return 1;
		}
		return this.name.compareTo(o.getName());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Parking [" + (this.address != null ? "address=" + this.address + ", " : "") + (this.available != null ? "available=" + this.available + ", " : "")
				+ (this.capacity != null ? "capacity=" + this.capacity + ", " : "") + (this.fillingStatus != null ? "fillingStatus=" + this.fillingStatus + ", " : "")
				+ (this.fillingTrend != null ? "fillingTrend=" + this.fillingTrend + ", " : "") + (this.name != null ? "name=" + this.name + ", " : "")
				+ (this.open != null ? "open=" + this.open + ", " : "") + (this.zone != null ? "zone=" + this.zone : "") + "]";
	}

}
