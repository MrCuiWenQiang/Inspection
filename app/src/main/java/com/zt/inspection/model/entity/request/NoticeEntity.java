package com.zt.inspection.model.entity.request;

public class NoticeEntity {
    /// <param name="txt_search">标题</param>
    /// <param name="start">开始时间</param>
    /// <param name="end">结束时间</param>

    private String TYPE;
    private int page;
    private int limit;

    public NoticeEntity() {
    }

    public NoticeEntity(String TYPE, int page, int limit) {
        this.TYPE = TYPE;
        this.page = page;
        this.limit = limit;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
