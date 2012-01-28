package com.drighetto.essai;

import org.ajaxtags.servlets.BaseAjaxServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class for Servlet: SampleGenerator
 * 
 * @author D. Righetto
 * 
 */
public class SampleGenerator extends BaseAjaxServlet {

	/**
	 * ID use by JVM for the serialization process
	 */
	private static final long serialVersionUID = -7270516963262943282L;

	/**
	 * Method to provide sample data
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
	public String getXmlContent(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DateFormat format = null;
		String str = "ERROR";
		String sId = "";
		try {
			format = new SimpleDateFormat("k':'m':'s':'S");
			sId    = (arg0.getSession(false) == null) ? "NULL" : arg0.getSession(false).getId(); 
			str    = "Session ID[" + sId + "] -> "
					+ format.format(new Date()) + " - {"
					+ arg1.getCharacterEncoding() + "}";
			System.out.println(str);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return str;
	}

}