package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A collection of named values
 */
@IID("{CF2376EA-CE8C-11D1-8B05-00600806D9B6}")
public interface ISWbemNamedValueSet extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get a named value from this Collection
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemNamedValue item(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * The number of items in this collection
     */
    @VTID(9)
    int count();

    /**
     * Add a named value to this collection
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemNamedValue add(
        java.lang.String strName,
        java.lang.Object varValue,
        @DefaultValue("0")int iFlags);

    /**
     * Remove a named value from this collection
     */
    @VTID(11)
    void remove(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * Make a copy of this collection
     */
    @VTID(12)
    com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet clone();

    @VTID(12)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemNamedValue clone(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * Delete all items in this collection
     */
    @VTID(13)
    void deleteAll();

}
