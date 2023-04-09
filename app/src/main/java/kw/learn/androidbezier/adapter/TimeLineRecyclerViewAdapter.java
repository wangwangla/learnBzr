package kw.learn.androidbezier.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kw.learn.androidbezier.EditActivity;
import kw.learn.androidbezier.R;
import kw.learn.androidbezier.application.MyApplication;
import kw.learn.androidbezier.bean.ContentInfo;
import kw.learn.androidbezier.bean.DateBean;
import kw.learn.androidbezier.bean.NoteBean;
import kw.learn.androidbezier.data.TimeLineAllItem;
import kw.learn.androidbezier.dialog.DeleteDialog;
import kw.learn.androidbezier.fragment.TimeLineFragment;
import kw.learn.androidbezier.utils.NoteUtils;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/1 9:58
 */
public class TimeLineRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<TimeLineAllItem> lineAllItems;
    private TimeLineFragment fragment;
    public TimeLineRecyclerViewAdapter(List<TimeLineAllItem> lineAllItems, TimeLineFragment timeLineFragment){
        this.lineAllItems = lineAllItems;
        this.fragment = timeLineFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.timeline_item_title,
                            parent, false);
            return new TitleViewHolder(inflate);
        }else {
            View inflate = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.timeline_item_content,
                            parent, false);
            return new ItemViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TimeLineAllItem item = lineAllItems.get(position);
        if (holder instanceof TitleViewHolder) {
            long time = item.getMsg().getTime();
            DateBean dateBean = NoteUtils.longToDateBean(time);
//            dateBean.getYear()+"年"+dateBean.getMonth()+"月";
            ((TitleViewHolder) holder).titleTextView.setText(
                    fragment.getString(
                            R.string.title_format,dateBean.getYear(),
                            dateBean.getMonth()));

        } else if (holder instanceof ItemViewHolder) {
            NoteBean msg = item.getMsg();
            long time = item.getMsg().getTime();
            DateBean dateBean = NoteUtils.longToDateBean(time);
            ArrayList<ContentInfo> content = msg.getContent();
            StringBuilder builder = new StringBuilder();
            for (ContentInfo info : content) {
                builder.append(info.getContent());
            }
            ((ItemViewHolder) holder).itemTextView.setText(builder.toString());
            String []mm = new String[]{
                    "周一",
                    "周二",
                    "周三",
                    "周四",
                    "周五",
                    "周六",
                    "周日",
            };
            ((ItemViewHolder) holder).titleView.setText(dateBean.getDay() +"  "+mm[dateBean.getWeekDay()]);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (lineAllItems.get(position).isTitle()) {
            return 0;  // 返回标题视图类型
        } else {
            return 1;  // 返回条目视图类型
        }

    }

    @Override
    public int getItemCount() {
        return lineAllItems.size();
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        private int position;

        public TitleViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.timeline_title_textview);
        }

        public void setPosition(int position) {
            this.position = position;
        }

    }

    public class ItemViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        TextView itemTextView;
        TextView titleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item_content);
            titleView = itemView.findViewById(R.id.item_title_textview);
            itemView.setOnClickListener(this::onClick);
            itemView.setOnLongClickListener(this::onLongClick);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyApplication.getInstance(), EditActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putBundle("data",lineAllItems.get(postion));
            intent.putExtra("data",lineAllItems.get(getPosition()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.getInstance().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            DeleteDialog dialog = new DeleteDialog(fragment.getActivity());
            dialog.setFileName(lineAllItems.get(getPosition()).getMsg().getTime()+"");
            dialog.setOkListener(new Runnable(){
                @Override
                public void run() {
                    boolean delete = true;
                    if (lineAllItems.size()>getPosition()+1){
                        if (!lineAllItems.get(getPosition()+1).isTitle()) {
                            delete = false;
                        }
                    }
                    if (getPosition()>0) {
                        if (!lineAllItems.get(getPosition()-1).isTitle()) {
                            delete = false;
                        }
                    }
                    if (delete){
                        lineAllItems.remove(getPosition()-1);
                        notifyItemRemoved(getPosition());
                        // 删除 position 位置上的数据
                        lineAllItems.remove(getPosition()-1);
// 更新 adapter
                        notifyItemRemoved(getPosition()-1);
                    }else {

                        // 删除 position 位置上的数据
                        lineAllItems.remove(getPosition());
// 更新 adapter
                        notifyItemRemoved(getPosition());
                    }

                }
            });
            dialog.show();
            return true;
        }
    }
}
