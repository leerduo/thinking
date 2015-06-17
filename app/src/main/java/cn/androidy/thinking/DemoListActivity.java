package cn.androidy.thinking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.androidy.thinking.adapters.DemoAdapter;
import cn.androidy.thinking.demos.DemoListBuilder;
import cn.androidy.thinking.demos.IDemoEntry;
import cn.androidy.thinking.demos.LyricDemo;
import cn.androidy.thinking.demos.ThreadPoolDemo;


public class DemoListActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_DEMO_FAMILY = "";
    private ActionBar mActionBar;
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private List<IDemoEntry> mList = new ArrayList<IDemoEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getKeyword());
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mList = buildDemoList();
        mRecyclerView.setAdapter(new DemoAdapter(this, mList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

        }
    }

    private List<IDemoEntry> buildDemoList() {
        IDemoEntry.DemoFamily demoFamily = getDemoFamilyFromKeyword();
        return DemoListBuilder.buildDemoList(demoFamily);
    }

    private String getKeyword() {
        Bundle extras = getIntent().getExtras();
        String keyword = extras == null ? null : extras.getString(KEY_DEMO_FAMILY);
        return TextUtils.isEmpty(keyword) ? "Demo列表" : keyword;
    }

    private IDemoEntry.DemoFamily getDemoFamilyFromKeyword() {
        String keyword = getKeyword();
        return IDemoEntry.DemoFamily.getDemoFamilyFromKeyword(keyword);
    }

    public static void startThisActivity(Context context, String keyword) {
        Intent intent = new Intent(context, DemoListActivity.class);
        Bundle extras = new Bundle();
        extras.putString(KEY_DEMO_FAMILY, keyword);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}
