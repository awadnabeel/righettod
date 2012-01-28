package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A connection to a Namespace
 */
@IID("{76A6415C-CB41-11D1-8B02-00600806D9B6}")
public interface ISWbemServices extends Com4jObject {
    /**
     * Get a single Class or Instance
     */
    @VTID(7)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject get(
        @DefaultValue("")java.lang.String strObjectPath,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Get a single Class or Instance asynchronously
     */
    @VTID(8)
    void getAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("")java.lang.String strObjectPath,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Delete a Class or Instance
     */
    @VTID(9)
    void delete(
        java.lang.String strObjectPath,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Delete a Class or Instance asynchronously
     */
    @VTID(10)
    void deleteAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strObjectPath,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Enumerate the Instances of a Class
     */
    @VTID(11)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet instancesOf(
        java.lang.String strClass,
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Enumerate the Instances of a Class asynchronously
     */
    @VTID(12)
    void instancesOfAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strClass,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Enumerate the subclasses of a Class
     */
    @VTID(13)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet subclassesOf(
        @DefaultValue("")java.lang.String strSuperclass,
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Enumerate the subclasses of a Class asynchronously 
     */
    @VTID(14)
    void subclassesOfAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        @DefaultValue("")java.lang.String strSuperclass,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Execute a Query
     */
    @VTID(15)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet execQuery(
        java.lang.String strQuery,
        @DefaultValue("WQL")java.lang.String strQueryLanguage,
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Execute an asynchronous Query
     */
    @VTID(16)
    void execQueryAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strQuery,
        @DefaultValue("WQL")java.lang.String strQueryLanguage,
        @DefaultValue("0")int lFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Get the Associators of a class or instance
     */
    @VTID(17)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet associatorsOf(
        java.lang.String strObjectPath,
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
     * Get the Associators of a class or instance asynchronously
     */
    @VTID(18)
    void associatorsOfAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strObjectPath,
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
     * Get the References to a class or instance
     */
    @VTID(19)
    com.drighetto.wmicom4j.wmijtd.ISWbemObjectSet referencesTo(
        java.lang.String strObjectPath,
        @DefaultValue("")java.lang.String strResultClass,
        @DefaultValue("")java.lang.String strRole,
        @DefaultValue("0")boolean bClassesOnly,
        @DefaultValue("0")boolean bSchemaOnly,
        @DefaultValue("")java.lang.String strRequiredQualifier,
        @DefaultValue("16")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Get the References to a class or instance asynchronously
     */
    @VTID(20)
    void referencesToAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strObjectPath,
        @DefaultValue("")java.lang.String strResultClass,
        @DefaultValue("")java.lang.String strRole,
        @DefaultValue("0")boolean bClassesOnly,
        @DefaultValue("0")boolean bSchemaOnly,
        @DefaultValue("")java.lang.String strRequiredQualifier,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Execute a Query to receive Notifications
     */
    @VTID(21)
    com.drighetto.wmicom4j.wmijtd.ISWbemEventSource execNotificationQuery(
        java.lang.String strQuery,
        @DefaultValue("WQL")java.lang.String strQueryLanguage,
        @DefaultValue("48")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Execute an asynchronous Query to receive Notifications
     */
    @VTID(22)
    void execNotificationQueryAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strQuery,
        @DefaultValue("WQL")java.lang.String strQueryLanguage,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * Execute a Method
     */
    @VTID(23)
    com.drighetto.wmicom4j.wmijtd.ISWbemObject execMethod(
        java.lang.String strObjectPath,
        java.lang.String strMethodName,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemInParameters,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet);

    /**
     * Execute a Method asynchronously
     */
    @VTID(24)
    void execMethodAsync(
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemSink,
        java.lang.String strObjectPath,
        java.lang.String strMethodName,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemInParameters,
        @DefaultValue("0")int iFlags,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemNamedValueSet,
        @MarshalAs(NativeType.Dispatch) com4j.Com4jObject objWbemAsyncContext);

    /**
     * The Security Configurator for this Object
     */
    @VTID(25)
    com.drighetto.wmicom4j.wmijtd.ISWbemSecurity security_();

}
