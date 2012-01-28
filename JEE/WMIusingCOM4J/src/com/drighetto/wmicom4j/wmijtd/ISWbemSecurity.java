package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Security Configurator
 */
@IID("{B54D66E6-2287-11D2-8B33-00600806D9B6}")
public interface ISWbemSecurity extends Com4jObject {
    /**
     * The security impersonation level
     */
    @VTID(7)
    com.drighetto.wmicom4j.wmijtd.WbemImpersonationLevelEnum impersonationLevel();

    /**
     * The security impersonation level
     */
    @VTID(8)
    void impersonationLevel(
        com.drighetto.wmicom4j.wmijtd.WbemImpersonationLevelEnum iImpersonationLevel);

    /**
     * The security authentication level
     */
    @VTID(9)
    com.drighetto.wmicom4j.wmijtd.WbemAuthenticationLevelEnum authenticationLevel();

    /**
     * The security authentication level
     */
    @VTID(10)
    void authenticationLevel(
        com.drighetto.wmicom4j.wmijtd.WbemAuthenticationLevelEnum iAuthenticationLevel);

    /**
     * The collection of privileges for this object
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.ISWbemPrivilegeSet privileges();

    @VTID(11)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemPrivilegeSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemPrivilege privileges(
        com.drighetto.wmicom4j.wmijtd.WbemPrivilegeEnum iPrivilege);

}
