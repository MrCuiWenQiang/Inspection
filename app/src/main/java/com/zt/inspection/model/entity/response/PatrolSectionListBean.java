package com.zt.inspection.model.entity.response;

import java.util.List;

public class PatrolSectionListBean {
    private List<PatrolRlistBean> RList;
    private String DEPARTNAME;
    private String OUTTIME;
    public List<PatrolRlistBean> getRList() {
        return RList;
    }

    public void setRList(List<PatrolRlistBean> RList) {
        this.RList = RList;
    }

    public String getDEPARTNAME() {
        return DEPARTNAME;
    }

    public void setDEPARTNAME(String DEPARTNAME) {
        this.DEPARTNAME = DEPARTNAME;
    }

    public String getOUTTIME() {
        return OUTTIME;
    }

    public void setOUTTIME(String OUTTIME) {
        this.OUTTIME = OUTTIME;
    }
}
