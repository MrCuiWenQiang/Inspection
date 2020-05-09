package com.zt.inspection.model.entity.db;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class UserDBEntity extends LitePalSupport {
    private String name;
    private String password;
    private Date logTimer;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLogTimer() {
        return logTimer;
    }

    public void setLogTimer(Date logTimer) {
        this.logTimer = logTimer;
    }
}
