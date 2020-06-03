package com.zt.inspection.model.entity.request;

/**
 *     strWhere：类型（数值），
 *     start：开始时间，
 *     end：结束时间，
 *     type：“0”，
 *     UserId：用户id
 */
public class LeaveEntity {

    private int strWhere;
    private String start;
    private String end;
    private String type="0";
    private String UserId;

    public int getStrWhere() {
        return strWhere;
    }

    public void setStrWhere(int strWhere) {
        this.strWhere = strWhere;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
