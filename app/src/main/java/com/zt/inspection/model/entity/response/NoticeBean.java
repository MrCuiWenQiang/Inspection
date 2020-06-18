package com.zt.inspection.model.entity.response;

import java.io.Serializable;

public class NoticeBean implements Serializable {
      /// <summary>
    /// 公告id
    /// </summary>
    private String NID;

    /// <summary>
    /// 标题
    /// </summary>
    private String TITLE;

    /// <summary>
    /// 内容
    /// </summary>
    private String CONTENT;

    /// <summary>
    /// 发布时间
    /// </summary>
    private String CREATEDATE;

    /// <summary>
    /// 发布人id
    /// </summary>
    private String USERID;

    /// <summary>
    /// 发布人姓名
    /// </summary>
    private String USERNAME;

    /// <summary>
    /// 类型：1.通知公告2.美篇图文3.工作消息
    /// </summary>
    private String TYPE;

    /// <summary>
    /// 类型文字
    /// </summary>
    private String TYPEs;


    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPEs() {
        return TYPEs;
    }

    public void setTYPEs(String TYPEs) {
        this.TYPEs = TYPEs;
    }
}
