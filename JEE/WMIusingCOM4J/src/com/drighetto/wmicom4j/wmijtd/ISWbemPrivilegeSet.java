package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A collection of Privilege Overrides
 */
@IID("{26EE67BF-5804-11D2-8B4A-00600806D9B6}")
public interface ISWbemPrivilegeSet extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get a named Privilege from this collection
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemPrivilege item(
        com.drighetto.wmicom4j.wmijtd.WbemPrivilegeEnum iPrivilege);

    /**
     * The number of items in this collection
     */
    @VTID(9)
    int count();

    /**
     * Add a Privilege to this collection
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemPrivilege add(
        com.drighetto.wmicom4j.wmijtd.WbemPrivilegeEnum iPrivilege,
        @DefaultValue("-1")boolean bIsEnabled);

    /**
     * Remove a Privilege from this collection
     */
    @VTID(11)
    void remove(
        com.drighetto.wmicom4j.wmijtd.WbemPrivilegeEnum iPrivilege);

    /**
     * Delete all items in this collection
     */
    @VTID(12)
    void deleteAll();

    /**
     * Add a named Privilege to this collection
     */
    @VTID(13)
    com.drighetto.wmicom4j.wmijtd.ISWbemPrivilege addAsString(
        java.lang.String strPrivilege,
        @DefaultValue("-1")boolean bIsEnabled);

}
