package kw.learn.androidbezier.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import kw.learn.androidbezier.R;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 14:11
 */
public class TextSizeAdapter extends RecyclerView.Adapter<TextSizeAdapter.ViewHolder> {
    private int[] mImages;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    public TextSizeAdapter(int[] images) {
        mImages = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mImages[position]+"");
    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }
}