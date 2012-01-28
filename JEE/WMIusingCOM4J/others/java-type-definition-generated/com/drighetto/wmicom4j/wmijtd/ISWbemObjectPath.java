package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * An Object path
 */
@IID("{5791BC27-CE9C-11D1-97BF-0000F81E849C}")
public interface ISWbemObjectPath extends Com4jObject {
    /**
     * The full path
     */
    @VTID(7)
    @DefaultMethod
    java.lang.String path();

    /**
     * The full path
     */
    @VTID(8)
    @DefaultMethod
    void path(
        java.lang.String strPath);

    /**
     * The relative path
     */
    @VTID(9)
    java.lang.String relPath();

    /**
     * The relative path
     */
    @VTID(10)
    void relPath(
        java.lang.String strRelPath);

    /**
     * The name of the Server
     */
    @VTID(11)
    java.lang.String server();

    /**
     * The name of the Server
     */
    @VTID(12)
    void server(
        java.lang.String strServer);

    /**
     * The Namespace path
     */
    @VTID(13)
    java.lang.String namespace();

    /**
     * The Namespace path
     */
    @VTID(14)
    void namespace(
        java.lang.String strNamespace);

    /**
     * The parent Namespace path
     */
    @VTID(15)
    java.lang.String parentNamespace();

    /**
     * The Display Name for this path
     */
    @VTID(16)
    java.lang.String displayName();

    /**
     * The Display Name for this path
     */
    @VTID(17)
    void displayName(
        java.lang.String strDisplayName);

    /**
     * The Class name
     */
    @VTID(18)
    java.lang.String _class();

    /**
     * The Class name
     */
    @VTID(19)
    void _class(
        java.lang.String strClass);

    /**
     * Indicates whether this path addresses a Class
     */
    @VTID(20)
    boolean isClass();

    /**
     * Coerce this path to address a Class
     */
    @VTID(21)
    void setAsClass();

    /**
     * Indicates whether this path addresses a Singleton Instance
     */
    @VTID(22)
    boolean isSingleton();

    /**
     * Coerce this path to address a Singleton Instance
     */
    @VTID(23)
    void setAsSingleton();

    /**
     * The collection of Key value bindings for this path
     */
    @VTID(24)
    com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet keys();

    @VTID(24)
    @ReturnValue(defaultPropertyThrough={com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet.class})
    com.drighetto.wmicom4j.wmijtd.ISWbemNamedValue keys(
        java.lang.String strName,
        @DefaultValue("0")int iFlags);

    /**
     * Defines the security components of this path
     */
    @VTID(25)
    com.drighetto.wmicom4j.wmijtd.ISWbemSecurity security_();

    /**
     * Defines locale component of this path
     */
    @VTID(26)
    java.lang.String locale();

    /**
     * Defines locale component of this path
     */
    @VTID(27)
    void locale(
        java.lang.String strLocale);

    /**
     * Defines authentication authority component of this path
     */
    @VTID(28)
    java.lang.String authority();

    /**
     * Defines authentication authority component of this path
     */
    @VTID(29)
    void authority(
        java.lang.String strAuthority);

}
