package kw.learn.androidbezier;

import static android.media.session.PlaybackState.ACTION_STOP;

import static androidx.media.session.MediaButtonReceiver.buildMediaButtonPendingIntent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.audiofx.DynamicsProcessing;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RemoteViews;

import kw.learn.androidbezier.fragment.CalendarFragment;
import kw.learn.androidbezier.fragment.ChartFragment;
import kw.learn.androidbezier.fragment.MineFragment;
import kw.learn.androidbezier.fragment.TimeLineFragment;
import kw.learn.androidbezier.utils.TimberUtils;

public class LoadingActivity extends AppCompatActivity {
    private Runnable timeLineRunable = new Runnable() {
        @Override
        public void run() {
            TimeLineFragment fragment = new TimeLineFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable calenderRunable = new Runnable() {
        @Override
        public void run() {
            CalendarFragment fragment = new CalendarFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable chartRunable = new Runnable() {
        @Override
        public void run() {
            ChartFragment fragment = new ChartFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable mineRunable = new Runnable() {
        @Override
        public void run() {
            MineFragment fragment = new MineFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        }
    };

    private Runnable addNote = new Runnable() {
        @Override
        public void run() {
            Intent enterAddNote = new Intent(LoadingActivity.this,MainActivity.class);
            startActivity(enterAddNote);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final View decorView = getWindow().getDecorView();
            final int systemUiVisibility = decorView.getSystemUiVisibility();
//          黑
            decorView.setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//          白
//            decorView.setSystemUiVisibility(systemUiVisibility & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        timeLineRunable.run();

//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
    }


    public void timeLine(View view) {
        timeLineRunable.run();
    }

    public void calender(View view) {
        calenderRunable.run();
    }

    public void chart(View view) {
        chartRunable.run();
    }

    public void mine(View view) {
        mineRunable.run();
    }

    public void addNote(View view) {
        addNote.run();
    }
}