package com.drighetto.essai.axis2.modules;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Handler providing logging of services call (services audit functionality)
 * 
 * @author Dominique RIGHETTO<br>
 */
@SuppressWarnings("unused")
public class AuditHandler extends AbstractHandler implements Handler {

	/** LOG4J Logger */
	private static Logger logger = Logger
			.getLogger("com.drighetto.essai.axis2.modules.AuditModule");

	/**
	 * Default Constructor
	 */
	public AuditHandler() {
		super();
	}

	/**
	 * Invoke is called to do the actual work of the Handler object...
	 * 
	 * @see org.apache.axis2.engine.Handler#invoke(org.apache.axis2.context.MessageContext)
	 */
	@SuppressWarnings("unchecked")
	public InvocationResponse invoke(MessageContext msgContext) {

		StringBuffer trace = new StringBuffer("");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssZ");
		ArrayList parameters = null;
		Parameter param = null;

		// Trace service call
		try {
			// Create trace message
			//--General informations
			trace.append("<trace id=\"").append(dateFormat.format(new Date()))
					.append("\">").append("<operation>").append(
							msgContext.getAxisOperation().getName()).append(
							"</operation>").append("<from>").append(
							(msgContext.getFrom() != null) ? msgContext
									.getFrom().getAddress() : "").append(
							"</from>").append("<replyTo>").append(
							(msgContext.getReplyTo() != null) ? msgContext
									.getReplyTo().getAddress() : "").append(
							"</replyTo>").append("<messageId>").append(
							(msgContext.getMessageID() != null) ? msgContext
									.getMessageID() : "")
					.append("</messageId>").append("<parameters>");
			//--Operation call parameters			
			parameters = msgContext.getAxisService().getParameters();
			for (int i = 0; i < parameters.size(); i++) {
				param = (Parameter) parameters.get(i);
				trace.append("<parameter name=\"").append(param.getName())
						.append("\">").append(param.getValue()).append("</parameter>");
			}
			trace.append("</parameters>").append("</trace>");
			// Log trace
			logger.info("[AuditModule] : " + trace.toString().trim());
		} catch (Exception e) {
			logger.fatal("[AuditModule]", e);
		} finally {
			trace = null;
			dateFormat = null;
			parameters = null;
			param = null;
		}

		// Notify Axis 2 container to continue
		// this processing must be transparent !
		return InvocationResponse.CONTINUE;
	}

}
