package kw.learn.androidbezier.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.data.TimeLineAllItem;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/1 9:58
 */
public class TimeLineRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<TimeLineAllItem> lineAllItems;
    public TimeLineRecyclerViewAdapter(List<TimeLineAllItem> lineAllItems){
        this.lineAllItems = lineAllItems;
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
        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).titleTextView.setText("xxxxxx");
        } else if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).itemTextView.setText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public TitleViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.timeline_title_textview);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item_content);
        }
    }
}
