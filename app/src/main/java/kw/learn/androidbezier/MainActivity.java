package kw.learn.androidbezier;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Date;

import kw.learn.androidbezier.adapter.ToolbarBgColorAdapter;
import kw.learn.androidbezier.bean.ContentInfo;
import kw.learn.androidbezier.bean.NoteBean;
import kw.learn.androidbezier.databinding.ActivityMain2Binding;
import kw.learn.androidbezier.dialog.LoadingDialog;
import kw.learn.androidbezier.dialog.SaveDialog;
import kw.learn.androidbezier.dialog.ToolBarBgColorDialog;
import kw.learn.androidbezier.dialog.ToolBarTextDialog;
import kw.learn.androidbezier.key.SoftKeyBroadManager;
import kw.learn.androidbezier.sqlite.NoteSeriUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private LinearLayout mBottomView;
    private NoteBean noteBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteBean = new NoteBean();
        noteBean.setTime(new Date().getTime());
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText editText = findViewById(R.id.add_note_content);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        View addNoteBack = findViewById(R.id.add_note_back);
        addNoteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDialog myDialog = new SaveDialog(MainActivity.this);
//                myDialog.getWindow().setGravity(Gravity.BOTTOM)
                myDialog.setTitle("保存");
                myDialog.setMessage("内容未存储，是否保存？");
                myDialog.setYesOnclickListener("确定", new SaveDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
    //                        SharedPreferenceUtil.setInfoToShared("Z", "N");
                        myDialog.dismiss();
                    }
                });
                myDialog.setNoOnclickListener("删除", new SaveDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
    //                        cbFingerprint.setChecked(true);
                        myDialog.dismiss();
                    }
                });
                myDialog.show();

//                LoadingDialog dialog = new LoadingDialog(MainActivity.this);
//                dialog.show();
            }
        });

        mBottomView = (LinearLayout) findViewById(R.id.relativeLayout2);
        View viewById = findViewById(R.id.container);
        SoftKeyBroadManager softKeyBroadManager = new SoftKeyBroadManager(viewById);
        softKeyBroadManager.addSoftKeyboardStateListener(softKeyboardStateListener);

        findViewById(R.id.save_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        initBottomListener();
        NoteSeriUtils.readNote();
    }

    public void save(){
        noteBean.setBgColor("#FFFFFFFF");
        noteBean.setMood("happy");
        noteBean.setTag("lalala");
        noteBean.setWeather("sun");
        ArrayList<ContentInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ContentInfo info  = new ContentInfo();
            info.setAlin(1);
            info.setColor("#FF000000");
            info.setSize(20);
            info.setContent("xxxxxxxx");
            arrayList.add(info);
        }
        noteBean.setContent(arrayList);
        NoteSeriUtils.saveNote(noteBean);
    }


    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener
            = new SoftKeyBroadManager.SoftKeyboardStateListener() {

        @Override
        public void onSoftKeyboardOpened(int keyboardHeightInPx) {
            mBottomView.requestLayout();
        }

        @Override
        public void onSoftKeyboardClosed() {
            mBottomView.requestLayout();
        }
    };

    private void initBottomListener(){
        findViewById(R.id.toolbar_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolBarTextDialog myDialog = new ToolBarTextDialog(MainActivity.this);
                myDialog.setTitle("保存");
                myDialog.getWindow().setGravity(Gravity.BOTTOM);
                myDialog.show();

            }
        });
        findViewById(R.id.toolbar_bgColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolBarBgColorDialog dialog = new ToolBarBgColorDialog(MainActivity.this);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.show();
            }
        });
        findViewById(R.id.toolbar_format).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.toolbar_insert_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}