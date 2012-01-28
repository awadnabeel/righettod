package com.righettod.jee6s3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Sample synchronous filter using web fragment deployment descriptor to declare
 * it
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Sample04SyncFilter implements Filter {

	/** Configuration for this filter */
	@SuppressWarnings("unused")
	private FilterConfig cfg = null;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("Sample04SyncFilter::Destroy --> (web fragement 01) ");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		System.out.println("Sample04SyncFilter::doFilter --> (web fragement 01)");
		fc.doFilter(req, resp);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.cfg = fc;
		System.out.println("Sample04SyncFilter::Init --> (web fragement 01) ");
	}

}