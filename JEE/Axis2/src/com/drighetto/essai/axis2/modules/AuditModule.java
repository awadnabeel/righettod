package com.drighetto.essai.axis2.modules;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.modules.Module;
import org.apache.log4j.Logger;
import org.apache.neethi.Assertion;
import org.apache.neethi.Policy;

/**
 * Class to manage module life cycle
 * 
 * @author Dominique RIGHETTO<br>
 */
@SuppressWarnings("unused")
public class AuditModule implements Module {

	/** LOG4J Logger */
	private static Logger logger = Logger
			.getLogger("com.drighetto.essai.axis2.modules.AuditModule");
	
	/**  
	 * @see org.apache.axis2.modules.Module#applyPolicy(org.apache.neethi.Policy, org.apache.axis2.description.AxisDescription)
	 */
	public void applyPolicy(
	Policy arg0, AxisDescription arg1) throws AxisFault {/**/}

	/**
	 * @see org.apache.axis2.modules.Module#canSupportAssertion(org.apache.neethi.Assertion)
	 */
	public boolean canSupportAssertion(Assertion arg0) {
		return false;
	}

	/**  
	 * @see org.apache.axis2.modules.Module#engageNotify(org.apache.axis2.description.AxisDescription)
	 */
	public void engageNotify(AxisDescription arg0) throws AxisFault {/**/}

	/**  
	 * @see org.apache.axis2.modules.Module#init(org.apache.axis2.context.ConfigurationContext, org.apache.axis2.description.AxisModule)
	 */
	public void init(ConfigurationContext arg0, AxisModule arg1)
			throws AxisFault {		
		logger.info("[AuditModule] : Init !");
	}

	/**  
	 * @see org.apache.axis2.modules.Module#shutdown(org.apache.axis2.context.ConfigurationContext)
	 */
	public void shutdown(ConfigurationContext arg0) throws AxisFault {
		logger.info("[AuditModule] : Shutdown !");
		
	}

}
