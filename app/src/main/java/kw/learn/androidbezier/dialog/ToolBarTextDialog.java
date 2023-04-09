package kw.learn.androidbezier.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.adapter.TextSizeAdapter;
import kw.learn.androidbezier.application.MyApplication;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/3 22:58
 */
public class ToolBarTextDialog extends Dialog {

    public ToolBarTextDialog(@NonNull Context context) {
        super(context);
    }

    public ToolBarTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ToolBarTextDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.toolbar_text_view);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //按空白处不能取消动画
//        setCanceledOnTouchOutside(false);

        // 获取屏幕宽度
        WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        // 设置Dialog宽度为屏幕宽度的80%
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (screenWidth);
        getWindow().setAttributes(params);


        RecyclerView recyclerView = findViewById(R.id.textsize);
        int xx[] = new int[]{
                10,12,14,16,18,20,22,24,26,28,30,32,34,36,40,50,60,70
        };
        TextSizeAdapter adapter = new TextSizeAdapter(xx);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.toolbar_text_root).setOnClickListener(closeDialog);
        findViewById(R.id.toolbar_text_back).setOnClickListener(closeDialog);
    }

    private View.OnClickListener closeDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}
