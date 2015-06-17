package cn.androidy.thinking.demos;

import android.content.Context;
import android.content.Intent;

import cn.androidy.thinking.CalendarPickerViewActivity;
import cn.androidy.thinking.RippleActivity;

/**
 * Created by Rick Meng on 2015/6/17.
 */
public class RippleDemo implements IDemoEntry {
    @Override
    public String getDemoTitle() {
        return "水波纹";
    }

    @Override
    public void demonstrate(Context context) {
        context.startActivity(new Intent(context, RippleActivity.class));
    }

    @Override
    public boolean isMember(DemoFamily demoFamily) {
        return demoFamily == DemoFamily.SQUARE || demoFamily == DemoFamily.ALL;
    }
}
