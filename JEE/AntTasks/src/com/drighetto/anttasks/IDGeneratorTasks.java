package com.drighetto.anttasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.Task;

import java.util.UUID;

/**
 * Task to generate a unique ID and update a property value with this ID
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class IDGeneratorTasks extends Task {

	/**
	 * Property name on which the value will be updated with the generated ID,
	 * if the property do not exists it will be created in the project.
	 */
	private String propertyname = null;

	/**
	 * Setter
	 * 
	 * @param pname The property name
	 */
	public void setPropertyname(String pname) {
		// Check property name name passed
		if (pname == null || "".equals(pname.trim())) {
			throw new IllegalArgumentException("The name of the property that will be contains the generated ID cannot be empty or null !");
		}
		this.propertyname = pname;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		try {
			// Generate a unique IU
			String id = UUID.randomUUID().toString();
			// Set or update the property value
			PropertyHelper helper = (PropertyHelper) getProject().getReference("ant.PropertyHelper");
			helper.setProperty(null, this.propertyname, id, true);
			log("Generated ID [" + id + "] put in property [" + this.propertyname + "].");
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

}
