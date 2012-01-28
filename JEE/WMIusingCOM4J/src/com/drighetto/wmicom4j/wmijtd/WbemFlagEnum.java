package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines behavior of various interface calls
 */
public enum WbemFlagEnum implements ComEnum {
    wbemFlagReturnImmediately(16),
    wbemFlagReturnWhenComplete(0),
    wbemFlagBidirectional(0),
    wbemFlagForwardOnly(32),
    wbemFlagNoErrorObject(64),
    wbemFlagReturnErrorObject(0),
    wbemFlagSendStatus(128),
    wbemFlagDontSendStatus(0),
    wbemFlagEnsureLocatable(256),
    wbemFlagDirectRead(512),
    wbemFlagSendOnlySelected(0),
    wbemFlagUseAmendedQualifiers(131072),
    wbemFlagGetDefault(0),
    wbemFlagSpawnInstance(1),
    wbemFlagUseCurrentTime(1),
    ;

    private final int value;
    WbemFlagEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
