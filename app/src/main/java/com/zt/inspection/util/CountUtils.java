package com.zt.inspection.util;

import android.graphics.Color;

/**
 * 为解决案件统计问题故起此类
 */
public class CountUtils {

    public static float getScaleValue(float max, float value) {
        if (max == 0) {
            return 0;
        }
        float nowV = value / max;
        return nowV * 100;
    }

    public static final int[] LIBERTY_COLORS = {
            Color.rgb(80, 116, 249),
            Color.rgb(80, 142, 229),
            Color.rgb(93, 119, 254),
            Color.rgb(66, 216, 176),
            Color.rgb(155, 210, 60)
    };
}
