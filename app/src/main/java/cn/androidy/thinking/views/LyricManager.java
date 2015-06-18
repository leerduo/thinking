package cn.androidy.thinking.views;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick Meng on 2015/6/18.
 */
public class LyricManager {
    private List<Lyric> mLyricList;
    private Rect mTextBound = new Rect();
    private DisplayMetrics dm;

    public LyricManager(List<String> textList, DisplayMetrics displayMetrics) {
        mLyricList = new ArrayList<Lyric>();
        dm = displayMetrics;
        if (textList != null && !textList.isEmpty()) {
            for (String s : textList) {
                mLyricList.add(new Lyric(s));
            }
        }

    }

    public void confirmLyricState(int width, int height, int normalColor, int changeColor, int maxTextWidth, Paint paint) {
        if (mLyricList != null && !mLyricList.isEmpty()) {
            int startY = (int) (dm.density * 20);
            float startProgress = 0;
            for (Lyric lyric : mLyricList) {
                String text = lyric.text;
                measureText(text, paint);
                int textWidth = Math.min(maxTextWidth, measureText(text, paint));
                lyric.textWidth = textWidth;
                lyric.startY = startY += (mTextBound.height() * 1.5f);
                lyric.startX = width / 2 - textWidth / 2;
                lyric.color = normalColor;
                lyric.colorChange = changeColor;
                lyric.height = height;
                lyric.startProgress = startProgress;
                startProgress += lyric.calculateProgressArea(getTotalWidth());
            }
        }
    }

    private int measureText(String text, Paint paint) {
        int w = (int) paint.measureText(text);
        mTextBound = new Rect();
        paint.getTextBounds(text, 0, text.length(), mTextBound);
        return w;
    }


    public void dispatchDraw(Canvas canvas, Paint paint, float progress) {
        if (mLyricList.isEmpty()) {
            return;
        }

        for (Lyric lyric : mLyricList) {
            lyric.onDraw(canvas, paint, progress);
        }
    }

    public void nextLyric() {
    }

    private int getTotalWidth() {
        if (mLyricList.isEmpty()) {
            return 0;
        }
        int result = 0;
        for (Lyric lyric : mLyricList) {
            result += lyric.textWidth;
        }
        return result;
    }
}
