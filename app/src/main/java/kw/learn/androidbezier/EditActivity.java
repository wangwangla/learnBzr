package kw.learn.androidbezier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kw.learn.androidbezier.bean.ContentInfo;
import kw.learn.androidbezier.bean.DateBean;
import kw.learn.androidbezier.bean.ImageFolderBean;
import kw.learn.androidbezier.bean.NoteBean;
import kw.learn.androidbezier.data.TimeLineAllItem;
import kw.learn.androidbezier.databinding.ActivityEditBinding;
import kw.learn.androidbezier.dialog.SaveDialog;
import kw.learn.androidbezier.dialog.ToolBarBgColorDialog;
import kw.learn.androidbezier.dialog.ToolBarTextDialog;
import kw.learn.androidbezier.key.SoftKeyBroadManager;
import kw.learn.androidbezier.listener.SignListener;
import kw.learn.androidbezier.sqlite.NoteSeriUtils;
import kw.learn.androidbezier.utils.NoteUtils;
import kw.learn.androidbezier.utils.TimberUtils;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private LinearLayout mBottomView;
    private NoteBean noteBean;
    private boolean isLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Serializable data = intent.getSerializableExtra("data");
        StringBuilder defaultStr = new StringBuilder();

        if (data!=null){
            if ( data instanceof TimeLineAllItem){
                noteBean = ((TimeLineAllItem) (data)).getMsg();
            }else {
                noteBean = new NoteBean();
                long time = new Date().getTime();
                System.out.println("=="+time);
                DateBean dateBean = NoteUtils.longToDateBean(time);
                System.out.println(dateBean.toString());
                noteBean.setTime(time);
            }
            ArrayList<ContentInfo> content = noteBean.getContent();
            for (ContentInfo info : content) {
                defaultStr.append(info.getContent());
            }
        }else {
            noteBean = new NoteBean();
            long time = new Date().getTime();
            System.out.println("=="+time);
            DateBean dateBean = NoteUtils.longToDateBean(time);
            System.out.println(dateBean.toString());
            noteBean.setTime(time);
        }
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView textZiNum = findViewById(R.id.text_zi_num);

        EditText editText = findViewById(R.id.add_note_content);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isLast = false;

            }

            @Override
            public void afterTextChanged(Editable s) {
                textZiNum.setText(getString(R.string.textNum_text,s.toString().length()));
            }
        });
        editText.setText(defaultStr.toString());
        editText.setSelection(editText.getText().length());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        View addNoteBack = findViewById(R.id.add_note_back);
        addNoteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLast && editText.getText().toString().trim().length()>0) {
                    SaveDialog myDialog = new SaveDialog(EditActivity.this);
                    myDialog.setTitle("保存");
                    myDialog.setMessage("内容未存储，是否保存？");
                    myDialog.setYesOnclickListener("确定", new SaveDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            //                        SharedPreferenceUtil.setInfoToShared("Z", "N");
                            myDialog.dismiss();
                            save();
                            editToMain();
                        }
                    });
                    myDialog.setNoOnclickListener("删除", new SaveDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            //                        cbFingerprint.setChecked(true);
                            myDialog.dismiss();
                            editToMain();
                        }
                    });
                    myDialog.show();
                }else {
                    editToMain();
                }
            }
        });

        mBottomView = (LinearLayout) findViewById(R.id.relativeLayout2);
        View viewById = findViewById(R.id.container);
        SoftKeyBroadManager softKeyBroadManager = new SoftKeyBroadManager(viewById);
        softKeyBroadManager.addSoftKeyboardStateListener(softKeyboardStateListener);

        findViewById(R.id.save_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLast = true;
                save();
            }
        });

        editListener();
        initBottomListener();
    }

    private void editListener() {

    }

    private void editToMain() {
        Intent edToMain = new Intent(EditActivity.this, MainActivity.class);
        startActivity(edToMain);
        finish();
    }

    public void save(){
        noteBean.setBgColor("#FFFFFFFF");
        noteBean.setMood("happy");
        noteBean.setTag("lalala");
        noteBean.setWeather("sun");
        ArrayList<ContentInfo> arrayList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
        EditText content = findViewById(R.id.add_note_content);
        ContentInfo info  = new ContentInfo();
        info.setAlin(1);
        info.setColor("#FF000000");
        info.setSize(20);
        info.setContent(content.getText().toString());
        arrayList.add(info);
//        }
        noteBean.setContent(arrayList);
        NoteSeriUtils.saveNote(noteBean,new SignListener(){
            @Override
            public void sign(int code) {
                super.sign(code);
                if (code == 1){
                    Toast.makeText(EditActivity.this,getString(R.string.success_save),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(EditActivity.this,getString(R.string.faild_save),Toast.LENGTH_LONG);
                }
            }
        });
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
                ToolBarTextDialog myDialog = new ToolBarTextDialog(EditActivity.this);
                myDialog.setTitle("保存");
                myDialog.getWindow().setGravity(Gravity.BOTTOM);
                myDialog.show();

            }
        });
        findViewById(R.id.toolbar_bgColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolBarBgColorDialog dialog = new ToolBarBgColorDialog(EditActivity.this);
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
//                Intent intent = new Intent(MainActivity.this,ImageSelectActivity.class);
//                startActivity(intent);
                FolderListActivity.startFolderListActivity(EditActivity.this, 1, null, 9);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                case 2:
                    List<ImageFolderBean> list = (List<ImageFolderBean>) data.getSerializableExtra("list");
                    if (list == null) {
                        return;
                    }
                    StringBuilder stringBuffer = new StringBuilder();
                    for (ImageFolderBean string : list) {
                        stringBuffer.append(string.path).append("\n");
                    }


                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        editToMain();
    }
}