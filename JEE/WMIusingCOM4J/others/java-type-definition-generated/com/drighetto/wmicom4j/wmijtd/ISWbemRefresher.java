package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Collection of Refreshable Objects
 */
@IID("{14D8250E-D9C2-11D3-B38F-00105A1F473A}")
public interface ISWbemRefresher extends Com4jObject,Iterable<Com4jObject> {
    @VTID(7)
    java.util.Iterator<Com4jObject> iterator();

    /**
     * Get an item from this refresher
     */
    @VTID(8)
    @DefaultMethod
    com.drighetto.wmicom4j.wmijtd.ISWbemRefreshableItem item(
        int iIndex);

    /**
     * The number of items in this refresher
     */
    @VTID(9)
    int count();

    /**
     * Add a refreshable instance to this refresher
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemRefreshableItem add(
        com.drighetto.wmicom4j.wmijtd.ISWbemServicesEx objWbemServices,
        java.lang.String bsInstancePath,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Add a refreshable enumerator to this refresher
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.ISWbemRefreshableItem addEnum(
        com.drighetto.wmicom4j.wmijtd.ISWbemServicesEx objWbemServices,
        java.lang.String bsClassName,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Remove an item from this refresher
     */
    @VTID(12)
    void remove(
        int iIndex,
        @DefaultValue("0")int iFlags);

    /**
     * Refresh all items in this collection
     */
    @VTID(13)
    void refresh(
        @DefaultValue("0")int iFlags);

    /**
     * Whether to attempt auto-reconnection to a remote provider
     */
    @VTID(14)
    boolean autoReconnect();

    /**
     * Whether to attempt auto-reconnection to a remote provider
     */
    @VTID(15)
    void autoReconnect(
        boolean bCount);

    /**
     * Delete all items in this collection
     */
    @VTID(16)
    void deleteAll();

}
