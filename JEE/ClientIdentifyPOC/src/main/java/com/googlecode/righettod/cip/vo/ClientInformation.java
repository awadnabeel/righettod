package com.googlecode.righettod.cip.vo;

import com.googlecode.righettod.cip.type.InformationSource;

/**
 * Object to store a client piece of information.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class ClientInformation {

	/** Source */
	private InformationSource source = null;

	/** Value */
	private String content = null;

	/**
	 * Constructor
	 * 
	 * @param source Source of the information
	 * @param content Information details
	 */
	public ClientInformation(InformationSource source, String content) {
		super();
		this.source = source;
		this.content = content;
	}

	/**
	 * Getter
	 * 
	 * @return the source
	 */
	public InformationSource getSource() {
		return this.source;
	}

	/**
	 * Getter
	 * 
	 * @return the content
	 */
	public String getContent() {
		return this.content;
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
		result = (prime * result) + ((this.content == null) ? 0 : this.content.hashCode());
		result = (prime * result) + ((this.source == null) ? 0 : this.source.hashCode());
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
		if (!(obj instanceof ClientInformation)) {
			return false;
		}
		ClientInformation other = (ClientInformation) obj;
		if (this.content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!this.content.equals(other.content)) {
			return false;
		}
		if (this.source != other.source) {
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
		return "ClientInformation [" + (this.source != null ? "source=" + this.source + ", " : "") + (this.content != null ? "content=" + this.content : "") + "]";
	}

}
