package com.zt.inspection.model.entity.response;

public class TodayBean {
    private int type;
    private String remarks;
    private String SIGNTIME;

    public String getSIGNTIME() {
        return SIGNTIME;
    }

    public void setSIGNTIME(String SIGNTIME) {
        this.SIGNTIME = SIGNTIME;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
