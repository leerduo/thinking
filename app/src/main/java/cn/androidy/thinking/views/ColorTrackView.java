package cn.androidy.thinking.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.androidy.thinking.R;

/**
 * @author zhy
 */
public class ColorTrackView extends View {

    private int mTextStartX;

    public enum Direction {
        LEFT, RIGHT;
    }

    private int mDirection = DIRECTION_LEFT;

    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;

    public void setDirection(int direction) {
        mDirection = direction;
    }

    private String mText = "qwertyuiop";
    private Paint mPaint;
    private int mTextSize = sp2px(30);

    private int mTextOriginColor = 0xff000000;
    private int mTextChangeColor = 0xffff0000;

    private Rect mTextBound = new Rect();
    private int mTextWidth;

    private int mMaxTextWidth;

    private float mProgress;

    private LyricManager lyricManager;

    public ColorTrackView(Context context) {
        super(context, null);
    }

    public ColorTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.ColorTrackView);
        mText = ta.getString(R.styleable.ColorTrackView_text);
        mTextSize = ta.getDimensionPixelSize(
                R.styleable.ColorTrackView_text_size, mTextSize);
        mTextOriginColor = ta.getColor(
                R.styleable.ColorTrackView_text_origin_color,
                mTextOriginColor);
        mTextChangeColor = ta.getColor(
                R.styleable.ColorTrackView_text_change_color,
                mTextChangeColor);
        mProgress = ta.getFloat(R.styleable.ColorTrackView_progress, 0);

        mDirection = ta.getInt(R.styleable.ColorTrackView_direction, mDirection);

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
        mTextWidth = Math.min(mMaxTextWidth, measureText(mText));
        mTextStartX = width / 2 - mTextWidth / 2;
        lyricManager.confirmLyricState(getMeasuredWidth(), getMeasuredHeight(), mTextOriginColor, mTextChangeColor, mMaxTextWidth, mPaint);
    }

    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = mTextBound.height();
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingTop() + getPaddingBottom();
    }

    private int measureWidth(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                // result = mTextBound.width();
                result = mTextWidth;
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingLeft() + getPaddingRight();
    }

    private int measureText(String text) {
        int w = (int) mPaint.measureText(text);
        mPaint.getTextBounds(mText, 0, text.length(), mTextBound);
        return w;
    }

    public void reverseColor() {
        int tmp = mTextOriginColor;
        mTextOriginColor = mTextChangeColor;
        mTextChangeColor = tmp;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        lyricManager.dispatchDraw(canvas, mPaint, mProgress);
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        requestLayout();
        invalidate();
    }

    public int getTextOriginColor() {
        return mTextOriginColor;
    }

    public void setTextOriginColor(int mTextOriginColor) {
        this.mTextOriginColor = mTextOriginColor;
        invalidate();
    }

    public int getTextChangeColor() {
        return mTextChangeColor;
    }

    public void setTextChangeColor(int mTextChangeColor) {
        this.mTextChangeColor = mTextChangeColor;
        invalidate();
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    private int sp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dpVal, getResources().getDisplayMetrics());
    }

}
