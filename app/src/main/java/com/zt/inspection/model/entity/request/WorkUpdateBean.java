package com.zt.inspection.model.entity.request;

/**
 * TITLE：案件名称，
 * CTID：类型ID，CADDRESS：地理位置，
 * CSTATE：案件状态写死0 0-待分派，1-施工中,2-施工完，3-已办结，
 * UPUID：上报人ID，X：X，Y：Y，CTYPE：问题类型，
 * DEPTID：部门ID,WORKLEVEL:工单等级ABCD，巡查表id： PID
 * <p>
 * /// 施工前图片
 * /// </summary>
 * public string SGQIMAGES { get; set; }
 * /// <summary>
 * /// 施工前视频
 * /// </summary>
 * public string SGQVIDEO { get; set; }
 */
public class WorkUpdateBean {
    private String TITLE;
    private String CTID;
    private String CTYPE;
    private String CADDRESS;
    private String CSTATE;
    private String UPUID;
    private String X;
    private String Y;
    private String DEPTID;
    private String WORKLEVEL;
    private String PID;
    private String SGQIMAGES;
    private String SGQVIDEO;
    private String FeedbackContent;

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCTID() {
        return CTID;
    }

    public void setCTID(String CTID) {
        this.CTID = CTID;
    }

    public String getCTYPE() {
        return CTYPE;
    }

    public void setCTYPE(String CTYPE) {
        this.CTYPE = CTYPE;
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

    public String getUPUID() {
        return UPUID;
    }

    public void setUPUID(String UPUID) {
        this.UPUID = UPUID;
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

    public String getDEPTID() {
        return DEPTID;
    }

    public void setDEPTID(String DEPTID) {
        this.DEPTID = DEPTID;
    }

    public String getWORKLEVEL() {
        return WORKLEVEL;
    }

    public void setWORKLEVEL(String WORKLEVEL) {
        this.WORKLEVEL = WORKLEVEL;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

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

    public String getFeedbackContent() {
        return FeedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        FeedbackContent = feedbackContent;
    }
}
