package com.zt.inspection.model.entity.response;

import java.io.Serializable;

public class ClockInBean implements Serializable {
    /**
     * CLOCKIN_ID：id,
     * CLOCKIN_NAME:名称,
     * DEPARTID:部门id,
     * X:x坐标,
     * Y:y坐标,
     * RADIUS:			半径（M）,
     * SIGNTIME:上班打卡设置时间,
     * OUTTIME:下班打卡设置时间,
     * USERID:创建人,
     * ADDTIME:创建时间
     */
    private String CLOCKIN_ID;
    private String CLOCKIN_NAME;
    private String DEPARTID;
    private String X;
    private String Y;
    private String RADIUS;
    private String SIGNTIME;
    private String OUTTIME;
    private String USERID;
    private String ADDTIME;

    public String getCLOCKIN_ID() {
        return CLOCKIN_ID;
    }

    public void setCLOCKIN_ID(String CLOCKIN_ID) {
        this.CLOCKIN_ID = CLOCKIN_ID;
    }

    public String getCLOCKIN_NAME() {
        return CLOCKIN_NAME;
    }

    public void setCLOCKIN_NAME(String CLOCKIN_NAME) {
        this.CLOCKIN_NAME = CLOCKIN_NAME;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public String getRADIUS() {
        return RADIUS;
    }

    public void setRADIUS(String RADIUS) {
        this.RADIUS = RADIUS;
    }

    public String getSIGNTIME() {
        return SIGNTIME;
    }

    public void setSIGNTIME(String SIGNTIME) {
        this.SIGNTIME = SIGNTIME;
    }

    public String getOUTTIME() {
        return OUTTIME;
    }

    public void setOUTTIME(String OUTTIME) {
        this.OUTTIME = OUTTIME;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getADDTIME() {
        return ADDTIME;
    }

    public void setADDTIME(String ADDTIME) {
        this.ADDTIME = ADDTIME;
    }
}
