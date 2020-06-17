package com.zt.inspection.model.entity.response;

/**
 *       案件ID:CID
 *         案件标题:TITLE
 *         案件编码:CASENUMBER
 *         案件上报时间: CDATETIME
 *         案件类型:CTID
 *         工单等级:WORKLEVEL
 *         地理位置名称:CADDRESS
 *         案件状态 0-待分派，1-施工中,2-施工完，3-已办结：CSTATE
 *         问题类型：CTYPE
 *         上报人ID：UPUID
 *         派发人ID：SENDUID
 *         上报人姓名：UPUNAME
 *         派发人姓名：SENDUNAME
 *         处理人ID： HANDLEUID
 *         巡查表id： PID
 *         巡查表NAME：PIDNAME
 *         派发时间：SENDTIME
 *         开始处理时间： HANDLEBEGINTIME
 *         处理完成时间：HANDLEENDTIME
 *         反馈时间：FEEDBACKTIME
 *         完结时间：CLOSETIME
 *         反馈内容：FEEDBACKCONTENT
 *         X坐标： X
 *         Y坐标：Y
 *         完成计划时间:PLANNEDTIME
 *         创建时间： CREATETIME
 *         总数量：num
 *         部门ID：DEPTID
 *          施工前图片：SGQIMAGES
 *         施工前视频：SGQVIDEO
 *         施工后图片：SGHIMAGES
 *         施工后视频：SGHVIDEO
 *         返回路径信息：url
 */
public class CaseInfoBean {
    private String CID;
    private String TITLE;
    private String CASENUMBER;
    private String CDATETIME;
    private String CTID;
    private String WORKLEVEL;
    private String CADDRESS;
    private String CSTATE;
    private String CTYPE;
    private String UPUID;
    private String SENDUID;
    private String UPUNAME;
    private String SENDUNAME;
    private String HANDLEUID;
    private String PID;
    private String PIDNAME;
    private String SENDTIME;
    private String HANDLEBEGINTIME;
    private String HANDLEENDTIME;
    private String FEEDBACKTIME;
    private String CLOSETIME;
    private String FEEDBACKCONTENT;
    private String X;
    private String Y;
    private String PLANNEDTIME;
    private String CREATETIME;
    private String SGQIMAGES;
    private String SGQVIDEO;
    private String SGHIMAGES;
    private String SGHVIDEO;
    private String url;

    public String getSGQIMAGES() {
        return SGQIMAGES;
    }

    public void setSGQIMAGES(String SGQIMAGES) {
        this.SGQIMAGES = SGQIMAGES;
    }

    public String getSGQVIDEO() {
        return SGQVIDEO;
    }

    public void setSGQVIDEO(String SGQVIDEO) {
        this.SGQVIDEO = SGQVIDEO;
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

    public String getWORKLEVEL() {
        return WORKLEVEL;
    }

    public void setWORKLEVEL(String WORKLEVEL) {
        this.WORKLEVEL = WORKLEVEL;
    }

    public String getCADDRESS() {
        return CADDRESS;
    }

    public void setCADDRESS(String CADDRESS) {
        this.CADDRESS = CADDRESS;
    }

    public String getCSTATE() {
        return CSTATE;
    }

    public void setCSTATE(String CSTATE) {
        this.CSTATE = CSTATE;
    }

    public String getCTYPE() {
        return CTYPE;
    }

    public void setCTYPE(String CTYPE) {
        this.CTYPE = CTYPE;
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

    public String getUPUNAME() {
        return UPUNAME;
    }

    public void setUPUNAME(String UPUNAME) {
        this.UPUNAME = UPUNAME;
    }

    public String getSENDUNAME() {
        return SENDUNAME;
    }

    public void setSENDUNAME(String SENDUNAME) {
        this.SENDUNAME = SENDUNAME;
    }

    public String getHANDLEUID() {
        return HANDLEUID;
    }

    public void setHANDLEUID(String HANDLEUID) {
        this.HANDLEUID = HANDLEUID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getPIDNAME() {
        return PIDNAME;
    }

    public void setPIDNAME(String PIDNAME) {
        this.PIDNAME = PIDNAME;
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
}
