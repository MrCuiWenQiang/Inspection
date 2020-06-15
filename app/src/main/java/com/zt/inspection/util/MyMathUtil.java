package com.zt.inspection.util;

public class MyMathUtil {
    public static boolean isHave(double x1, double y1, double x2, double y2, double length) {
        double x = Math.pow(Math.abs(x1 - x2), 2);
        double y = Math.pow(Math.abs(y1 - y2), 2);
        return Math.sqrt(x + y) <= length;
    }
}
