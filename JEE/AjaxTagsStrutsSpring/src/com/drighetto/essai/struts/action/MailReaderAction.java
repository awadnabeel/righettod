package com.drighetto.essai.struts.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lu.pgd.nssis.ihm.mail.Message;

/**
 * Action to test a redirection system through STRUTS
 * @author Dominique RIGHETTO
 * 4 oct. 06<br>
 */
public class MailReaderAction extends Action {
	
    /**
     * Method to execute action
     * @param mapping Struts mapping object
     * @param form Action form bean
     * @param request  HTTP request
     * @param response HTTP response
     * @return org.apache.struts.action.ActionForward
     */
    @Override
	public ActionForward execute( ActionMapping mapping, @SuppressWarnings("unused")
	ActionForm form, HttpServletRequest request, @SuppressWarnings("unused")
	HttpServletResponse response )
    {
    	try{
		//		 Get session
				Session session = Session.getDefaultInstance(new Properties(), null);
		
		//		 Get the store
				Store store = session.getStore("pop3");
				store.connect("pop.free.fr", "drighetto", "3eruvrjp");
		
		//		 Get folder
				Folder folder = store.getFolder("INBOX");
				folder.open(Folder.READ_WRITE);
		
		//		 Get mails
				List<Message> mailsList = new ArrayList<Message>();
		        javax.mail.Message[] popMails = folder.getMessages();
		        for( javax.mail.Message msg : popMails)
		        	mailsList.add( new Message( msg ) );
		
		//		 Close connection 
				folder.close(false);
				store.close();	
				
		//      Set attribute 
				request.getSession(false).setAttribute("MAILS", mailsList);
				
    	}
    	catch( Exception exp){
    		exp.printStackTrace();
    	}
    	
		return mapping.findForward("mailDisplayer");
    }	

}
