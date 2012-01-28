package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines timeout constants
 */
public enum WbemTimeout implements ComEnum {
    wbemTimeoutInfinite(-1),
    ;

    private final int value;
    WbemTimeout(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
