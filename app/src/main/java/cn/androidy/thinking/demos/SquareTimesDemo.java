package cn.androidy.thinking.demos;

import android.content.Context;
import android.content.Intent;

import cn.androidy.thinking.CalendarPickerViewActivity;

/**
 * Created by Rick Meng on 2015/6/17.
 */
public class SquareTimesDemo implements IDemoEntry {
    @Override
    public String getDemoTitle() {
        return "日历选择";
    }

    @Override
    public void demonstrate(Context context) {
        context.startActivity(new Intent(context, CalendarPickerViewActivity.class));
    }

    @Override
    public boolean isMember(DemoFamily demoFamily) {
        return demoFamily == DemoFamily.SQUARE || demoFamily == DemoFamily.ALL;
    }
}
