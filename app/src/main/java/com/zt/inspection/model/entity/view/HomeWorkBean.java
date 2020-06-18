package com.zt.inspection.model.entity.view;

public class HomeWorkBean {
    private String title;
    private String hint;
    private int icon;
    private String status;//案件状态

    public HomeWorkBean() {
    }

    public HomeWorkBean(String title, String hint, int icon, String status) {
        this.title = title;
        this.hint = hint;
        this.icon = icon;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
