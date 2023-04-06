package kw.learn.androidbezier.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.application.MyApplication;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 15:11
 */
public class ToolbarBgColorAdapter extends RecyclerView.Adapter<ToolbarBgColorAdapter.ViewHolder> {
    private ArrayList arrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.bg_item);
        }
    }

    public ToolbarBgColorAdapter(ArrayList images) {
        arrayList = images;
    }

    @Override
    public ToolbarBgColorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.toolbarbgcolor_item, parent, false);
        ToolbarBgColorAdapter.ViewHolder viewHolder = new ToolbarBgColorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ToolbarBgColorAdapter.ViewHolder holder, int position) {
//        holder.textView.setBackground(arrayList.get(position));

        // 获取 ShapeDrawable 对象
        GradientDrawable shapeDrawable
                = (GradientDrawable)
                MyApplication
                        .getInstance()
                        .getDrawable(R.drawable.toolbar_dyncamic);

        shapeDrawable.setColor(Color.RED);
// 修改填充颜色
//        int fillColor = 0;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            fillColor = MyApplication.getInstance().getColor(R.color.white);
//        }else {
//            fillColor =  R.color.white;
////        }
//        shapeDrawable.getPaint().setColor(Color.BLACK);
//// 修改边框颜色
//        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
//        shapeDrawable.getPaint().setStrokeWidth(MyApplication.getInstance().getResources().
//                getDimension(R.dimen.shape_stroke_width));
//        shapeDrawable.getPaint().setColor(Color.WHITE);

// 设置 ShapeDrawable 对象为 View 的背景
        View view = holder.textView;
        view.setBackground(shapeDrawable);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}