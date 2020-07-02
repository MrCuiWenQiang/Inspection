package com.zt.inspection.model.entity.request;

public class ModifyPOWBean {
    private String Password1;
    private String Password;
    private String PATROLCODE;

    public ModifyPOWBean() {
    }

    public ModifyPOWBean(String password1, String password, String PATROLCODE) {
        Password1 = password1;
        Password = password;
        this.PATROLCODE = PATROLCODE;
    }

    public String getPassword1() {
        return Password1;
    }

    public void setPassword1(String password1) {
        Password1 = password1;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPATROLCODE() {
        return PATROLCODE;
    }

    public void setPATROLCODE(String PATROLCODE) {
        this.PATROLCODE = PATROLCODE;
    }
}
