package com.drighetto.essai.struts.action;

import org.ajaxtags.servlets.BaseAjaxAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ajax action to test scalability of AJAX TAGS library
 * @author Dominique RIGHETTO
 * 4 oct. 06<br>
 */
public class AskAnswerAction extends BaseAjaxAction {
    
    /**
     * Ajax action to test scalability of AJAX TAGS library
     * @param arg0 Action mapping struts object
     * @param arg1 Action form struts object
     * @param arg2 HTTP request object
     * @param arg3 HTTP response object
     * @throws java.lang.Exception
     */
	@Override
	public String getXmlContent(@SuppressWarnings("unused")
	ActionMapping arg0, @SuppressWarnings("unused")
	ActionForm arg1,
			HttpServletRequest arg2, @SuppressWarnings("unused")
			HttpServletResponse arg3) throws Exception {
        String str = arg2.getParameter("id");
        
        System.out.println(str);
		return str;		
	}

}
