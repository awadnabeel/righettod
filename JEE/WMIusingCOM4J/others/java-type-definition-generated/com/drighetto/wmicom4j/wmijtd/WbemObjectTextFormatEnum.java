package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines object text formats
 */
public enum WbemObjectTextFormatEnum implements ComEnum {
    wbemObjectTextFormatCIMDTD20(1),
    wbemObjectTextFormatWMIDTD20(2),
    ;

    private final int value;
    WbemObjectTextFormatEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
