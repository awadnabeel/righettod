package com.drighetto.struts2.model;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import java.text.DateFormat;
import java.util.Date;

/**
 * Simple action, in Struts2 action are included in Model layer <br>
 * com.opensymphony.xwork2.ActionSupport inheritance is optional
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class Action02 extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -1L;

	/** Attribute to store a message */
	private String message = null;

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * <br>
	 * 
	 * Simple action, by default the methods executed by Struts2 for a action is
	 * named "execute"
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		// Update the message
		this.message += " [" + DateFormat.getDateInstance().format(new Date()) + "]";

		// Return a SUCCESS flag to indicate a redirection to the renderer
		// affected to SUCCESS flag in "struts.xml" configuration file for this
		// action
		return SUCCESS;
	}

	/**
	 * Getter for the attribute message
	 * 
	 * @return The value of message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Setter for the attribute message, this setter use a validator to check
	 * that the message passed is not null and have a size > 0
	 * 
	 * @param message The new value
	 */
	@RequiredStringValidator(trim = true, shortCircuit = true, key = "page02.message.required")
	public void setMessage(String message) {
		this.message = message;
	}

}