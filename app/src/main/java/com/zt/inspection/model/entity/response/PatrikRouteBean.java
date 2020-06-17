package com.zt.inspection.model.entity.response;

/**
 * RID：巡查id，X：X，Y：Y，CREATEUSER：创建人，CREATETIME：创建时间，PATROLSECTIONID：巡查路段ID
 */
public class PatrikRouteBean {
    private String RID;
    private String X;
    private String Y;
    private String CREATEUSER;
    private String CREATETIME;
    private String PATROLSECTIONID;

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
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

    public String getCREATEUSER() {
        return CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER) {
        this.CREATEUSER = CREATEUSER;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getPATROLSECTIONID() {
        return PATROLSECTIONID;
    }

    public void setPATROLSECTIONID(String PATROLSECTIONID) {
        this.PATROLSECTIONID = PATROLSECTIONID;
    }
}
