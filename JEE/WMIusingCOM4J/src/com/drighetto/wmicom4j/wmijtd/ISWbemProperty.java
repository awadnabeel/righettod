package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Property
 */
@IID("{1A388F98-D4BA-11D1-8B09-00600806D9B6}")
public interface ISWbemProperty extends Com4jObject {
    /**
     * The value of this Property
     */
    @VTID(7)
    @DefaultMethod
    @ReturnValue(type=NativeType.VARIANT)
    java.lang.Object value();

    /**
     * The value of this Property
     */
    @VTID(8)
    @DefaultMethod
    void value(
        java.lang.Object varValue);

    /**
     * The name of this Property
     */
    @VTID(9)
    java.lang.String name();

    /**
     * Indicates whether this Property is local or propagated
     */
    @VTID(10)
    boolean isLocal();

    /**
     * The originating class of this Property
     */
    @VTID(11)
    java.lang.String origin();

    /**
     * The CIM Type of this Property
     */
    @VTID(12)
    com.drighetto.wmicom4j.wmijtd.WbemCimtypeEnum cimType();

    /**
     * The collection of Qualifiers of this Property
     */
    @VTID(13)
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifierSet qualifiers_();

    @VTID(13)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemQualifierSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifier qualifiers_(
        java.lang.String name,
        @DefaultValue("0")int iFlags);

    /**
     * Indicates whether this Property is an array type
     */
    @VTID(14)
    boolean isArray();

}
