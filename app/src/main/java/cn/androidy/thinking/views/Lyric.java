package cn.androidy.thinking.views;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Rick Meng on 2015/6/18.
 */
public class Lyric {
    public String text;
    public int startX;
    public int textWidth;
    public int height;
    public float startY;
    public int color;
    public int colorChange;
    public float startProgress;
    public float progressLength;

    public Lyric(String text) {
        this.text = text;
    }

    /**
     * @param canvas
     * @param paint
     * @param progress
     * @return 如果是正在渲染的歌词，返回true。
     */
    public boolean onDraw(Canvas canvas, Paint paint, float progress) {
        float divProgress = 0;
        if (progress > startProgress + progressLength) {
            divProgress = 1.0f;
        } else if (progress < startProgress) {
            divProgress = 0.0f;
        } else {
            divProgress = (progress - startProgress) / progressLength;
        }
        int dividorPosition = (int) (divProgress * textWidth + startX);
        if (divProgress < 1.0f && divProgress > 0) {
            drawText(canvas, paint, colorChange, startX, dividorPosition);
            drawText(canvas, paint, color, dividorPosition, startX + textWidth);
            return true;
        } else {
            drawText(canvas, paint, color, startX, startX + textWidth);
        }
        return false;
    }

    private void drawText(Canvas canvas, Paint paint, int color, int dividorPosition, int endX) {
        paint.setColor(color);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(dividorPosition, 0, endX, height);
        canvas.drawText(text, startX, startY, paint);
        canvas.restore();
    }

    public float calculateProgressArea(int totalWidth) {
        progressLength = 1.0f * textWidth / totalWidth;
        return progressLength;
    }

}
