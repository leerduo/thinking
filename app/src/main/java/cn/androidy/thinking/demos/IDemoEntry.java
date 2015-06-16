package cn.androidy.thinking.demos;

import android.content.Context;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public interface IDemoEntry {

    //demo标题
    public String getDemoTitle();

    //演示demo
    public void demonstrate(Context context);
}
