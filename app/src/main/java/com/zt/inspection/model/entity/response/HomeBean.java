package com.zt.inspection.model.entity.response;

import java.util.List;

/**
 * caseZS
 * String
 * 否
 * 案件总数
 *
 * InspectionZS
 * String
 * 否
 * 巡检总数
 *
 * typecaseZS
 * String
 * 否
 * 类型案件总数集合
 *
 * DEPARTCase
 * String
 * 否
 * 案件部门信息集合
 */
public class HomeBean {
    private int caseZS;
    private int InspectionZS;
    private List<HomeWorkBean> typecaseZS;
    private List<HomeWorkStatusBean> DEPARTCase;

    public int getCaseZS() {
        return caseZS;
    }

    public void setCaseZS(int caseZS) {
        this.caseZS = caseZS;
    }

    public int getInspectionZS() {
        return InspectionZS;
    }

    public void setInspectionZS(int inspectionZS) {
        InspectionZS = inspectionZS;
    }

    public List<HomeWorkBean> getTypecaseZS() {
        return typecaseZS;
    }

    public void setTypecaseZS(List<HomeWorkBean> typecaseZS) {
        this.typecaseZS = typecaseZS;
    }

    public List<HomeWorkStatusBean> getDEPARTCase() {
        return DEPARTCase;
    }

    public void setDEPARTCase(List<HomeWorkStatusBean> DEPARTCase) {
        this.DEPARTCase = DEPARTCase;
    }
}
