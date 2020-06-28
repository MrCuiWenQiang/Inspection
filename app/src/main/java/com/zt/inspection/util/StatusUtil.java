package com.zt.inspection.util;

public class StatusUtil {
    /**
     *  案件状态 0-待分派，1-施工中,2-施工完，3-已办结：CSTATE
     */
    private static final String MANAGER = "1";


    public static String getName(String status) {
        if ("0".equals(status)){
            return "待分派";
        }else  if ("1".equals(status)){
            return "施工中";
        }else  if ("2".equals(status)){
            return "施工完";
        }else  if ("3".equals(status)){
            return "已办结";
        }else {
            return "未知";
        }
    }

    public static boolean isFinsh(String status) {
          if ("2".equals(status)){
            return true;
        }else  if ("3".equals(status)){
            return true;
        }else {
            return false;
        }
    }
}
