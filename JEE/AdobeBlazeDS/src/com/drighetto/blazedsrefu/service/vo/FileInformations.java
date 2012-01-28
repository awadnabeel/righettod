package com.drighetto.blazedsrefu.service.vo;

import java.io.Serializable;

/**
 * Class storing file informations
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class FileInformations implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6025708810400506804L;

	/** File short name */
	private String name = null;

	/** File full path */
	private String path = null;

	/** File size in bytes */
	private long size = 0;

	/** Flag indicating if the current file is a directory */
	private boolean directory = false;

	/** Last modification date of the file */
	private String lastModificationDate = null;

	/** Flag indicating if the current file is writable */
	private boolean writeable = false;

	/** Flag indicating if the current file is hidden */
	private boolean hidden = false;

	/** Path of the parent of the current file */
	private String parentPath = null;

	/**
	 * 
	 * Default constructor
	 * 
	 */
	public FileInformations() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name File short name
	 * @param path File full path
	 * @param size File size in bytes
	 * @param directory File indicating if the current file is a directory
	 * @param lastModificationDate Last modification date of the file
	 * @param writeable Flag indicating if the current file is writable
	 * @param hidden Flag indicating if the current file is hidden
	 * @param parentPath Path of the parent of the current file
	 */
	public FileInformations(String name, String path, long size, boolean directory, String lastModificationDate,
			boolean writeable, boolean hidden, String parentPath) {
		super();
		this.name = name;
		this.path = path;
		this.size = size;
		this.directory = directory;
		this.lastModificationDate = lastModificationDate;
		this.writeable = writeable;
		this.hidden = hidden;
		this.parentPath = parentPath;
	}

	/**
	 * Getter for the attribute name
	 * 
	 * @return The value of name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for the attribute name
	 * 
	 * @param name The new value
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the attribute path
	 * 
	 * @return The value of path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Setter for the attribute path
	 * 
	 * @param path The new value
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Getter for the attribute size
	 * 
	 * @return The value of size
	 */
	public long getSize() {
		return this.size;
	}

	/**
	 * Setter for the attribute size
	 * 
	 * @param size The new value
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * Getter for the attribute directory
	 * 
	 * @return The value of directory
	 */
	public boolean isDirectory() {
		return this.directory;
	}

	/**
	 * Setter for the attribute directory
	 * 
	 * @param directory The new value
	 */
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	/**
	 * Getter for the attribute lastModificationDate
	 * 
	 * @return The value of lastModificationDate
	 */
	public String getLastModificationDate() {
		return this.lastModificationDate;
	}

	/**
	 * Setter for the attribute lastModificationDate
	 * 
	 * @param lastModificationDate The new value
	 */
	public void setLastModificationDate(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * Getter for the attribute writeable
	 * 
	 * @return The value of writeable
	 */
	public boolean isWriteable() {
		return this.writeable;
	}

	/**
	 * Setter for the attribute writeable
	 * 
	 * @param writeable The new value
	 */
	public void setWriteable(boolean writeable) {
		this.writeable = writeable;
	}

	/**
	 * Getter for the attribute hidden
	 * 
	 * @return The value of hidden
	 */
	public boolean isHidden() {
		return this.hidden;
	}

	/**
	 * Setter for the attribute hidden
	 * 
	 * @param hidden The new value
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Getter for the attribute parentPath
	 * 
	 * @return The value of parentPath
	 */
	public String getParentPath() {
		return this.parentPath;
	}

	/**
	 * Setter for the attribute parentPath
	 * 
	 * @param parentPath The new value
	 */
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

}
