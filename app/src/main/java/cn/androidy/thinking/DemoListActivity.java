package cn.androidy.thinking;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.androidy.thinking.adapters.DemoAdapter;
import cn.androidy.thinking.demos.IDemoEntry;
import cn.androidy.thinking.demos.LyricDemo;
import cn.androidy.thinking.demos.ThreadPoolDemo;


public class DemoListActivity extends AppCompatActivity implements View.OnClickListener {
    private ActionBar mActionBar;
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private List<IDemoEntry> mList = new ArrayList<IDemoEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mList.add(new ThreadPoolDemo());
        mList.add(new LyricDemo());
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
}
