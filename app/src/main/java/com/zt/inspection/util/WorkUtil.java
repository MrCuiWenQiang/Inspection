package com.zt.inspection.util;

import com.zt.inspection.R;

public class WorkUtil {

    public static String typeName(int type){
        String sv = null;
        switch (type) {
            case 0: {
                sv = "正常";
                break;
            }
            case 1: {
                sv = "缺卡";
                break;
            }
            case 2: {
                sv = "迟到";
                break;
            }
            case 3: {
                sv = "早退";
                break;
            }
            case 4: {
                sv = "旷工";
                break;
            }
            default: {
                sv = "正常";
            }
        }
        return sv;
    }
    public static int typeColor(int type){
        int sv = R.color.ablue;
        switch (type) {
            case 0: {
                sv = R.color.ablue;
                break;
            }
            case 1: {
                sv = R.color.tt;
                break;
            }
            case 2: {
                sv = R.color.tt;
                break;
            }
            case 3: {
                sv = R.color.tt;
                break;
            }
            case 4: {
                sv = R.color.tt;
                break;
            }
            default: {
                sv = R.color.ablue;
            }
        }
        return sv;
    }
}
