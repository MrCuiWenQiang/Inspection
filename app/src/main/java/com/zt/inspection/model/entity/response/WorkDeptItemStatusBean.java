package com.zt.inspection.model.entity.response;

import java.util.List;

public class WorkDeptItemStatusBean {
    private String name;
    private int type;
    private int errorNumber;
    private int number;
    private int sum;
    private List<WorkDeptItemDataBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<WorkDeptItemDataBean> getList() {
        return list;
    }

    public void setList(List<WorkDeptItemDataBean> list) {
        this.list = list;
    }
}
