package kw.learn.androidbezier;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import kw.learn.androidbezier.databinding.ActivityMain2Binding;
import kw.learn.androidbezier.dialog.LoadingDialog;
import kw.learn.androidbezier.dialog.SaveDialog;
import kw.learn.androidbezier.key.SoftKeyBroadManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private LinearLayout mBottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

}