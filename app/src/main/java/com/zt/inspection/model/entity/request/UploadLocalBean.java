package com.zt.inspection.model.entity.request;

/**
 * X:x轴，Y:y轴，CREATEUSER：创建人，PATROLSECTIONID：巡查路线ID
 */
public class UploadLocalBean {
    private String X;
    private String Y;
    private String PATROLSECTIONID;
    private String CREATEUSER;

    public UploadLocalBean() {
    }

    public UploadLocalBean(String x, String y, String PATROLSECTIONID, String CREATEUSER) {
        X = x;
        Y = y;
        this.PATROLSECTIONID = PATROLSECTIONID;
        this.CREATEUSER = CREATEUSER;
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

    public String getPATROLSECTIONID() {
        return PATROLSECTIONID;
    }

    public void setPATROLSECTIONID(String PATROLSECTIONID) {
        this.PATROLSECTIONID = PATROLSECTIONID;
    }

    public String getCREATEUSER() {
        return CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER) {
        this.CREATEUSER = CREATEUSER;
    }
}
