package cn.faker.repaymodel.widget.view.date;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.faker.repaymodel.R;
import cn.faker.repaymodel.util.DateUtils;

public class DateSelectView extends FrameLayout implements View.OnClickListener {
    private final int TYPE_MONTH = 2;
    private final int TYPE_WEEK = 3;

    private int select_typo = 3;//默认周

    private TextView tv_m;
    private TextView tv_w;

    private ImageView imLeft;
    private TextView tvDate;
    private ImageView imRight;


    public DateSelectView(Context context) {
        this(context, null);
    }

    public DateSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DateSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_date_select, this, false);
        this.addView(view);
        tv_m = view.findViewById(R.id.tv_m);
        tv_w = view.findViewById(R.id.tv_w);

        imLeft = view.findViewById(R.id.im_left);
        tvDate = view.findViewById(R.id.tv_date);
        imRight = view.findViewById(R.id.im_right);

        tv_m.setOnClickListener(this);
        tv_w.setOnClickListener(this);
        imLeft.setOnClickListener(this);
        imRight.setOnClickListener(this);
    }

    // TODO: 2020/6/14 独特的时间选择功能实现

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.im_left) {

        } else if (i == R.id.im_right) {

        } else if (i == R.id.tv_m) {
            select_o(tv_m);
        } else if (i == R.id.tv_w) {
            select_o(tv_w);
        }
    }

    private void initDate(){
        DateUtils.getCurrentDate(DateUtils.DATE_FORMAT);
    }

    /**
     * 模式选择后界面切换
     *
     * @param tv
     */
    private void select_o(TextView tv) {
        if (tv.equals(tv_m)) {
            select_typo = TYPE_MONTH;
            tv_m.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.navy));
            tv_m.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            tv_w.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            tv_w.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        } else {
            select_typo = TYPE_WEEK;
            tv_w.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.navy));
            tv_w.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            tv_m.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            tv_m.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
    }
}
