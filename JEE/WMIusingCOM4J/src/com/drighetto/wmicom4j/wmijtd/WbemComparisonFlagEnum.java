package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines settings for object comparison
 */
public enum WbemComparisonFlagEnum implements ComEnum {
    wbemComparisonFlagIncludeAll(0),
    wbemComparisonFlagIgnoreQualifiers(1),
    wbemComparisonFlagIgnoreObjectSource(2),
    wbemComparisonFlagIgnoreDefaultValues(4),
    wbemComparisonFlagIgnoreClass(8),
    wbemComparisonFlagIgnoreCase(16),
    wbemComparisonFlagIgnoreFlavor(32),
    ;

    private final int value;
    WbemComparisonFlagEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
