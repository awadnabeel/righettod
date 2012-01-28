package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A collection of Properties
 */
@IID("{DEA0A7B2-D4BA-11D1-8B09-00600806D9B6}")
public interface ISWbemPropertySet extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get a named Property from this collection
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemProperty item(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * The number of items in this collection
     */
    @VTID(9)
    int count();

    /**
     * Add a Property to this collection
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemProperty add(
        java.lang.String strName,
        com.drighetto.wmicom4j.wmijtd.WbemCimtypeEnum iCimType,
        @DefaultValue("0")boolean bIsArray,
        @DefaultValue("0")int iFlags);

    /**
     * Remove a Property from this collection
     */
    @VTID(11)
    void remove(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

}
