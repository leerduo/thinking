package cn.androidy.thinking;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.common.activities.SampleActivityBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.androidy.thinking.views.DefaultLrcBuilder;
import cn.androidy.thinking.views.ILrcBuilder;
import cn.androidy.thinking.views.ILrcView;
import cn.androidy.thinking.views.LrcRow;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class LyricViewActivity extends SampleActivityBase implements View.OnClickListener {
    // Whether the Log Fragment is currently shown
    private boolean mLogShown;
    private ActionBar mActionBar;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<Integer> mFloatingActionButtonImageResIdList;
    private int mCurrentColorIndex = 0;
    public final static String TAG = "LyricViewActivity";
    ILrcView mLrcView;
    private int mPalyTimerDuration = 1000;
    private Timer mTimer;
    private TimerTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_view);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(this);
        mFloatingActionButtonImageResIdList = new ArrayList<Integer>();
        mFloatingActionButtonImageResIdList.add(R.drawable.ic_favorite_border_white_48dp);
        mFloatingActionButtonImageResIdList.add(R.drawable.ic_favorite_white_48dp);
        mFloatingActionButton.setImageResource(mFloatingActionButtonImageResIdList.get(mCurrentColorIndex));
        mCurrentColorIndex = (mCurrentColorIndex + 1) % mFloatingActionButtonImageResIdList.size();

        String lrc = getFromAssets("test.lrc");
        mLrcView = (ILrcView) findViewById(R.id.lrcView);
        ILrcBuilder builder = new DefaultLrcBuilder();
        List<LrcRow> rows = builder.getLrcRows(lrc);

        mLrcView.setLrc(rows);
        beginLrcPlay();

        mLrcView.setListener(new ILrcView.LrcViewListener() {

            public void onLrcSeeked(int newPosition, LrcRow row) {
                if (mPlayer != null) {
                    mPlayer.seekTo((int) row.time);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }

    MediaPlayer mPlayer;

    public void beginLrcPlay() {

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(getAssets().openFd("m.mp3").getFileDescriptor());
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    if (mTimer == null) {
                        mTimer = new Timer();
                        mTask = new LrcTask();
                        mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
                    }
                }
            });
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    stopLrcPlay();
                }
            });
            mPlayer.prepare();
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lyric_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                if (line.trim().equals(""))
                    continue;
                Result += line + "\r\n";
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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

    public void stopLrcPlay() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    class LrcTask extends TimerTask {

        long beginTime = -1;

        @Override
        public void run() {
            if (beginTime == -1) {
                beginTime = System.currentTimeMillis();
            }

            final long timePassed = mPlayer.getCurrentPosition();
            LyricViewActivity.this.runOnUiThread(new Runnable() {

                public void run() {
                    mLrcView.seekLrcToTime(timePassed);
                }
            });

        }
    }

    ;
}
