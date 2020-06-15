package com.zt.inspection.model.entity.response;

import java.util.List;

public class WorkDeptBean {
    private int errorNumber;
    private int number;
    private List<WorkDeptItemStatusBean> status;

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<WorkDeptItemStatusBean> getStatus() {
        return status;
    }

    public void setStatus(List<WorkDeptItemStatusBean> status) {
        this.status = status;
    }
}
