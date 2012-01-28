package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines content of generated object text
 */
public enum WbemTextFlagEnum implements ComEnum {
    wbemTextFlagNoFlavors(1),
    ;

    private final int value;
    WbemTextFlagEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
