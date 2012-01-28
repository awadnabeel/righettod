package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A collection of Qualifiers
 */
@IID("{9B16ED16-D3DF-11D1-8B08-00600806D9B6}")
public interface ISWbemQualifierSet extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get a named Qualifier from this collection
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifier item(
        java.lang.String name,
        @DefaultValue("0")int iFlags);

    /**
     * The number of items in this collection
     */
    @VTID(9)
    int count();

    /**
     * Add a Qualifier to this collection
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifier add(
        java.lang.String strName,
        java.lang.Object varVal,
        @DefaultValue("-1")boolean bPropagatesToSubclass,
        @DefaultValue("-1")boolean bPropagatesToInstance,
        @DefaultValue("-1")boolean bIsOverridable,
        @DefaultValue("0")int iFlags);

    /**
     * Remove a Qualifier from this collection
     */
    @VTID(11)
    void remove(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

}
