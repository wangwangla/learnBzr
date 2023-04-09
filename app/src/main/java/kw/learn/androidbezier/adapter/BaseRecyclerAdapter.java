package kw.learn.androidbezier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kw.learn.androidbezier.core.AnimateFirstDisplayListener;
import kw.learn.androidbezier.listener.OnRecyclerViewClickListener;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/6 9:35
 */
public class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected OnRecyclerViewClickListener mOnClickListener;

    protected Context mContext;
    protected LayoutInflater mInflater;

    protected AnimateFirstDisplayListener displayListener;

    protected BaseRecyclerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    protected List<T> list;
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public void setOnClickListener(OnRecyclerViewClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

}
