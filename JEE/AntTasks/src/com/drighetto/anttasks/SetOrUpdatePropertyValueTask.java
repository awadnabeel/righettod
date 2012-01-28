package com.drighetto.anttasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.Task;

/**
 * Task to set or update the value of a property
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class SetOrUpdatePropertyValueTask extends Task {

	/**
	 * Property name on which the value will be setted or updated, if the
	 * property do not exists it will be created in the project.
	 */
	private String propertyname = null;

	/** New value to set */
	private String newvalue = null;

	/**
	 * Setter
	 * 
	 * @param pname The property name
	 */
	public void setPropertyname(String pname) {
		// Check property name name passed
		if (pname == null || "".equals(pname.trim())) {
			throw new IllegalArgumentException("The name of the property that will be setted or updated cannot be empty or null !");
		}
		this.propertyname = pname;
	}

	/**
	 * Setter
	 * 
	 * @param value The value to set
	 */
	public void setNewvalue(String value) {
		this.newvalue = value;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		try {
			String v = (this.newvalue == null) ? "" : this.newvalue;
			// Set or update the property value
			PropertyHelper helper = (PropertyHelper) getProject().getReference("ant.PropertyHelper");
			helper.setProperty(null, this.propertyname, v, true);
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

}
