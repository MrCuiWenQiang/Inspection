package com.zt.inspection.model.entity.request;

/**
 * USERID
 * String
 * 是
 * 用户ID
 *
 * X
 * String
 * 是
 * X
 *
 * Y
 * String
 * 是
 * Y
 *
 * ISLINE
 * String
 * 是
 * 是否在线：0在线，1不在线
 *
 * DEPARTID
 * String
 * 是
 * 部门ID
 *
 * USERNAME
 * String
 * 是
 * 用户名称
 */
public class LocationBean {
    private String USERID;
    private String X;
    private String Y;
    private String ISLINE;
    private String DEPARTID;
    private String USERNAME;

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
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

    public String getISLINE() {
        return ISLINE;
    }

    public void setISLINE(String ISLINE) {
        this.ISLINE = ISLINE;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
}
