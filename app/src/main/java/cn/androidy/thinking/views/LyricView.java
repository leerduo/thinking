package cn.androidy.thinking.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.TextView;

import cn.androidy.thinking.R;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class LyricView extends TextView {
    private Shader shader;
    private Paint paint;
    private DisplayMetrics dm;
    private Bitmap shaderBitmap;

    public LyricView(Context context) {
        super(context);
        init();
    }

    public LyricView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LyricView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dm = getResources().getDisplayMetrics();
        shaderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shader);
        shader = new LinearGradient(100, 0, 400, 0,
                new int[]{0xff000000, 0xff4CAF50}, new float[]{0.1f, 1f}, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setShader(shader);
        paint.setTextSize(24 * dm.density);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("qwertyuiopasdfghjklzxcvbnm", 20 * dm.density, 200 * dm.density, paint);
    }
}
