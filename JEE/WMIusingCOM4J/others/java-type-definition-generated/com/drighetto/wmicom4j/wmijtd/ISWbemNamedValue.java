package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A named value
 */
@IID("{76A64164-CB41-11D1-8B02-00600806D9B6}")
public interface ISWbemNamedValue extends Com4jObject {
    /**
     * The Value of this Named element
     */
    @VTID(7)
    @DefaultMethod
    @ReturnValue(type=NativeType.VARIANT)
    java.lang.Object value();

    /**
     * The Value of this Named element
     */
    @VTID(8)
    @DefaultMethod
    void value(
        java.lang.Object varValue);

    /**
     * The Name of this Value
     */
    @VTID(9)
    java.lang.String name();

}
