package kw.learn.androidbezier;

import static androidx.media.session.MediaButtonReceiver.buildMediaButtonPendingIntent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kw.learn.androidbezier.fragment.CalendarFragment;
import kw.learn.androidbezier.fragment.ChartFragment;
import kw.learn.androidbezier.fragment.MineFragment;
import kw.learn.androidbezier.fragment.TimeLineFragment;
import kw.learn.androidbezier.theme.ThemeUtils;

public class MainActivity extends AppCompatActivity {
    private int fragmentIndex;

    private Runnable timeLineRunable = new Runnable() {
        @Override
        public void run() {
            fragmentIndex = 1;
            TimeLineFragment fragment = new TimeLineFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable calenderRunable = new Runnable() {
        @Override
        public void run() {
            fragmentIndex = 2;
            CalendarFragment fragment = new CalendarFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable chartRunable = new Runnable() {
        @Override
        public void run() {
            fragmentIndex = 3;
            ChartFragment fragment = new ChartFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable mineRunable = new Runnable() {
        @Override
        public void run() {
            fragmentIndex = 4;
            MineFragment fragment = new MineFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable addNote = new Runnable() {
        @Override
        public void run() {
            Intent enterAddNote = new Intent(MainActivity.this, EditActivity.class);
            startActivity(enterAddNote);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeUtils.setStatusBarColor(this,false);
        //default fragment
        timeLineRunable.run();
    }


    public void timeLine(View view) {
        if (fragmentIndex == 1)return;
        timeLineRunable.run();
    }

    public void calender(View view) {
        if (fragmentIndex == 2)return;
        calenderRunable.run();
    }

    public void chart(View view) {
        if (fragmentIndex == 3)return;
        chartRunable.run();
    }

    public void mine(View view) {
        if (fragmentIndex == 4)return;
        mineRunable.run();
    }

    public void addNote(View view) {
        addNote.run();
    }
}