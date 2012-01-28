package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Used to define connection behavior
 */
public enum WbemConnectOptionsEnum implements ComEnum {
    wbemConnectFlagUseMaxWait(128),
    ;

    private final int value;
    WbemConnectOptionsEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
