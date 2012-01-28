package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Qualifier
 */
@IID("{79B05932-D3B7-11D1-8B06-00600806D9B6}")
public interface ISWbemQualifier extends Com4jObject {
    /**
     * The value of this Qualifier
     */
    @VTID(7)
    @DefaultMethod
    @ReturnValue(type=NativeType.VARIANT)
    java.lang.Object value();

    /**
     * The value of this Qualifier
     */
    @VTID(8)
    @DefaultMethod
    void value(
        java.lang.Object varValue);

    /**
     * The name of this Qualifier
     */
    @VTID(9)
    java.lang.String name();

    /**
     * Indicates whether this Qualifier is local or propagated
     */
    @VTID(10)
    boolean isLocal();

    /**
     * Determines whether this Qualifier can propagate to subclasses
     */
    @VTID(11)
    boolean propagatesToSubclass();

    /**
     * Determines whether this Qualifier can propagate to subclasses
     */
    @VTID(12)
    void propagatesToSubclass(
        boolean bPropagatesToSubclass);

    /**
     * Determines whether this Qualifier can propagate to instances
     */
    @VTID(13)
    boolean propagatesToInstance();

    /**
     * Determines whether this Qualifier can propagate to instances
     */
    @VTID(14)
    void propagatesToInstance(
        boolean bPropagatesToInstance);

    /**
     * Determines whether this Qualifier can be overridden where propagated
     */
    @VTID(15)
    boolean isOverridable();

    /**
     * Determines whether this Qualifier can be overridden where propagated
     */
    @VTID(16)
    void isOverridable(
        boolean bIsOverridable);

    /**
     * Determines whether the value of this Qualifier has been amended
     */
    @VTID(17)
    boolean isAmended();

}
