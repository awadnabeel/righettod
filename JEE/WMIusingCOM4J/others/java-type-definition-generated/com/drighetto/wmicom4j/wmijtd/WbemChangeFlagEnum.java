package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines semantics of putting a Class or Instance
 */
public enum WbemChangeFlagEnum implements ComEnum {
    wbemChangeFlagCreateOrUpdate(0),
    wbemChangeFlagUpdateOnly(1),
    wbemChangeFlagCreateOnly(2),
    wbemChangeFlagUpdateCompatible(0),
    wbemChangeFlagUpdateSafeMode(32),
    wbemChangeFlagUpdateForceMode(64),
    wbemChangeFlagStrongValidation(128),
    wbemChangeFlagAdvisory(65536),
    ;

    private final int value;
    WbemChangeFlagEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
