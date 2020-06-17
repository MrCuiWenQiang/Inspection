package com.zt.inspection.model.entity.response;

/**
 * CID：案件ID，
 * TITLE：案件标题，
 * CASENUMBER：案件编码
 * ，CDATETIME：案件上报时间，
 * CTID：	案件状态/0-待分配，1-施工中，2-施工完，4-已办结，
 * SENDTIME：派发时间，
 * HANDLEBEGINTIME：处理开始时间，
 * HANDLEENDTIME：处理完成时间，
 * FEEDBACKTIME：反馈时间，
 * CLOSETIME：完结时间，
 * FEEDBACKCONTENT：反馈内容，
 * CTYPE：问题类型，
 * X：X,
 * Y：Y，
 * PLANNEDTIME：完成计划时间，
 * CREATETIME：创建时间，
 * DEPTID：上报人部门ID，
 * UPUID：上报人ID/外键，
 * SENDUID：派发人ID/外键，
 * HANDLEUID：处理人ID/外键
 */
public class CaseInfoBean {
    private String CID;
    private String TITLE;
    private String CASENUMBER;
    private String CDATETIME;
    private String CTID;
    private String SENDTIME;
    private String HANDLEBEGINTIME;
    private String HANDLEENDTIME;
    private String FEEDBACKTIME;
    private String CLOSETIME;
    private String FEEDBACKCONTENT;
    private String CTYPE;
    private String X;
    private String Y;
    private String PLANNEDTIME;
    private String CREATETIME;
    private String DEPTID;
    private String UPUID;
    private String SENDUID;
    private String HANDLEUID;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCASENUMBER() {
        return CASENUMBER;
    }

    public void setCASENUMBER(String CASENUMBER) {
        this.CASENUMBER = CASENUMBER;
    }

    public String getCDATETIME() {
        return CDATETIME;
    }

    public void setCDATETIME(String CDATETIME) {
        this.CDATETIME = CDATETIME;
    }

    public String getCTID() {
        return CTID;
    }

    public void setCTID(String CTID) {
        this.CTID = CTID;
    }

    public String getSENDTIME() {
        return SENDTIME;
    }

    public void setSENDTIME(String SENDTIME) {
        this.SENDTIME = SENDTIME;
    }

    public String getHANDLEBEGINTIME() {
        return HANDLEBEGINTIME;
    }

    public void setHANDLEBEGINTIME(String HANDLEBEGINTIME) {
        this.HANDLEBEGINTIME = HANDLEBEGINTIME;
    }

    public String getHANDLEENDTIME() {
        return HANDLEENDTIME;
    }

    public void setHANDLEENDTIME(String HANDLEENDTIME) {
        this.HANDLEENDTIME = HANDLEENDTIME;
    }

    public String getFEEDBACKTIME() {
        return FEEDBACKTIME;
    }

    public void setFEEDBACKTIME(String FEEDBACKTIME) {
        this.FEEDBACKTIME = FEEDBACKTIME;
    }

    public String getCLOSETIME() {
        return CLOSETIME;
    }

    public void setCLOSETIME(String CLOSETIME) {
        this.CLOSETIME = CLOSETIME;
    }

    public String getFEEDBACKCONTENT() {
        return FEEDBACKCONTENT;
    }

    public void setFEEDBACKCONTENT(String FEEDBACKCONTENT) {
        this.FEEDBACKCONTENT = FEEDBACKCONTENT;
    }

    public String getCTYPE() {
        return CTYPE;
    }

    public void setCTYPE(String CTYPE) {
        this.CTYPE = CTYPE;
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

    public String getPLANNEDTIME() {
        return PLANNEDTIME;
    }

    public void setPLANNEDTIME(String PLANNEDTIME) {
        this.PLANNEDTIME = PLANNEDTIME;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getDEPTID() {
        return DEPTID;
    }

    public void setDEPTID(String DEPTID) {
        this.DEPTID = DEPTID;
    }

    public String getUPUID() {
        return UPUID;
    }

    public void setUPUID(String UPUID) {
        this.UPUID = UPUID;
    }

    public String getSENDUID() {
        return SENDUID;
    }

    public void setSENDUID(String SENDUID) {
        this.SENDUID = SENDUID;
    }

    public String getHANDLEUID() {
        return HANDLEUID;
    }

    public void setHANDLEUID(String HANDLEUID) {
        this.HANDLEUID = HANDLEUID;
    }
}
