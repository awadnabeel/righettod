package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A collection of Classes or Instances
 */
@IID("{76A6415F-CB41-11D1-8B02-00600806D9B6}")
public interface ISWbemObjectSet extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get an Object with a specific path from this collection
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemObject item(
        java.lang.String strObjectPath,
        @DefaultValue("0")int iFlags);

    /**
     * The number of items in this collection
     */
    @VTID(9)
    int count();

    /**
     * The Security Configurator for this Object
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemSecurity security_();

}
