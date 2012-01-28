package com.drighetto.essai.springehcache;

import java.io.Serializable;

/**
 * Cached element
 * 
 * @author Dominique RIGHETTO<br>
 *         24 nov. 07<br>
 */
public class CachedElement implements Serializable {

	/** Field "serialVersionUID" */
	private static final long serialVersionUID = -9107841055533713848L;

	/** Element internal ID */
	private int elementId = 0;

	/** Some element data */
	private String data = null;

	/**
	 * 
	 * Default Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 */
	public CachedElement() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 * @param elementId
	 * @param data
	 */
	public CachedElement(int elementId, String data) {
		super();
		this.elementId = elementId;
		this.data = data;
	}

	/**
	 * Getter for elementId<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 * @return the elementId
	 */
	public int getElementId() {
		return this.elementId;
	}

	/**
	 * Setter for elementId<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 * @param elementId
	 *            the elementId to set
	 */
	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	/**
	 * Getter for data<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 * @return the data
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Setter for data<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         24 nov. 07<br>
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object newObj) {
		boolean isEquals = false;
		if (newObj instanceof CachedElement) {
			isEquals = (((CachedElement) newObj).elementId == this.elementId);
		}

		return isEquals;

	}

	/**
	 * @see java.lang.Object#hashCode()
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.elementId;
	}

	/**
	 * @see java.lang.Object#toString()
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return new StringBuilder("{ID:").append(this.elementId).append(";")
				.append("DATA:").append(this.data).append("}").toString();
	}

}
