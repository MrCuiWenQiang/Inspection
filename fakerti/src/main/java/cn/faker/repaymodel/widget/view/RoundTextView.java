package cn.faker.repaymodel.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import cn.faker.repaymodel.R;
import cn.faker.repaymodel.util.CustomUtility;

/**
 * 圆形背景的按钮   仿制打卡
 */
public class RoundTextView extends RelativeLayout {

    private int mWidth;
    private int mHeight;

    private Paint mPaint;

    private int windowWidth;
    private int r;//圆半径

    private @ColorRes
    int backColor;

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        windowWidth = CustomUtility.getWindowWidth(getContext()) / 3;

        backColor = R.color.blue;

        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(getContext(), backColor));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth / 2, mHeight / 2, r,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.EXACTLY || heightSpecMode == MeasureSpec.EXACTLY) {
            mWidth = widthSpecSize;
            mHeight = heightSpecSize;
        } else {
//            float defaultSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getContext().getResources().getDisplayMetrics());
//            float defaultSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, windowWidth, getContext().getResources().getDisplayMetrics());
            //先以屏幕宽度为准
            float defaultSize = windowWidth;
            mHeight = (int) defaultSize;
            mWidth = (int) defaultSize;
        }
        if (mWidth < mHeight) {
            mHeight = mWidth;
        } else {
            mWidth = mHeight;
        }

        r = mHeight / 2;
        setMeasuredDimension(mWidth, mHeight);
    }
}
