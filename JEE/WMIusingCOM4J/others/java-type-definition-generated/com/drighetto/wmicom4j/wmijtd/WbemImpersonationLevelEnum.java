package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines the security impersonation level
 */
public enum WbemImpersonationLevelEnum implements ComEnum {
    wbemImpersonationLevelAnonymous(1),
    wbemImpersonationLevelIdentify(2),
    wbemImpersonationLevelImpersonate(3),
    wbemImpersonationLevelDelegate(4),
    ;

    private final int value;
    WbemImpersonationLevelEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
