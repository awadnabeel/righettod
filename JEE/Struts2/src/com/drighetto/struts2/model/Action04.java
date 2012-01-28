package com.drighetto.struts2.model;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * Simple Action for show access of context, application, session, request
 * objects
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class Action04 extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 6669938575643017006L;

	/** Context attributes map */
	private Map<String, Object> contextAttr = null;

	/** Application objects attributes map */
	private Map<String, Object> applicationScopeAttr = null;

	/** Session objects attributes map */
	private Map<String, Object> sessionScopeAttr = null;

	/** Request objects attributes map */
	private Map<String, Object> requestScopeAttr = null;

	/**
	 * {@inheritDoc}
	 * 
	 * <br>
	 * 
	 * Simple action, by default the methods executed by Struts2 for a action is
	 * named "execute"
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		this.contextAttr = (Map) ActionContext.getContext().get("attr");
		this.applicationScopeAttr = (Map) ActionContext.getContext().get("application");
		this.sessionScopeAttr = (Map) ActionContext.getContext().get("session");
		this.requestScopeAttr = (Map) ActionContext.getContext().get("request");
		return SUCCESS;
	}

	/**
	 * Getter for the attribute applicationScopeAttr
	 * 
	 * @return The value of applicationScopeAttr
	 */
	public Map<String, Object> getApplicationScopeAttr() {
		return this.applicationScopeAttr;
	}

	/**
	 * Getter for the attribute sessionScopeAttr
	 * 
	 * @return The value of sessionScopeAttr
	 */
	public Map<String, Object> getSessionScopeAttr() {
		return this.sessionScopeAttr;
	}

	/**
	 * Getter for the attribute requestScopeAttr
	 * 
	 * @return The value of requestScopeAttr
	 */
	public Map<String, Object> getRequestScopeAttr() {
		return this.requestScopeAttr;
	}

	/**
	 * Getter for the attribute contextAttr
	 * 
	 * @return The value of contextAttr
	 */
	public Map<String, Object> getContextAttr() {
		return this.contextAttr;
	}

}
