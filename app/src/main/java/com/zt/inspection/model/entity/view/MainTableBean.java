package com.zt.inspection.model.entity.view;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class MainTableBean {
    private String name;
    private int rountIcon;//圆形标签
    private int icon;//底部标签
    private int selectIcon;//底部标签选择后
    private int position;//对应位置

    public MainTableBean() {
    }
    public MainTableBean(int icon, int selectIcon, String name) {
        this.name = name;
        this.icon = icon;
        this.selectIcon = selectIcon;
    }
    public MainTableBean(String name, int rountIcon, int position) {
        this.name = name;
        this.rountIcon = rountIcon;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRountIcon() {
        return rountIcon;
    }

    public void setRountIcon(int rountIcon) {
        this.rountIcon = rountIcon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }
}
