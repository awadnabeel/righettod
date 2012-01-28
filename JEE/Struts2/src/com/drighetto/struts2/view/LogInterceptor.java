package com.drighetto.struts2.view;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple custom logger interceptor because Struts2 provide already a logging
 * interceptor... <br>
 * <br>
 * <b>Keep in mind that invoke will return after the result has been called (eg.
 * after you JSP has been rendered), making it perfect for things like
 * open-session-in-view patterns. If you want to do something before the result
 * gets called, you should implement a PreResultListener.</b> <br>
 * <br>
 * <b>A Struts 2 Action instance is created for every request and do not need to
 * be thread-safe. Conversely, Interceptors are shared between requests and must
 * be thread-safe.</b> <br>
 * <br>
 * See http://struts.apache.org/2.x/docs/interceptors.html
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class LogInterceptor implements Interceptor {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -1241557481868080009L;

	/** Logger */
	private static final Log LOGGER = LogFactory.getLog(LogInterceptor.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {
		LOGGER.info("LogInterceptor : Release");

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
		LOGGER.info("LogInterceptor : Initialization");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// Log before invocation delegation
		Action currentAction = (Action) actionInvocation.getAction();
		LOGGER.info("LogInterceptor : Before invocation of " + actionInvocation.getProxy().getMethod() + " from class " + currentAction.getClass().getName());
		// Invoke action method
		String result = actionInvocation.invoke();
		// Log after invocation
		LOGGER.info("LogInterceptor : After invocation of " + actionInvocation.getProxy().getMethod() + " - Result is '" + result + "'");
		// Retun invocation result
		return result;
	}

}
