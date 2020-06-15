package com.zt.inspection.model.entity.request;

public class UserIdEntity {
    private String userId;

    public UserIdEntity() {
    }

    public UserIdEntity(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
