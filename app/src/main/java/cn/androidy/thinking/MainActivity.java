package cn.androidy.thinking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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
import cn.androidy.thinking.demos.BaseDemo;
import cn.androidy.thinking.demos.IDemoEntry;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBar mActionBar;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<Integer> mFloatingActionButtonImageResIdList;
    private int mCurrentColorIndex = 0;
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private List<IDemoEntry> mList = new ArrayList<IDemoEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_48pt);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(this);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(this);
        mFloatingActionButtonImageResIdList = new ArrayList<Integer>();
        mFloatingActionButtonImageResIdList.add(R.drawable.ic_favorite_border_white_48dp);
        mFloatingActionButtonImageResIdList.add(R.drawable.ic_favorite_white_48dp);
        mFloatingActionButton.setImageResource(mFloatingActionButtonImageResIdList.get(mCurrentColorIndex));
        mCurrentColorIndex = (mCurrentColorIndex + 1) % mFloatingActionButtonImageResIdList.size();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mList.add(new BaseDemo());
        mRecyclerView.setAdapter(new DemoAdapter(this, mList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(mNavigationView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.action_about) {
            mDrawerLayout.closeDrawers();
            return true;
        } else if (id == R.id.action_settings) {
            mDrawerLayout.closeDrawers();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.floatingActionButton:
                mFloatingActionButton.setImageResource(mFloatingActionButtonImageResIdList.get(mCurrentColorIndex));
                mCurrentColorIndex = (mCurrentColorIndex + 1) % mFloatingActionButtonImageResIdList.size();
                break;
        }
    }
}
