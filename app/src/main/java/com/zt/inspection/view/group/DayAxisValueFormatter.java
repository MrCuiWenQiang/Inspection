package com.zt.inspection.view.group;


/**
 * Created by li on 12/13/18.
 */
public class DayAxisValueFormatter extends ValueFormatter
{
    public static final int DAY=0; //日
    public static final int PERCENT=1; //百分比
    private  String[] mMonths;
    private int type;

    public DayAxisValueFormatter(String[] mMonths,int type) {
        this.mMonths = mMonths;
        this.type=type;
    }

    @Override
    public String getFormattedValue(float value) {
        String s="";

        int days = (int) value;
        switch (type){
            case DAY: s=mMonths[days];break;
            case PERCENT: s=mMonths[days]+"%";break;
        }
        return s;
    }
}
