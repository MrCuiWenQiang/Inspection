package com.zt.inspection.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class WordUtil {
    public static String getPinyin(String name){
        char[] chars = name.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        for (int i = 0 ; i < chars.length;i ++){
            if (Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")){
                pinyin.append(PinyinHelper.toHanyuPinyinStringArray(chars[i])[0]);
            }else {
                pinyin.append(chars[i]);
            }
        }
        return pinyin.toString();
    }

    public static void main(String[] args){
        System.out.print(getPinyin("mdk"));
    }
}
