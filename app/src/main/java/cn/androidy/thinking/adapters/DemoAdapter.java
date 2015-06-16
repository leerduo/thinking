package cn.androidy.thinking.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.androidy.thinking.R;
import cn.androidy.thinking.demos.IDemoEntry;
import cn.androidy.thinking.viewholders.DemoItemHolder;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoItemHolder> {

    private List<IDemoEntry> mList;
    private Context mContext;

    public DemoAdapter(Context context, List<IDemoEntry> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public DemoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_item, parent, false);
        return new DemoItemHolder(view);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final DemoItemHolder holder, int position) {
        final View view = holder.mView;
        holder.tvDemoTitle.setText(mList.get(position).getDemoTitle());
        final int p = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(p).demonstrate(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
