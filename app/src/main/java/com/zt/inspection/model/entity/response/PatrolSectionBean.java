package com.zt.inspection.model.entity.response;

/**
 * result	String	否	ok成功，error失败
 * id	String	否	巡查路段ID
 */
public class PatrolSectionBean {
    private String result;
    private String id;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
