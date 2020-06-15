package com.zt.inspection.util;

import com.zt.inspection.MyApplication;

public class RoleIdUtil {
    /// 角色id 1:管理员 2:部门领导 3:巡检人员信息 4:施工人员
    private static final String MANAGER = "1";
    private static final String BUMEN = "2";
    private static final String XUNJIAN = "3";
    private static final String SHIGONG = "4";

    public static boolean isManager() {
        return MANAGER.equals(MyApplication.loginUser.getRoleId());
    }

    public static boolean isBUMEN() {
        return BUMEN.equals(MyApplication.loginUser.getRoleId());
    }

    public static boolean isXUNJIAN() {
        return XUNJIAN.equals(MyApplication.loginUser.getRoleId());
    }

    public static boolean isSHIGONG() {
        return SHIGONG.equals(MyApplication.loginUser.getRoleId());
    }

}
