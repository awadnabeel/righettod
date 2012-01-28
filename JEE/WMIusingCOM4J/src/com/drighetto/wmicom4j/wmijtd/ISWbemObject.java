package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Class or Instance
 */
@IID("{76A6415A-CB41-11D1-8B02-00600806D9B6}")
public interface ISWbemObject extends Com4jObject {
    /**
     * Save this Object
     */
    @VTID(7)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectPath put_(
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Save this Object asynchronously
     */
    @VTID(8)
    void putAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Delete this Object
     */
    @VTID(9)
    void delete_(
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Delete this Object asynchronously
     */
    @VTID(10)
    void deleteAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Return all instances of this Class
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet instances_(
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Return all instances of this Class asynchronously
     */
    @VTID(12)
    void instancesAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Enumerate subclasses of this Class
     */
    @VTID(13)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet subclasses_(
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Enumerate subclasses of this Class asynchronously
     */
    @VTID(14)
    void subclassesAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Get the Associators of this Object
     */
    @VTID(15)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet associators_(
        @DefaultValue("")java.lang.String strAssocClass,
        @DefaultValue("")java.lang.String strResultClass,
        @DefaultValue("")java.lang.String strResultRole,
        @DefaultValue("")java.lang.String strRole,
        @DefaultValue("0")boolean bClassesOnly,
        @DefaultValue("0")boolean bSchemaOnly,
        @DefaultValue("")java.lang.String strRequiredAssocQualifier,
        @DefaultValue("")java.lang.String strRequiredQualifier,
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Get the Associators of this Object asynchronously
     */
    @VTID(16)
    void associatorsAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("")java.lang.String strAssocClass,
        @DefaultValue("")java.lang.String strResultClass,
        @DefaultValue("")java.lang.String strResultRole,
        @DefaultValue("")java.lang.String strRole,
        @DefaultValue("0")boolean bClassesOnly,
        @DefaultValue("0")boolean bSchemaOnly,
        @DefaultValue("")java.lang.String strRequiredAssocQualifier,
        @DefaultValue("")java.lang.String strRequiredQualifier,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Get the References to this Object
     */
    @VTID(17)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet references_(
        @DefaultValue("")java.lang.String strResultClass,
        @DefaultValue("")java.lang.String strRole,
        @DefaultValue("0")boolean bClassesOnly,
        @DefaultValue("0")boolean bSchemaOnly,
        @DefaultValue("")java.lang.String strRequiredQualifier,
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Get the References to this Object asynchronously
     */
    @VTID(18)
    void referencesAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("")java.lang.String strResultClass,
        @DefaultValue("")java.lang.String strRole,
        @DefaultValue("0")boolean bClassesOnly,
        @DefaultValue("0")boolean bSchemaOnly,
        @DefaultValue("")java.lang.String strRequiredQualifier,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Execute a Method of this Object
     */
    @VTID(19)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject execMethod_(
        java.lang.String strMethodName,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemInParameters,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Execute a Method of this Object asynchronously
     */
    @VTID(20)
    void execMethodAsync_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strMethodName,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemInParameters,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Clone this Object
     */
    @VTID(21)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject clone_();

    /**
     * Get the MOF text of this Object
     */
    @VTID(22)
    java.lang.String getObjectText_(
        @DefaultValue("0")int iFlags);

    /**
     * Create a subclass of this Object
     */
    @VTID(23)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject spawnDerivedClass_(
        @DefaultValue("0")int iFlags);

    /**
     * Create an Instance of this Object
     */
    @VTID(24)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject spawnInstance_(
        @DefaultValue("0")int iFlags);

    /**
     * Compare this Object with another
     */
    @VTID(25)
    boolean compareTo_(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemObject,
        @DefaultValue("0")int iFlags);

    /**
     * The collection of Qualifiers of this Object
     */
    @VTID(26)
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifierSet qualifiers_();

    @VTID(26)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemQualifierSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemQualifier qualifiers_(
        java.lang.String name,
        @DefaultValue("0")int iFlags);

    /**
     * The collection of Properties of this Object
     */
    @VTID(27)
    com.drighetto.wmicom4j.wmijtd.ISWbemPropertySet properties_();

    @VTID(27)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemPropertySet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemProperty properties_(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * The collection of Methods of this Object
     */
    @VTID(28)
    com.drighetto.wmicom4j.wmijtd.ISWbemMethodSet methods_();

    @VTID(28)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemMethodSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemMethod methods_(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * An array of strings describing the class derivation heirarchy, in most-derived-from order (the first element in the array defines the superclass and the last element defines the dynasty class).
     */
    @VTID(29)
    @ReturnValue(type=NativeType.VARIANT)
    java.lang.Object derivation_();

    /**
     * The path of this Object
     */
    @VTID(30)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectPath path_();

    /**
     * The Security Configurator for this Object
     */
    @VTID(31)
    com.drighetto.wmicom4j.wmijtd.ISWbemSecurity security_();

}
