package com.zt.inspection.model.entity.response;

/**
 * PATROLSECTIONID：巡查路段ID，USERID：创建人，DEPARTID：部门id，
 * SIGNTIME：巡			检开始打卡时间，OUTTIME：巡检结束打卡时间，
 * PATROLSECTIONNAME:巡查名称
 */
public class SectionBean {
    private String PATROLSECTIONID;
    private String PATROLSECTIONNAME;
    private String USERID;
    private String DEPARTID;
    private String SIGNTIME;
    private String OUTTIME;

    public String getPATROLSECTIONID() {
        return PATROLSECTIONID;
    }

    public void setPATROLSECTIONID(String PATROLSECTIONID) {
        this.PATROLSECTIONID = PATROLSECTIONID;
    }

    public String getPATROLSECTIONNAME() {
        return PATROLSECTIONNAME;
    }

    public void setPATROLSECTIONNAME(String PATROLSECTIONNAME) {
        this.PATROLSECTIONNAME = PATROLSECTIONNAME;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
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
}
