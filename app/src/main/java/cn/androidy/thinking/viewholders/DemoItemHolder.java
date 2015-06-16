package cn.androidy.thinking.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.androidy.thinking.R;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class DemoItemHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public TextView tvDemoTitle;

    public DemoItemHolder(View view) {
        super(view);
        mView = view;
        tvDemoTitle = (TextView) mView.findViewById(R.id.tvDemoTitle);
    }
}
