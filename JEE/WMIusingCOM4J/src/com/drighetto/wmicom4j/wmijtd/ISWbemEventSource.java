package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * An Event source
 */
@IID("{27D54D92-0EBE-11D2-8B22-00600806D9B6}")
public interface ISWbemEventSource extends Com4jObject {
    /**
     * Retrieve the next event within a specified time period. The timeout is specified in milliseconds.
     */
    @VTID(7)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject nextEvent(
        @DefaultValue("-1")int iTimeoutMs);

    /**
     * The Security Configurator for this Object
     */
    @VTID(8)
    com.drighetto.wmicom4j.wmijtd.ISWbemSecurity security_();

}
