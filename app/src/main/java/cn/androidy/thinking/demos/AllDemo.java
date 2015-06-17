package cn.androidy.thinking.demos;

import android.content.Context;
import android.content.Intent;

import cn.androidy.thinking.DemoListActivity;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class AllDemo implements IDemoEntry {
    private DemoFamily demoFamily;
    private String demoName;

    public AllDemo(String demoName) {
        this.demoName = demoName;
    }

    public AllDemo(DemoFamily demoFamily, String demoName) {
        this.demoFamily = demoFamily;
        this.demoName = demoName;
    }

    @Override
    public String getDemoTitle() {
        return demoName;
    }

    @Override
    public void demonstrate(Context context) {
        DemoListActivity.startThisActivity(context, demoFamily.getName());
    }

    @Override
    public boolean isMember(DemoFamily demoFamily) {
        return demoFamily == this.demoFamily;
    }
}
