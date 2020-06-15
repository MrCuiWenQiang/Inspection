package com.zt.inspection.model.entity.response;

import java.util.List;

public class WorkUserBean {
    private String name;
    private int type;
    private int sum;
    private float timeSum;
    private List<WorkUserItemBean> list;

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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public float getTimeSum() {
        return timeSum;
    }

    public void setTimeSum(float timeSum) {
        this.timeSum = timeSum;
    }

    public List<WorkUserItemBean> getList() {
        return list;
    }

    public void setList(List<WorkUserItemBean> list) {
        this.list = list;
    }
}
