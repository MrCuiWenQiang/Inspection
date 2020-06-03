package com.zt.inspection.model.entity.response;

import java.sql.Date;

public class LoginBean {
    /// <summary>
    /// 主键
    /// </summary>
    private String ID;

    /// <summary>
    /// 用户名称
    /// </summary>
    private String UserName;
    /// <summary>
    /// 修改人
    /// </summary>
    private String MODIYUSER;
    /// <summary>
    /// 修改人
    /// </summary>
    private String Password_new;
    /// <summary>
    /// 创建人
    /// </summary>
    private String CREATEUSER;
    /// <summary>
    /// 创建时间
    /// </summary>
    private Date CREATETIME;
    /// <summary>
    /// 修改时间
    /// </summary>
    private Date MODIYTIME;

    /// <summary>
    /// 主键
    /// </summary>
    private String PATROLID;

    /// <summary>
    /// 用户Id
    /// </summary>
    private String PATROLCODE;
    /// <summary>
    /// 用户名称
    /// </summary>
    private String PATROLNAME;
    /// <summary>
    /// 手机号
    /// </summary>
    private String SIMCODE;
    /// <summary>
    /// 创建时间
    /// </summary>
    private String ADDTIME;
    /// <summary>
    /// 删除状态
    /// </summary>
    private String DELETYPE;
    /// <summary>
    /// 办公电话
    /// </summary>
    private String TEL;
    /// <summary>
    /// 用户头像
    /// </summary>
    private String USERIMAGE;
    /// <summary>
    /// 部门id
    /// </summary>
    private String DEPARTID;
    /// <summary>
    /// 0-男，1-女
    /// </summary>
    private String SEX;
    /// <summary>
    /// 性别字体显示
    /// </summary>
    private String SEXstr;
    /// <summary>
    /// 邮箱
    /// </summary>
    private String EMAIL;
    /// <summary>
    /// 是否在线:0-离线 1-在线 2-忙碌 3-离开
    /// </summary>
    private String STATUS;

    /// <summary>
    /// 密码
    /// </summary>
    private String Password;

    /// <summary>
    /// 状态
    /// </summary>
    private String State;
    /// <summary>
    /// 状态字体显示
    /// </summary>
    private String Statestr;
    /// <summary>
    /// 地址
    /// </summary>
    private String ADDRESS;

    /// <summary>
    /// 用户Id(原来用户id)
    /// </summary>
    private String UserId;

    /// <summary>
    /// 角色id
    /// </summary>
    private String RoleId;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMODIYUSER() {
        return MODIYUSER;
    }

    public void setMODIYUSER(String MODIYUSER) {
        this.MODIYUSER = MODIYUSER;
    }

    public String getPassword_new() {
        return Password_new;
    }

    public void setPassword_new(String password_new) {
        Password_new = password_new;
    }

    public String getCREATEUSER() {
        return CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER) {
        this.CREATEUSER = CREATEUSER;
    }

    public Date getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(Date CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public Date getMODIYTIME() {
        return MODIYTIME;
    }

    public void setMODIYTIME(Date MODIYTIME) {
        this.MODIYTIME = MODIYTIME;
    }

    public String getPATROLID() {
        return PATROLID;
    }

    public void setPATROLID(String PATROLID) {
        this.PATROLID = PATROLID;
    }

    public String getPATROLCODE() {
        return PATROLCODE;
    }

    public void setPATROLCODE(String PATROLCODE) {
        this.PATROLCODE = PATROLCODE;
    }

    public String getPATROLNAME() {
        return PATROLNAME;
    }

    public void setPATROLNAME(String PATROLNAME) {
        this.PATROLNAME = PATROLNAME;
    }

    public String getSIMCODE() {
        return SIMCODE;
    }

    public void setSIMCODE(String SIMCODE) {
        this.SIMCODE = SIMCODE;
    }

    public String getADDTIME() {
        return ADDTIME;
    }

    public void setADDTIME(String ADDTIME) {
        this.ADDTIME = ADDTIME;
    }

    public String getDELETYPE() {
        return DELETYPE;
    }

    public void setDELETYPE(String DELETYPE) {
        this.DELETYPE = DELETYPE;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getUSERIMAGE() {
        return USERIMAGE;
    }

    public void setUSERIMAGE(String USERIMAGE) {
        this.USERIMAGE = USERIMAGE;
    }

    public String getDEPARTID() {
        return DEPARTID;
    }

    public void setDEPARTID(String DEPARTID) {
        this.DEPARTID = DEPARTID;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getSEXstr() {
        return SEXstr;
    }

    public void setSEXstr(String SEXstr) {
        this.SEXstr = SEXstr;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStatestr() {
        return Statestr;
    }

    public void setStatestr(String statestr) {
        Statestr = statestr;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }
}
