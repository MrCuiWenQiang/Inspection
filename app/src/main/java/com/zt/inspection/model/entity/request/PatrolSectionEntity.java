package com.zt.inspection.model.entity.request;

/**
 * USERID	String	是	要查询的用户id
 * DEPARTID	String	是	部门id
 * SIGNTIME	String	是	巡检开始打卡时间
 * PATROLSECTIONNAME	String	是	巡查名称
 */
public class PatrolSectionEntity {
    private String USERID;
    private String DEPARTID;
    private String PATROLSECTIONNAME;

    public PatrolSectionEntity() {
    }

    public PatrolSectionEntity(String USERID, String DEPARTID, String PATROLSECTIONNAME) {
        this.USERID = USERID;
        this.DEPARTID = DEPARTID;
        this.PATROLSECTIONNAME = PATROLSECTIONNAME;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
    }

    public String getPATROLSECTIONNAME() {
        return PATROLSECTIONNAME;
    }

    public void setPATROLSECTIONNAME(String PATROLSECTIONNAME) {
        this.PATROLSECTIONNAME = PATROLSECTIONNAME;
    }
}
