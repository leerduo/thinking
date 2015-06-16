package cn.androidy.thinking.demos;

import android.content.Context;
import android.content.Intent;

import cn.androidy.thinking.DemoListActivity;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class BaseDemo implements IDemoEntry {
    @Override
    public String getDemoTitle() {
        return "基本Demo";
    }

    @Override
    public void demonstrate(Context context) {
        context.startActivity(new Intent(context, DemoListActivity.class));
    }
}
