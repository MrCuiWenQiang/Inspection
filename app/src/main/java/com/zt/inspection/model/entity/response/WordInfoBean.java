package com.zt.inspection.model.entity.response;

/**
 * CHID
 * String
 * 否
 * 处理记录id
 *
 * CID
 * String
 * 否
 * 案件表ID
 *
 * SENDUID
 * String
 * 否
 * 派发人
 *
 * HANDLEUID
 * String
 * 否
 * 处理人ID
 *
 * DEPTID
 * String
 * 否
 * 处理人部门ID
 *
 * FKUiD
 * String
 * 否
 * 反馈人ID
 *
 * SENDTIME
 * String
 * 否
 * 派发时间
 *
 * HANDLEBEGINTIME
 * String
 * 否
 * 处理开始时间
 *
 * HANDLEENDTIME
 * String
 * 否
 * 处理完成时间
 *
 * FEEDBACKTIME
 * String
 * 否
 * 反馈时间
 *
 * FEEDBACKCONTENT
 * String
 * 否
 * 反馈内容
 *
 * CREATETIME
 * String
 * 否
 * 创建时间
 *
 * CSTATE
 * String
 * 否
 * 当前状态/
 * 0-待分配，
 * 1-已派发，
 * 2-施工中，
 * 3-已完成，4-已办结
 *
 * SENDUNAME
 * String
 * 否
 * 派发人姓名
 *
 * HANDLEUNAME
 * String
 * 否
 * 处理人ID
 *
 * SGHIMAGES
 * String
 * 否
 * 施工后照片保存
 *
 * SGHVIDEO
 * String
 * 否
 * 施工后的视频
 * url
 * String
 * 网络
 */
public class WordInfoBean {
    private String CHID;
    private String CID;
    private String SENDUID;
    private String HANDLEUID;
    private String DEPTID;
    private String FKUiD;
    private String SENDTIME;
    private String HANDLEBEGINTIME;
    private String HANDLEENDTIME;
    private String FEEDBACKTIME;
    private String FEEDBACKCONTENT;
    private String CREATETIME;
    private String CSTATE;
    private String SENDUNAME;
    private String HANDLEUNAME;
    private String SGHIMAGES;
    private String SGHVIDEO;
    private String url;

    public String getCHID() {
        return CHID;
    }

    public void setCHID(String CHID) {
        this.CHID = CHID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
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

    public String getDEPTID() {
        return DEPTID;
    }

    public void setDEPTID(String DEPTID) {
        this.DEPTID = DEPTID;
    }

    public String getFKUiD() {
        return FKUiD;
    }

    public void setFKUiD(String FKUiD) {
        this.FKUiD = FKUiD;
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

    public String getFEEDBACKCONTENT() {
        return FEEDBACKCONTENT;
    }

    public void setFEEDBACKCONTENT(String FEEDBACKCONTENT) {
        this.FEEDBACKCONTENT = FEEDBACKCONTENT;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getCSTATE() {
        return CSTATE;
    }

    public void setCSTATE(String CSTATE) {
        this.CSTATE = CSTATE;
    }

    public String getSENDUNAME() {
        return SENDUNAME;
    }

    public void setSENDUNAME(String SENDUNAME) {
        this.SENDUNAME = SENDUNAME;
    }

    public String getHANDLEUNAME() {
        return HANDLEUNAME;
    }

    public void setHANDLEUNAME(String HANDLEUNAME) {
        this.HANDLEUNAME = HANDLEUNAME;
    }

    public String getSGHIMAGES() {
        return SGHIMAGES;
    }

    public void setSGHIMAGES(String SGHIMAGES) {
        this.SGHIMAGES = SGHIMAGES;
    }

    public String getSGHVIDEO() {
        return SGHVIDEO;
    }

    public void setSGHVIDEO(String SGHVIDEO) {
        this.SGHVIDEO = SGHVIDEO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
