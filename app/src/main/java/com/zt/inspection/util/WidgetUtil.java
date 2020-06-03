package com.zt.inspection.util;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.faker.repaymodel.util.error.ErrorUtil;

/**
 * 此工具初衷是为了根据视图控件自动关联bean实体类的值
 * 不再进行大量的settext逻辑
 * <p>
 * 除了通过变量名或者id去匹配外  可通过对bean 属性值注解去查找
 */
public class WidgetUtil {
    private WidgetListener listener;

    public void bindData(Activity ac, Object ob) {
        Map<String, View> vsMap = getACField(ac);
        if (vsMap == null || vsMap.size() <= 0) {
            return;
        }
        Map<String, Object> dataMap = getValues(ob);
        if (dataMap == null || dataMap.size() <= 0) {
            return;
        }
        if (listener == null) {
            listener = getMyListener();
        }
        for (String vsName : vsMap.keySet()) {

            for (String dataname : dataMap.keySet()) {
                if (listener.isData(vsName, dataname)) {
                    listener.settingData(vsMap.get(vsName), dataMap.get(dataname));
                }
            }
        }

    }

    private WidgetListener getMyListener() {
        WidgetListener widgetListener = new WidgetListener() {
            @Override
            public boolean isData(String viewName, String dataName) {
                dataName = dataName.replace("-","");
                return viewName.indexOf(dataName)>0;
            }

            @Override
            public void settingData(View view, Object data) {
                String value = String.valueOf(data);
                if (view instanceof TextView) {
                    ((TextView) view).setText(value);
                }
            }
        };
        return widgetListener;
    }


    private Map<String, View> getACField(Activity ac) {
        Class ac_C = ac.getClass();
        Field[] ac_fields = ac_C.getDeclaredFields();
        Map<String, View> vsMap = new HashMap<>();
        //先获取ac所有view值
        for (Field item : ac_fields) {
            item.setAccessible(true);
            if (item.getType() != View.class) {
                continue;
            }
            Object value;
            try {
                value = item.get(ac);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                ErrorUtil.showError(e);
                continue;
            }
            if (value != null) {
                vsMap.put(item.getName(), (View) value);
            }
        }
        return vsMap;
    }

    private Map<String, Object> getValues(Object ob) {
        Class c = ob.getClass();
        Field[] fields = c.getFields();
        Map<String, Object> dataMap = new HashMap<>();
        for (Field item : fields) {
            item.setAccessible(true);
            Object value;
            try {
                value = item.get(ob);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                ErrorUtil.showError(e);
                continue;
            }
            if (value == null) continue;
            dataMap.put(item.getName(), value);
        }
        return dataMap;
    }


    public interface WidgetListener {
        boolean isData(String viewName, String dataName);//判断条件

        void settingData(View view, Object data);//设置数据方式
    }
}
