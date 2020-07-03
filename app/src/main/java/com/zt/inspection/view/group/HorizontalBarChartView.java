package com.zt.inspection.view.group;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class HorizontalBarChartView extends HorizontalBarChart {

    public HorizontalBarChartView(Context context) {
        super(context);
    }

    public HorizontalBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalBarChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    private void initSetting(int size) {
        /*xy轴设置*/
        //x轴设置显示位置在底部
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxis.setGranularity(1f);
        mXAxis.setLabelCount(size);
        mXAxis.setTextSize(8f);
        //保证y轴从0开始 不然会上移一点
        mAxisLeft.setAxisMinimum(0f);
        mAxisRight.setAxisMinimum(0f);
        mAxisRight.enableGridDashedLine(10f,10f,10f);
        //不显示X轴 Y轴线条
        mXAxis.setDrawAxisLine(false);
        mXAxis.setDrawGridLines(false);
        mXAxis.setGridColor(Color.TRANSPARENT);

        //mAxisLeft.setDrawAxisLine(false);
        mAxisRight.setDrawAxisLine(false);
        mAxisLeft.setDrawGridLines(false);
        mAxisRight.setDrawGridLines(false);
        //不显示左侧Y轴
        mAxisRight.setEnabled(false);
        mAxisLeft.setEnabled(false);
        /*折现图例 标签 设置*/
        mLegend.setForm(Legend.LegendForm.LINE);
        mLegend.setTextSize(0f);
        //显示位置
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        mLegend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        /*是否绘制在表里*/
        mLegend.setDrawInside(false);
        //不显示右下角描述内容
        mDescription.setEnabled(false);
        setDescription(mDescription);

    }


    /*设置图表效果*/
    private void setChartEffect(){
        //不可以手动缩放
        setScaleXEnabled(false);
        setScaleYEnabled(false);
        setScaleEnabled(false);

//        //背景颜色
//        setBackgroundColor(Color.WHITE);
        //不显示图表网格
        setDrawGridBackground(false);
        setDrawBorders(false);

        setHighlightFullBarEnabled(false);
        //显示柱图底层阴影
        setDrawBarShadow(true);
        //最大显示值
        //setMaxVisibleValueCount(100);
        //限制长度在图表内部
        setDrawValueAboveBar(true);
        setPinchZoom(false);

        setFitBars(true);
        //设置动画效果
        animateY(1000,Easing.EasingOption.Linear);
        animateX(1000,Easing.EasingOption.Linear);
    }

    /*固定宽*/
    private void setChartData(int size){
        Matrix m = new Matrix();
        m.postScale(scaleNum(size), 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        getViewPortHandler().refresh(m, this, false);//将图表动画显示之前进行缩放
    }
    //30个横坐标时，缩放4f是正好的。
    private float scalePercent = 3f/30f;

    private float scaleNum(int xCount){
        return xCount * scalePercent;
    }


    public void setBarDataSet(int color, int size, final List<BarEntry> list, String[] v) {
        BarDataSet barDataSet=new BarDataSet(list,"");
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(0f);
        barDataSet.setFormSize(0f);
        barDataSet.setDrawValues(false);
        BarData barData=new BarData(barDataSet);
        barData.setValueTextSize(10f);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(v,DayAxisValueFormatter.DAY);
        mXAxis.setValueFormatter(xAxisFormatter);
        //ValueFormatter dataFormatter = new DayAxisValueFormatter(v,DayAxisValueFormatter.PERCENT);
        barData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((int)entry.getY())+"%";
            }
        });
        barData.setValueTextSize(10f);
        barData.setValueTextColor(Color.WHITE);
        barData.setDrawValues(true);
        if (size<6){
            barData.setBarWidth((float) size/10f);
            setData(barData);
        }else {
            setData(barData);
            setChartData(size);
        }
        setChartEffect();
        initSetting(size);
    }

}
