package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * Defines a privilege
 */
public enum WbemPrivilegeEnum implements ComEnum {
    wbemPrivilegeCreateToken(1),
    wbemPrivilegePrimaryToken(2),
    wbemPrivilegeLockMemory(3),
    wbemPrivilegeIncreaseQuota(4),
    wbemPrivilegeMachineAccount(5),
    wbemPrivilegeTcb(6),
    wbemPrivilegeSecurity(7),
    wbemPrivilegeTakeOwnership(8),
    wbemPrivilegeLoadDriver(9),
    wbemPrivilegeSystemProfile(10),
    wbemPrivilegeSystemtime(11),
    wbemPrivilegeProfileSingleProcess(12),
    wbemPrivilegeIncreaseBasePriority(13),
    wbemPrivilegeCreatePagefile(14),
    wbemPrivilegeCreatePermanent(15),
    wbemPrivilegeBackup(16),
    wbemPrivilegeRestore(17),
    wbemPrivilegeShutdown(18),
    wbemPrivilegeDebug(19),
    wbemPrivilegeAudit(20),
    wbemPrivilegeSystemEnvironment(21),
    wbemPrivilegeChangeNotify(22),
    wbemPrivilegeRemoteShutdown(23),
    wbemPrivilegeUndock(24),
    wbemPrivilegeSyncAgent(25),
    wbemPrivilegeEnableDelegation(26),
    wbemPrivilegeManageVolume(27),
    ;

    private final int value;
    WbemPrivilegeEnum(int value) { this.value=value; }
    public int comEnumValue() { return value; }
}
