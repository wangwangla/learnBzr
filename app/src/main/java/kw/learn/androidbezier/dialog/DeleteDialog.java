package kw.learn.androidbezier.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.adapter.TextSizeAdapter;
import kw.learn.androidbezier.application.MyApplication;
import kw.learn.androidbezier.sqlite.NoteSeriUtils;
import kw.learn.androidbezier.utils.NoteUtils;

/**
 * @Auther jian xian si qi
 * @Date 2023/6/9 15:24
 */
public class DeleteDialog extends Dialog {

    private String fileName;

    public DeleteDialog(@NonNull Context context) {
        super(context);
    }

    public DeleteDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DeleteDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_item_delete);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                NoteSeriUtils.delete(fileName);
                runnable.run();
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //按空白处不能取消动画
//        setCanceledOnTouchOutside(false);
        // 获取屏幕宽度
//        WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        int screenWidth = displayMetrics.widthPixels;
//        // 设置Dialog宽度为屏幕宽度的80%
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width = (int) (screenWidth);
//        getWindow().setAttributes(params);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private Runnable runnable;
    public void setOkListener(Runnable runnable) {
        this.runnable = runnable;
    }
}

