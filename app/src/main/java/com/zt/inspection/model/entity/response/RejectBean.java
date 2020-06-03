package com.zt.inspection.model.entity.response;

/**
 * AUDIT_ID：审批id, LEAVE_ID：请假编号
 * ，主键id,'STATE':'3',  AUDIT_INFO：审批意			见
 */
public class RejectBean {
    private String AUDIT_ID;
    private String LEAVE_ID;
    private String STATE = "3";
    private String AUDIT_INFO;

    public RejectBean() {
    }

    public RejectBean(String AUDIT_ID, String LEAVE_ID, String AUDIT_INFO) {
        this.AUDIT_ID = AUDIT_ID;
        this.LEAVE_ID = LEAVE_ID;
        this.AUDIT_INFO = AUDIT_INFO;
    }

    public String getAUDIT_ID() {
        return AUDIT_ID;
    }

    public void setAUDIT_ID(String AUDIT_ID) {
        this.AUDIT_ID = AUDIT_ID;
    }

    public String getLEAVE_ID() {
        return LEAVE_ID;
    }

    public void setLEAVE_ID(String LEAVE_ID) {
        this.LEAVE_ID = LEAVE_ID;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getAUDIT_INFO() {
        return AUDIT_INFO;
    }

    public void setAUDIT_INFO(String AUDIT_INFO) {
        this.AUDIT_INFO = AUDIT_INFO;
    }
}
