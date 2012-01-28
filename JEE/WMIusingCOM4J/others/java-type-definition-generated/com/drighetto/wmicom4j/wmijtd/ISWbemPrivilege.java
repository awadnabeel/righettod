package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Privilege Override
 */
@IID("{26EE67BD-5804-11D2-8B4A-00600806D9B6}")
public interface ISWbemPrivilege extends Com4jObject {
    /**
     * Whether the Privilege is to be enabled or disabled
     */
    @VTID(7)
    @DefaultMethod
    boolean isEnabled();

    /**
     * Whether the Privilege is to be enabled or disabled
     */
    @VTID(8)
    @DefaultMethod
    void isEnabled(
        boolean bIsEnabled);

    /**
     * The name of the Privilege
     */
    @VTID(9)
    java.lang.String name();

    /**
     * The display name of the Privilege
     */
    @VTID(10)
    java.lang.String displayName();

    /**
     * The Privilege identifier
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.WbemPrivilegeEnum identifier();

}
