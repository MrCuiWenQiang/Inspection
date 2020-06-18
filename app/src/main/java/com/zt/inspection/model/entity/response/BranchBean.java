package com.zt.inspection.model.entity.response;

/**
 *
 * PATROLID:id
 * ,PATROLCODE：用户Id，
 * PATROLNAME：用户名称，
 * SIMCODE:手机号,
 * ADDTIME:创建时间,
 * DEPARTID:部门id,
 * EMAIL:邮箱,
 * DEPARTLEADER:是否是部门领导：0：否1：是
 */
public class BranchBean {
    private String PATROLID;
    private String PATROLCODE;
    private String PATROLNAME;
    private String SIMCODE;
    private String ADDTIME;
    private String DEPARTID;
    private String EMAIL;
    private String DEPARTLEADER;

    public String getPATROLID() {
        return PATROLID;
    }

    public void setPATROLID(String PATROLID) {
        this.PATROLID = PATROLID;
    }

    public String getPATROLCODE() {
        return PATROLCODE;
    }

    public void setPATROLCODE(String PATROLCODE) {
        this.PATROLCODE = PATROLCODE;
    }

    public String getPATROLNAME() {
        return PATROLNAME;
    }

    public void setPATROLNAME(String PATROLNAME) {
        this.PATROLNAME = PATROLNAME;
    }

    public String getSIMCODE() {
        return SIMCODE;
    }

    public void setSIMCODE(String SIMCODE) {
        this.SIMCODE = SIMCODE;
    }

    public String getADDTIME() {
        return ADDTIME;
    }

    public void setADDTIME(String ADDTIME) {
        this.ADDTIME = ADDTIME;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDEPARTLEADER() {
        return DEPARTLEADER;
    }

    public void setDEPARTLEADER(String DEPARTLEADER) {
        this.DEPARTLEADER = DEPARTLEADER;
    }
}
