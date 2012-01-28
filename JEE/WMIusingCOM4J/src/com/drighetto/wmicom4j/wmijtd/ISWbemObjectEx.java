package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Class or Instance
 */
@IID("{269AD56A-8A67-4129-BC8C-0506DCFE9880}")
public interface ISWbemObjectEx extends com.drighetto.wmicom4j.wmijtd.ISWbemObject {
    /**
     * Refresh this Object
     */
    @VTID(32)
    void refresh_(
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * The collection of System Properties of this Object
     */
    @VTID(33)
    com.drighetto.wmicom4j.wmijtd.ISWbemPropertySet systemProperties_();

    @VTID(33)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemPropertySet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemProperty systemProperties_(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * Retrieve a textual representation of this Object
     */
    @VTID(34)
    java.lang.String getText_(
        com.drighetto.wmicom4j.wmijtd.WbemObjectTextFormatEnum iObjectTextFormat,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Set this Object using the supplied textual representation
     */
    @VTID(35)
    void setFromText_(
        java.lang.String bsText,
        com.drighetto.wmicom4j.wmijtd.WbemObjectTextFormatEnum iObjectTextFormat,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

}
