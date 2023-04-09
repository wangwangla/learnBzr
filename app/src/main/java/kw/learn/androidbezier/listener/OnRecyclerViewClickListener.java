package kw.learn.androidbezier.listener;

import android.view.View;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/6 9:33
 */
public interface OnRecyclerViewClickListener {

    /**
     * 点击item监听时间
     * @param view View
     * @param position position
     */
    void onItemClick(View view, int position);

    /**
     * 长按监听时间
     * @param view View
     * @param position position
     */
    void onItemLongClick(View view, int position);
}
