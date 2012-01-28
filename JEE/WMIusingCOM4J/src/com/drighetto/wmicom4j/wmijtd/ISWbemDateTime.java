package com.drighetto.wmicom4j.wmijtd  ;

import com4j.*;

/**
 * A Datetime
 */
@IID("{5E97458A-CF77-11D3-B38F-00105A1F473A}")
public interface ISWbemDateTime extends Com4jObject {
    /**
     * The DMTF datetime
     */
    @VTID(7)
    @DefaultMethod
    java.lang.String value();

    /**
     * The DMTF datetime
     */
    @VTID(8)
    @DefaultMethod
    void value(
        java.lang.String strValue);

    /**
     * The Year component of the value (must be in the range 0-9999)
     */
    @VTID(9)
    int year();

    /**
     * The Year component of the value (must be in the range 0-9999)
     */
    @VTID(10)
    void year(
        int iYear);

    /**
     * Whether the Year component is specified
     */
    @VTID(11)
    boolean yearSpecified();

    /**
     * Whether the Year component is specified
     */
    @VTID(12)
    void yearSpecified(
        boolean bYearSpecified);

    /**
     * The Month component of the value (must be in the range 1-12)
     */
    @VTID(13)
    int month();

    /**
     * The Month component of the value (must be in the range 1-12)
     */
    @VTID(14)
    void month(
        int iMonth);

    /**
     * Whether the Month component is specified
     */
    @VTID(15)
    boolean monthSpecified();

    /**
     * Whether the Month component is specified
     */
    @VTID(16)
    void monthSpecified(
        boolean bMonthSpecified);

    /**
     * The Day component of the value (must be in the range 1-31, or 0-999999 for interval values)
     */
    @VTID(17)
    int day();

    /**
     * The Day component of the value (must be in the range 1-31, or 0-999999 for interval values)
     */
    @VTID(18)
    void day(
        int iDay);

    /**
     * Whether the Day component is specified
     */
    @VTID(19)
    boolean daySpecified();

    /**
     * Whether the Day component is specified
     */
    @VTID(20)
    void daySpecified(
        boolean bDaySpecified);

    /**
     * The Hours component of the value (must be in the range 0-23)
     */
    @VTID(21)
    int hours();

    /**
     * The Hours component of the value (must be in the range 0-23)
     */
    @VTID(22)
    void hours(
        int iHours);

    /**
     * Whether the Hours component is specified
     */
    @VTID(23)
    boolean hoursSpecified();

    /**
     * Whether the Hours component is specified
     */
    @VTID(24)
    void hoursSpecified(
        boolean bHoursSpecified);

    /**
     * The Minutes component of the value (must be in the range 0-59)
     */
    @VTID(25)
    int minutes();

    /**
     * The Minutes component of the value (must be in the range 0-59)
     */
    @VTID(26)
    void minutes(
        int iMinutes);

    /**
     * Whether the Minutes component is specified
     */
    @VTID(27)
    boolean minutesSpecified();

    /**
     * Whether the Minutes component is specified
     */
    @VTID(28)
    void minutesSpecified(
        boolean bMinutesSpecified);

    /**
     * The Seconds component of the value (must be in the range 0-59)
     */
    @VTID(29)
    int seconds();

    /**
     * The Seconds component of the value (must be in the range 0-59)
     */
    @VTID(30)
    void seconds(
        int iSeconds);

    /**
     * Whether the Seconds component is specified
     */
    @VTID(31)
    boolean secondsSpecified();

    /**
     * Whether the Seconds component is specified
     */
    @VTID(32)
    void secondsSpecified(
        boolean bSecondsSpecified);

    /**
     * The Microseconds component of the value (must be in the range 0-999999)
     */
    @VTID(33)
    int microseconds();

    /**
     * The Microseconds component of the value (must be in the range 0-999999)
     */
    @VTID(34)
    void microseconds(
        int iMicroseconds);

    /**
     * Whether the Microseconds component is specified
     */
    @VTID(35)
    boolean microsecondsSpecified();

    /**
     * Whether the Microseconds component is specified
     */
    @VTID(36)
    void microsecondsSpecified(
        boolean bMicrosecondsSpecified);

    /**
     * The UTC component of the value (must be in the range -720 to 720)
     */
    @VTID(37)
    int utc();

    /**
     * The UTC component of the value (must be in the range -720 to 720)
     */
    @VTID(38)
    void utc(
        int iUTC);

    /**
     * Whether the UTC component is specified
     */
    @VTID(39)
    boolean utcSpecified();

    /**
     * Whether the UTC component is specified
     */
    @VTID(40)
    void utcSpecified(
        boolean bUTCSpecified);

    /**
     * Indicates whether this value describes an absolute date and time or is an interval
     */
    @VTID(41)
    boolean isInterval();

    /**
     * Indicates whether this value describes an absolute date and time or is an interval
     */
    @VTID(42)
    void isInterval(
        boolean bIsInterval);

    /**
     * Retrieve value in Variant compatible (VT_DATE) format
     */
    @VTID(43)
    java.util.Date getVarDate(
        @DefaultValue("-1")boolean bIsLocal);

    /**
     * Set the value using Variant compatible (VT_DATE) format
     */
    @VTID(44)
    void setVarDate(
        java.util.Date dVarDate,
        @DefaultValue("-1")boolean bIsLocal);

    /**
     * Retrieve value in FILETIME compatible string representation
     */
    @VTID(45)
    java.lang.String getFileTime(
        @DefaultValue("-1")boolean bIsLocal);

    /**
     * Set the value using FILETIME compatible string representation
     */
    @VTID(46)
    void setFileTime(
        java.lang.String strFileTime,
        @DefaultValue("-1")boolean bIsLocal);

}
