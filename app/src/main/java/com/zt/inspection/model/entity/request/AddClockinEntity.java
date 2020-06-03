package com.zt.inspection.model.entity.request;

/**
 * USERID:用户编码，SIGNDESC：备注，OUTTIME：1为上班打卡，2为下班打卡
 */
public class AddClockinEntity {
    private String USERID;
    private String SIGNDESC;
    private String OUTTIME;

    public AddClockinEntity() {
    }

    public AddClockinEntity(String USERID, String SIGNDESC, String OUTTIME) {
        this.USERID = USERID;
        this.SIGNDESC = SIGNDESC;
        this.OUTTIME = OUTTIME;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getSIGNDESC() {
        return SIGNDESC;
    }

    public void setSIGNDESC(String SIGNDESC) {
        this.SIGNDESC = SIGNDESC;
    }

    public String getOUTTIME() {
        return OUTTIME;
    }

    public void setOUTTIME(String OUTTIME) {
        this.OUTTIME = OUTTIME;
    }
}
