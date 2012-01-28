package com.drighetto.wmicom4j.wmijtd.events;

import com4j.*;

/**
 * A sink for events arising from asynchronous operations
 */
@IID("{75718CA0-F029-11D1-A1AC-00C04FB6C223}")
public abstract class ISWbemSinkEvents {
    /**
     * Event triggered when an Object is available
     */
    @DISPID(1)
    public void onObjectReady(
        com.drighetto.wmicom4j.wmijtd.ISWbemObject objWbemObject,
        com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet objWbemAsyncContext) {
            throw new UnsupportedOperationException();
    }

    /**
     * Event triggered when an asynchronous operation is completed
     */
    @DISPID(2)
    public void onCompleted(
        com.drighetto.wmicom4j.wmijtd.WbemErrorEnum iHResult,
        com.drighetto.wmicom4j.wmijtd.ISWbemObject objWbemErrorObject,
        com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet objWbemAsyncContext) {
            throw new UnsupportedOperationException();
    }

    /**
     * Event triggered to report the progress of an asynchronous operation
     */
    @DISPID(3)
    public void onProgress(
        int iUpperBound,
        int iCurrent,
        java.lang.String strMessage,
        com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet objWbemAsyncContext) {
            throw new UnsupportedOperationException();
    }

    /**
     * Event triggered when an object path is available following a Put operation
     */
    @DISPID(4)
    public void onObjectPut(
        com.drighetto.wmicom4j.wmijtd.ISWbemObjectPath objWbemObjectPath,
        com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet objWbemAsyncContext) {
            throw new UnsupportedOperationException();
    }

}
