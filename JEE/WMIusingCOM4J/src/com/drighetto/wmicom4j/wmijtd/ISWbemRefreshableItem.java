package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A single item in a Refresher
 */
@IID("{5AD4BF92-DAAB-11D3-B38F-00105A1F473A}")
public interface ISWbemRefreshableItem extends Com4jObject {
    /**
     * The index of this item in the parent refresher
     */
    @VTID(7)
    int index();

    /**
     * The parent refresher
     */
    @VTID(8)
    com.drighetto.wmicom4j.wmijtd.ISWbemRefresher refresher();

    @VTID(8)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemRefresher.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemRefreshableItem refresher(
        int iIndex);

    /**
     * Whether this item represents a single object or an object set
     */
    @VTID(9)
    boolean isSet();

    /**
     * The object
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectEx object();

    /**
     * The object set
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet objectSet();

    @VTID(11)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemObject objectSet(
        java.lang.String strObjectPath,
        @DefaultValue("0")int iFlags);

    /**
     * Remove this item from the parent refresher
     */
    @VTID(12)
    void remove(
        @DefaultValue("0")int iFlags);

}
