package com.zt.inspection.model.entity.request;

public class NoticeEntity {
    /// <param name="txt_search">标题</param>
    /// <param name="start">开始时间</param>
    /// <param name="end">结束时间</param>

    private String txt_search;
    private String start;
    private String end;

    public NoticeEntity() {
    }

    public NoticeEntity(String txt_search, String start, String end) {
        this.txt_search = txt_search;
        this.start = start;
        this.end = end;
    }

    public String getTxt_search() {
        return txt_search;
    }

    public void setTxt_search(String txt_search) {
        this.txt_search = txt_search;
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
}
