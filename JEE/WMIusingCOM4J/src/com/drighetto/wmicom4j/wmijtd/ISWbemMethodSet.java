package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A collection of Methods
 */
@IID("{C93BA292-D955-11D1-8B09-00600806D9B6}")
public interface ISWbemMethodSet extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get a named Method from this collection
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemMethod item(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * The number of items in this collection
     */
    @VTID(9)
    int count();

}
