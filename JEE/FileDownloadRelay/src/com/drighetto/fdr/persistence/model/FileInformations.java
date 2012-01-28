package com.drighetto.fdr.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity containing downloaded file informations
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("serial")
@Entity
public class FileInformations implements Serializable {

	/** File ID (record ID) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identifier;

	/** Name of the file */
	@Basic(fetch = FetchType.EAGER)
	private String name = null;

	/** Download date */
	@Temporal(TemporalType.TIMESTAMP)
	private Date downloadDate = new Date();

	/**
	 * Constructor
	 */
	public FileInformations() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *        File name
	 */
	public FileInformations(String name) {
		super();
		this.name = name;
	}

	/**
	 * Getter
	 * 
	 * @return the identifier
	 */
	public Long getIdentifier() {
		return this.identifier;
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
	 * Getter
	 * 
	 * @return the downloadDate
	 */
	public Date getDownloadDate() {
		return this.downloadDate;
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
		result = prime * result + ((this.downloadDate == null) ? 0 : this.downloadDate.hashCode());
		result = prime * result + ((this.identifier == null) ? 0 : this.identifier.hashCode());
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
		if (!(obj instanceof FileInformations)) {
			return false;
		}
		FileInformations other = (FileInformations) obj;
		if (this.downloadDate == null) {
			if (other.downloadDate != null) {
				return false;
			}
		} else if (!this.downloadDate.equals(other.downloadDate)) {
			return false;
		}
		if (this.identifier == null) {
			if (other.identifier != null) {
				return false;
			}
		} else if (!this.identifier.equals(other.identifier)) {
			return false;
		}
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileInformations [" + (this.downloadDate != null ? "downloadDate=" + this.downloadDate + ", " : "") + (this.identifier != null ? "identifier=" + this.identifier + ", " : "")
				+ (this.name != null ? "name=" + this.name : "") + "]";
	}

}
