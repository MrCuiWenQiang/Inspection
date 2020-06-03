package com.zt.inspection.model.entity.response;

public class LeaveListBean {
    /// 请假编号，主键id
    private String LEAVE_ID;
    /// 请假人Id
    private String USER_ID;
    /// 请假人姓名
    private String USER_NAME;
    /// 请假类型（病假、事假）
    private String LEAVE_TYPE;
    /// 请假原因
    private String LEAVE_REASON;
    /// 开始日期
    private String START_DATE;
    /// 终止日期
    private String END_DATE;
    /// 流程号
    private String FLOW_NO;
    /// 当前节点编号
    private String CURRENT_NODE;
    /// 状态(0表示草稿，1表示已提交审批，2表示审批结束)
    private String STATE;
    /// 创建时间
    private String CREATTIME;
    /// 创建人
    private String CREATEUSER;
    /// 请假类型（病假、事假）String
    private String LEAVE_TYPEstr;
    /// 状态(0表示草稿，1表示已提交审批，2表示审批结束)
    private String STATEs;
    /// 操作
    private String caozuo;
    /// 审批id
    private String AUDIT_ID;
    /// 审批人id  
    private String USER_IDTLA;
    /// 审批人姓名
    private String USER_NAMETLA;
    /// 审批意见
    private String AUDIT_INFO;
    /// 审批日期
    private String AUDIT_DATE;
    /// 操作
    private String caozuo1;
    /// 审核创建时间
    private String CREATTIMETLA;

    public String getLEAVE_ID() {
        return LEAVE_ID;
    }

    public void setLEAVE_ID(String LEAVE_ID) {
        this.LEAVE_ID = LEAVE_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getLEAVE_TYPE() {
        return LEAVE_TYPE;
    }

    public void setLEAVE_TYPE(String LEAVE_TYPE) {
        this.LEAVE_TYPE = LEAVE_TYPE;
    }

    public String getLEAVE_REASON() {
        return LEAVE_REASON;
    }

    public void setLEAVE_REASON(String LEAVE_REASON) {
        this.LEAVE_REASON = LEAVE_REASON;
    }

    public String getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(String START_DATE) {
        this.START_DATE = START_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getFLOW_NO() {
        return FLOW_NO;
    }

    public void setFLOW_NO(String FLOW_NO) {
        this.FLOW_NO = FLOW_NO;
    }

    public String getCURRENT_NODE() {
        return CURRENT_NODE;
    }

    public void setCURRENT_NODE(String CURRENT_NODE) {
        this.CURRENT_NODE = CURRENT_NODE;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getCREATTIME() {
        return CREATTIME;
    }

    public void setCREATTIME(String CREATTIME) {
        this.CREATTIME = CREATTIME;
    }

    public String getCREATEUSER() {
        return CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER) {
        this.CREATEUSER = CREATEUSER;
    }

    public String getLEAVE_TYPEstr() {
        return LEAVE_TYPEstr;
    }

    public void setLEAVE_TYPEstr(String LEAVE_TYPEstr) {
        this.LEAVE_TYPEstr = LEAVE_TYPEstr;
    }

    public String getSTATEs() {
        return STATEs;
    }

    public void setSTATEs(String STATEs) {
        this.STATEs = STATEs;
    }

    public String getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(String caozuo) {
        this.caozuo = caozuo;
    }

    public String getAUDIT_ID() {
        return AUDIT_ID;
    }

    public void setAUDIT_ID(String AUDIT_ID) {
        this.AUDIT_ID = AUDIT_ID;
    }

    public String getUSER_IDTLA() {
        return USER_IDTLA;
    }

    public void setUSER_IDTLA(String USER_IDTLA) {
        this.USER_IDTLA = USER_IDTLA;
    }

    public String getUSER_NAMETLA() {
        return USER_NAMETLA;
    }

    public void setUSER_NAMETLA(String USER_NAMETLA) {
        this.USER_NAMETLA = USER_NAMETLA;
    }

    public String getAUDIT_INFO() {
        return AUDIT_INFO;
    }

    public void setAUDIT_INFO(String AUDIT_INFO) {
        this.AUDIT_INFO = AUDIT_INFO;
    }

    public String getAUDIT_DATE() {
        return AUDIT_DATE;
    }

    public void setAUDIT_DATE(String AUDIT_DATE) {
        this.AUDIT_DATE = AUDIT_DATE;
    }

    public String getCaozuo1() {
        return caozuo1;
    }

    public void setCaozuo1(String caozuo1) {
        this.caozuo1 = caozuo1;
    }

    public String getCREATTIMETLA() {
        return CREATTIMETLA;
    }

    public void setCREATTIMETLA(String CREATTIMETLA) {
        this.CREATTIMETLA = CREATTIMETLA;
    }
}
