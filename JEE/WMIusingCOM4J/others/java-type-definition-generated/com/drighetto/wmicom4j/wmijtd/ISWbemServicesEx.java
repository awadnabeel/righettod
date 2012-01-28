package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A connection to a Namespace
 */
@IID("{D2F68443-85DC-427E-91D8-366554CC754C}")
public interface ISWbemServicesEx extends com.drighetto.wmicom4j.wmijtd.ISWbemServices {
    /**
     * Save the Object to this Namespace
     */
    @VTID(26)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectPath put(
        com.drighetto.wmicom4j.wmijtd.ISWbemObjectEx objWbemObject,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Save the Object to this Namespace asynchronously
     */
    @VTID(27)
    void putAsync(
        com.drighetto.wmicom4j.wmijtd.ISWbemSink objWbemSink,
        com.drighetto.wmicom4j.wmijtd.ISWbemObjectEx objWbemObject,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

}
