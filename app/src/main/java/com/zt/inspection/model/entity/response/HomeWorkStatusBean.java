package com.zt.inspection.model.entity.response;

/**
 * CSTATENOComplete
 * String
 * 否
 * 未完成
 *
 * CSTATEEComplete
 * String
 * 否
 * 所有
 *
 * DEPARTNAME
 * String
 * 否
 * 部门
 *
 * DEPARTID
 * String
 * 否
 * 部门ID
 */
public class HomeWorkStatusBean {
    private int CSTATENOComplete;
    private int CSTATEEComplete;
    private String DEPARTNAME;
    private String DEPARTID;

    public int getCSTATENOComplete() {
        return CSTATENOComplete;
    }

    public void setCSTATENOComplete(int CSTATENOComplete) {
        this.CSTATENOComplete = CSTATENOComplete;
    }

    public int getCSTATEEComplete() {
        return CSTATEEComplete;
    }

    public void setCSTATEEComplete(int CSTATEEComplete) {
        this.CSTATEEComplete = CSTATEEComplete;
    }

    public String getDEPARTNAME() {
        return DEPARTNAME;
    }

    public void setDEPARTNAME(String DEPARTNAME) {
        this.DEPARTNAME = DEPARTNAME;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
    }
}
