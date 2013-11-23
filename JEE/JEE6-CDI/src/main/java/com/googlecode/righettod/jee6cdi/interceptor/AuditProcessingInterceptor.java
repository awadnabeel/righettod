package com.googlecode.righettod.jee6cdi.interceptor;

import java.io.Serializable;
import java.util.UUID;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.googlecode.righettod.jee6cdi.qualifier.AuditProcessing;

/**
 * Implementation of a bean providing CDI Lifecycle interception features.<br>
 * The custom CDI annotation "[at]AuditProcessing" is used in order to specify to CDI container to use <br>
 * this implementation when bean or bean's methods are annoted with "[at]AuditProcessing".<br>
 * CDI annotation "[at]Interceptor" is used to indicate to CDI container that this class is <br>
 * an Interceptor implementation.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.oracle.com/javaee/6/tutorial/doc/gkhjx.html"
 * 
 */
@SuppressWarnings({ "serial" })
@AuditProcessing
@Interceptor
public class AuditProcessingInterceptor implements Serializable {

	/** Instance unique ID */
	private String instanceId = UUID.randomUUID().toString();

	/**
	 * Constructor
	 */
	public AuditProcessingInterceptor() {
		System.out.printf("Initialize CDI interceptor 'AuditProcessingInterceptor' instance '%s'.\n", this.instanceId);
	}

	/**
	 * Interceptor for method call event
	 * 
	 * @param invocationContext Method call invocation context
	 * @return Method execution result
	 * @throws Exception
	 */
	@AroundInvoke
	public Object logMethodCall(InvocationContext invocationContext) throws Exception {
		System.out.printf("Before call of method '%s' in class '%s' for interceptor instance '%s'.\n", invocationContext.getMethod().getName(), invocationContext.getMethod().getDeclaringClass()
				.getName(), this.instanceId);
		Object result = invocationContext.proceed();
		System.out.printf("After  call of method '%s' in class '%s' for interceptor instance '%s'.\n", invocationContext.getMethod().getName(), invocationContext.getMethod().getDeclaringClass()
				.getName(), this.instanceId);
		return result;
	}
}
