package kw.learn.androidbezier.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/4 12:30
 */
public class Demo01Dialog extends Dialog {

    private View convertView;
    /**
     * 构造函数，
     * @param context  上下文
     * @param layoutId 传递自定义Dialog的XML资源文件ID
     */
    public Demo01Dialog(Context context, int layoutId) {
        super(context);
        //填充XML
        convertView = LayoutInflater.from(context).inflate(layoutId, null);
        //设置没有标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(convertView);
        //设置窗口背景透明
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public <T extends View> T getView(int viewId){
        return (T) convertView.findViewById(viewId);
    }

    public String getText(int viewId){
        return ((TextView) getView(viewId)).getText().toString();
    }

    public Demo01Dialog setText(int viewId, String text){
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public Demo01Dialog setViewOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        if (view != null)
            view.setOnClickListener(listener);
        return this;
    }

    @Override
    public void show() {
        if (!isShowing()) super.show();
    }

    @Override
    public void dismiss() {
        if (isShowing()) super.dismiss();
    }
}