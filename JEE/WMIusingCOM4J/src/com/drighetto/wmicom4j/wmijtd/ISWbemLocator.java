package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Used to obtain Namespace connections
 */
@IID("{76A6415B-CB41-11D1-8B02-00600806D9B6}")
public interface ISWbemLocator extends Com4jObject {
    /**
     * Connect to a Namespace
     */
    @VTID(7)
    com.drighetto.wmicom4j.wmijtd.ISWbemServices connectServer(
        @DefaultValue(".")java.lang.String strServer,
        @DefaultValue("")java.lang.String strNamespace,
        @DefaultValue("")java.lang.String strUser,
        @DefaultValue("")java.lang.String strPassword,
        @DefaultValue("")java.lang.String strLocale,
        @DefaultValue("")java.lang.String strAuthority,
        @DefaultValue("0")int iSecurityFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * The Security Configurator for this Object
     */
    @VTID(8)
    com.drighetto.wmicom4j.wmijtd.ISWbemSecurity security_();

}
