package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Asynchronous operation control
 */
@IID("{75718C9F-F029-11D1-A1AC-00C04FB6C223}")
public interface ISWbemSink extends Com4jObject {
    /**
     * Cancel an asynchronous operation
     */
    @VTID(7)
    void cancel();

}
