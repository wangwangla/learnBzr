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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.adapter.TextSizeAdapter;
import kw.learn.androidbezier.adapter.ToolbarBgColorAdapter;
import kw.learn.androidbezier.application.MyApplication;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 15:08
 */
public class ToolBarBgColorDialog extends Dialog {

    public ToolBarBgColorDialog(@NonNull Context context) {
        super(context);
    }

    public ToolBarBgColorDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ToolBarBgColorDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.toolbarbgcolor);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        // 获取屏幕宽度
        WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        // 设置Dialog宽度为屏幕宽度的80%
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (screenWidth);
        getWindow().setAttributes(params);


        RecyclerView recyclerView = findViewById(R.id.toolbar_bgColor);
        ArrayList<String> bgColors = new ArrayList<>();
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");
        bgColors.add("#FF000000");

        ToolbarBgColorAdapter adapter = new ToolbarBgColorAdapter(bgColors);
        recyclerView.setLayoutManager(new GridLayoutManager(MyApplication.getInstance(), 4));
        recyclerView.setAdapter(adapter);
//
//        findViewById(R.id.toolbar_text_root).setOnClickListener(closeDialog);
//        findViewById(R.id.toolbar_text_back).setOnClickListener(closeDialog);
    }

    private View.OnClickListener closeDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}
