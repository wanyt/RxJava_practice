package com.applistwithrxjava.wanyt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.R;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/7/1 10:59
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class FromAdapter extends RecyclerView.Adapter<FromAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    ArrayList<AppListBean> list = new ArrayList<>();

    public FromAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 这中添加item的方式效率低下
     * @param position
     * @param app
     */
    public void addItem(int position, AppListBean app){
        list.add(app);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_fragment_from_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppListBean app = list.get(position);
        holder.tvItem.setText(app.name);
        holder.ivIcon.setImageDrawable(app.drawable);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_name)
        TextView tvItem;
        @BindView(R.id.iv_item_icon)
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
