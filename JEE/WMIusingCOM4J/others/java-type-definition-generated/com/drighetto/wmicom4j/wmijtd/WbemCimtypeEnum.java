package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines the valid CIM Types of a Property value
 */
public enum WbemCimtypeEnum implements ComEnum {
    wbemCimtypeSint8(16),
    wbemCimtypeUint8(17),
    wbemCimtypeSint16(2),
    wbemCimtypeUint16(18),
    wbemCimtypeSint32(3),
    wbemCimtypeUint32(19),
    wbemCimtypeSint64(20),
    wbemCimtypeUint64(21),
    wbemCimtypeReal32(4),
    wbemCimtypeReal64(5),
    wbemCimtypeBoolean(11),
    wbemCimtypeString(8),
    wbemCimtypeDatetime(101),
    wbemCimtypeReference(102),
    wbemCimtypeChar16(103),
    wbemCimtypeObject(13),
    ;

    private final int value;
    WbemCimtypeEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
