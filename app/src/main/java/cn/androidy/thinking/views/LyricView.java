package cn.androidy.thinking.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.androidy.thinking.R;

/**
 * Created by Rick Meng on 2015/6/18.
 */
public class LyricView extends View {
    private Paint mPaint;
    private int mTextSize = sp2px(30);
    private int mTextOriginColor = 0xff000000;
    private int mTextChangeColor = 0xffff0000;
    private int mMaxTextWidth;
    private float mProgress;
    private LyricManager lyricManager;

    public LyricView(Context context) {
        super(context, null);
    }

    public LyricView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.LyricView);
        mTextSize = ta.getDimensionPixelSize(
                R.styleable.LyricView_text_size, mTextSize);
        mTextOriginColor = ta.getColor(
                R.styleable.LyricView_text_origin_color,
                mTextOriginColor);
        mTextChangeColor = ta.getColor(
                R.styleable.LyricView_text_change_color,
                mTextChangeColor);
        mProgress = ta.getFloat(R.styleable.LyricView_progress, 0);
        ta.recycle();

        mPaint.setTextSize(mTextSize);

        List<String> list = new ArrayList<String>();
        list.add("风吹雨成花");
        list.add("时间追不上白马");
        list.add("你年少掌心的梦话");
        list.add("依然紧握着吗");
        list.add("云翻涌成夏");
        list.add(" 眼泪被岁月蒸发");
        list.add("这条路上的你我她");
        list.add(" 有谁迷路了吗");
        lyricManager = new LyricManager(list, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        mMaxTextWidth = width - getPaddingLeft() - getPaddingRight();
        lyricManager.confirmLyricState(getMeasuredWidth(), getMeasuredHeight(), mTextOriginColor, mTextChangeColor, mMaxTextWidth, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        lyricManager.dispatchDraw(canvas, mPaint, mProgress);
    }

    private int sp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dpVal, getResources().getDisplayMetrics());
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

}
