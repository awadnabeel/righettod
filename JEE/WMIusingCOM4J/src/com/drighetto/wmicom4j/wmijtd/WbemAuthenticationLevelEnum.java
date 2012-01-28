package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines the security authentication level
 */
public enum WbemAuthenticationLevelEnum {
    wbemAuthenticationLevelDefault, // 0
    wbemAuthenticationLevelNone, // 1
    wbemAuthenticationLevelConnect, // 2
    wbemAuthenticationLevelCall, // 3
    wbemAuthenticationLevelPkt, // 4
    wbemAuthenticationLevelPktIntegrity, // 5
    wbemAuthenticationLevelPktPrivacy, // 6
}
