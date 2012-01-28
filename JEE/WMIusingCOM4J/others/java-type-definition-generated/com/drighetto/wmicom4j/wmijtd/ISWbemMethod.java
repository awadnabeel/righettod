package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Method
 */
@IID("{422E8E90-D955-11D1-8B09-00600806D9B6}")
public interface ISWbemMethod extends Com4jObject {
    /**
     * The name of this Method
     */
    @VTID(7)
    java.lang.String name();

    /**
     * The originating class of this Method
     */
    @VTID(8)
    java.lang.String origin();

    /**
     * The in parameters for this Method.
     */
    @VTID(9)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject inParameters();

    /**
     * The out parameters for this Method.
     */
    @VTID(10)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject outParameters();

    /**
     * The collection of Qualifiers of this Method.
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifierSet qualifiers_();

    @VTID(11)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemQualifierSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifier qualifiers_(
        java.lang.String name,
        @DefaultValue("0")int iFlags);

}
