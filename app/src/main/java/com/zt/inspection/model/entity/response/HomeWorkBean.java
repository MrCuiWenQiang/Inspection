package com.zt.inspection.model.entity.response;

/**
 * CID
 * String
 * 否
 * 案件类型ID
 *
 * CNAME
 * String
 * 否
 * 案件类型
 *
 * ZS
 * String
 * 否
 * 案件类型数量
 */
public class HomeWorkBean {
    private String CID;
    private String CNAME;
    private int ZS;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public int getZS() {
        return ZS;
    }

    public void setZS(int ZS) {
        this.ZS = ZS;
    }
}
