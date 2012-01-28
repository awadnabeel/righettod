package com.drighetto.essai;

import org.ajaxtags.servlets.BaseAjaxServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class for Servlet: TimeGenerator
 * 
 */
public class TimeGenerator extends BaseAjaxServlet {

	/**
	 * ID use by JVM for the serialization process
	 */
	private static final long serialVersionUID = -3496512338731009726L;

	/**
	 * Method to provide time
	 * 
	 * @param arg0
	 *            HTTP request object
	 * @param arg1
	 *            HTTP response object
	 * @throws java.lang.Exception
	 * @author D. Righetto
	 * @return java.lang.String
	 * 
	 */
	@Override
	public String getXmlContent(
	HttpServletRequest arg0, @SuppressWarnings("unused")
	HttpServletResponse arg1) throws Exception {
		DateFormat format = null;
		String str = "ERROR";
		try {
			format = new SimpleDateFormat("k':'m':'s':'S");
			str = "TIME : [" + format.format(new Date()) + "] CALL FROM '" + arg0.getParameter("src") + "'";
			System.out.println(str);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return str;
	}

}