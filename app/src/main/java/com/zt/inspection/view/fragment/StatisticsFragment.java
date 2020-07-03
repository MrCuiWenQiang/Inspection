package com.zt.inspection.view.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zt.inspection.R;
import com.zt.inspection.contract.StatisticsContract;
import com.zt.inspection.model.entity.response.HomeBean;
import com.zt.inspection.model.entity.response.HomeWorkBean;
import com.zt.inspection.model.entity.response.HomeWorkStatusBean;
import com.zt.inspection.presenter.StatisticsPresenter;
import com.zt.inspection.util.CountUtils;
import com.zt.inspection.view.group.HorizontalBarChartView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.date.DateUtil;

public class StatisticsFragment extends BaseMVPFragment<StatisticsContract.View, StatisticsPresenter>
        implements StatisticsContract.View {
    public static StatisticsFragment newInstance() {
        Bundle args = new Bundle();
        StatisticsFragment fragment = new StatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView imTop;
    private TextView tvStatusTitle;
    private TextView tvXc;
    private TextView tvShb;
    private ImageView iv_select;

    private HomeBean data;
    private HorizontalBarChartView hBarChart;
    private PieChart mPieChart;


    private String[] selectV = new String[]{"年统计", "月统计"};

    private int type = 0;


    @Override
    public int getLayoutId() {
        return R.layout.fg_statistics;
    }

    @Override
    public void initview(View v) {
        imTop = findViewById(R.id.im_top);
        tvStatusTitle = findViewById(R.id.tv_status_title);
        tvXc = findViewById(R.id.tv_xc);
        tvShb = findViewById(R.id.tv_shb);
        mPieChart = findViewById(R.id.mPieChart);
        hBarChart = findViewById(R.id.hb_chart);
        iv_select = findViewById(R.id.iv_select);
    }

    Integer[] select_y;
    Integer[] select_m;
    String[] select_y_s;
    String[] select_m_s;

    // TODO: 2020/7/3 懒得优化了
    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        int year = DateUtils.getYear();
        int month = DateUtils.getMonth();
        mPresenter.loadData(year, 0);
        List<Integer> selectYS = new ArrayList<>();
        List<Integer> selectMS = new ArrayList<>();
        List<String> selectYSS = new ArrayList<>();
        List<String> selectMSS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            selectYS.add(year - i);
            selectYSS.add(year - i + "年");
        }
        for (int i = 0; i < 12; i++) {
            if (month - i <= 0) {
                break;
            }
            selectMS.add(month - i);
            selectMSS.add(month - i + "月");
        }
        select_y = selectYS.toArray(new Integer[]{});
        select_m = selectMS.toArray(new Integer[]{});
        select_y_s = selectYSS.toArray(new String[]{});
        select_m_s = selectMSS.toArray(new String[]{});
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog("模式选择", selectV, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        type = which;
                        if (which == 0) {
                            showLoading();
                            mPresenter.loadData(Integer.valueOf(DateUtil.getYear()), 0);
                        } else if (which == 1) {
                            showLoading();
                            mPresenter.loadData(DateUtils.getYear(), DateUtils.getMonth());
                        }
                    }
                });
            }
        });
        tvStatusTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    showListDialog("年份选择", select_y_s, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            mPresenter.loadData(select_y[which], 0);
                        }
                    });
                } else {
                    showListDialog("月份选择", select_m_s, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            showLoading();
                            mPresenter.loadData(DateUtils.getYear(), select_m[which]);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void loadSuccess(int year, int month, HomeBean data) {
        dimiss();
        this.data = data;
        tvXc.setText(String.valueOf(data.getInspectionZS()));
        tvShb.setText(String.valueOf(data.getCaseZS()));
        if (type == 0) {
            tvStatusTitle.setText(year + "年统计");
        } else {
            tvStatusTitle.setText(month + "月统计");
        }
        initPieChart();
        initHBarChart();
    }

    private void initPieChart() {

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(2f);

        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 5, 5, 5);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文字
//        mPieChart.setCenterText(generateCenterSpannableText());

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        if (data.getTypecaseZS() != null) {
            for (HomeWorkBean item : data.getTypecaseZS()) {
                entries.add(new PieEntry(item.getZS(), item.getCNAME()));
            }
        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : CountUtils.LIBERTY_COLORS)
            colors.add(c);

        dataSet.setColors(colors);
        PieData Pdata = new PieData(dataSet);
        Pdata.setValueFormatter(new PercentFormatter());
        Pdata.setValueTextSize(11f);
        Pdata.setValueTextColor(Color.WHITE);
        mPieChart.setData(Pdata);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();


        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);

        mPieChart.animateY(1400);

    }

    private void initHBarChart() {
        List<BarEntry> list = new ArrayList<>();
        List<String> tablist = new ArrayList<>();
        if (data.getDEPARTCase() != null) {
//            for (int i = 0; i < data.getDEPARTCase().size(); i++) {
            for (int i = 0; i < data.getDEPARTCase().size(); i++) {
                HomeWorkStatusBean item = data.getDEPARTCase().get(i);
                BarEntry barEntry = new BarEntry(i, CountUtils.getScaleValue(item.getCSTATEEComplete(), item.getCSTATENOComplete()));
                list.add(barEntry);
                tablist.add(item.getDEPARTNAME());
            }
            hBarChart.setBarDataSet(Color.parseColor("#508EF9"), list.size(), list, tablist.toArray(new String[]{}));
        }
    }


    @Override
    public void loadFail(String message) {
        dimiss();
        ToastUtility.showToast(message);
    }
}
