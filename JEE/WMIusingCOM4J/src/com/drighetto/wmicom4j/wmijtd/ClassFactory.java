package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
    private ClassFactory() {} // instanciation is not allowed


    /**
     * Used to obtain Namespace connections
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemLocator createSWbemLocator() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemLocator.class, "{76A64158-CB41-11D1-8B02-00600806D9B6}" );
    }

    /**
     * A collection of Named Values
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet createSWbemNamedValueSet() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemNamedValueSet.class, "{9AED384E-CE8B-11D1-8B05-00600806D9B6}" );
    }

    /**
     * Object Path
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemObjectPath createSWbemObjectPath() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemObjectPath.class, "{5791BC26-CE9C-11D1-97BF-0000F81E849C}" );
    }

    /**
     * The last error on the current thread
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemLastError createSWbemLastError() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemLastError.class, "{C2FEEEAC-CFCD-11D1-8B05-00600806D9B6}" );
    }

    /**
     * A sink for events arising from asynchronous operations
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemSink createSWbemSink() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemSink.class, "{75718C9A-F029-11D1-A1AC-00C04FB6C223}" );
    }

    /**
     * Date & Time
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemDateTime createSWbemDateTime() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemDateTime.class, "{47DFBE54-CF76-11D3-B38F-00105A1F473A}" );
    }

    /**
     * Refresher
     */
    public static com.drighetto.wmicom4j.wmijtd.ISWbemRefresher createSWbemRefresher() {
        return COM4J.createInstance( com.drighetto.wmicom4j.wmijtd.ISWbemRefresher.class, "{D269BF5C-D9C1-11D3-B38F-00105A1F473A}" );
    }
}
