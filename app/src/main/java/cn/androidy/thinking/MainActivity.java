package cn.androidy.thinking;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, TabLayout.OnTabSelectedListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBar mActionBar;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<Integer> mFloatingActionButtonImageResIdList;
    private int mCurrentColorIndex = 0;
    private TabLayout mTabLayout;
    private Tab tab1;
    private Tab tab2;
    private Tab tab3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
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
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tab1 = mTabLayout.newTab().setText("Tab1");
        tab1.select();
        tab2 = mTabLayout.newTab().setText("Tab2");
        tab3 = mTabLayout.newTab().setText("Tab3");
        mTabLayout.addTab(tab1);
        mTabLayout.addTab(tab2);
        mTabLayout.addTab(tab3);
        mTabLayout.setOnTabSelectedListener(this);
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
        if (id == R.id.action_drawer_menu) {
            mDrawerLayout.openDrawer(mNavigationView);
            return true;
        } else if (id == R.id.action_search) {
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

    protected void showSnackbar(String msg, View.OnClickListener listener) {
        Snackbar
                .make(findViewById(R.id.relativeLayout), R.string.prompt, Snackbar.LENGTH_LONG)
                .setAction(msg, listener).setActionTextColor(0xffffffff)
                .show(); // Don’t forget to show!
    }

    @Override
    public void onTabSelected(Tab tab) {
    }

    @Override
    public void onTabUnselected(Tab tab) {
    }

    @Override
    public void onTabReselected(Tab tab) {
    }
}
