package com.zt.inspection.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ResurltUtil {
    public static List<String> toPaths(String url,String ps) {
        if (!TextUtils.isEmpty(ps)){
            List<String> photoPaths = new ArrayList<>();
            String[] pss = ps.split(",");
            for (String path: pss ) {
                photoPaths.add( url+path);
            }
            return photoPaths;
        }
        return null;
    }
}
