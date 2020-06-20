package com.zt.inspection.model.entity.request;

/**
 * CID
 * String
 * 是
 * 案件表ID
 *
 * FEEDBACKCONTENT
 * String
 * 是
 * 反馈内容
 *
 * DEPTID
 * String
 * 是
 * 处理人部门ID
 *
 * CSTATE
 * String
 * 是
 * 当前状态2-未完成,3-已完成
 *
 * HANDLEUID
 * String
 * 是
 * 处理人ID：userid
 *
 * SGHIMAGES
 * String
 * 是
 * 施工后照片保存
 *
 * SGHVIDEO
 * String
 * 是
 * 施工后视频保存
 */
public class AddHandleInfoEntity {
    private String CID;
    private String FEEDBACKCONTENT;
    private String DEPTID;
    private String CSTATE;
    private String HANDLEUID;
    private String SGHIMAGES;
    private String SGHVIDEO;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getFEEDBACKCONTENT() {
        return FEEDBACKCONTENT;
    }

    public void setFEEDBACKCONTENT(String FEEDBACKCONTENT) {
        this.FEEDBACKCONTENT = FEEDBACKCONTENT;
    }

    public String getDEPTID() {
        return DEPTID;
    }

    public void setDEPTID(String DEPTID) {
        this.DEPTID = DEPTID;
    }

    public String getCSTATE() {
        return CSTATE;
    }

    public void setCSTATE(String CSTATE) {
        this.CSTATE = CSTATE;
    }

    public String getHANDLEUID() {
        return HANDLEUID;
    }

    public void setHANDLEUID(String HANDLEUID) {
        this.HANDLEUID = HANDLEUID;
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
}
