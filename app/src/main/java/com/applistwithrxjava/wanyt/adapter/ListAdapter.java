package com.applistwithrxjava.wanyt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.R;
import com.applistwithrxjava.wanyt.listener.ItemClickListener;

import java.util.ArrayList;

/**
 * Created on 2016/6/21 15:14
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private final String tag = ".wanyt.ListAdapter";

    LayoutInflater layoutInflater;
    ArrayList<String> list;
    ItemClickListener itemClickListener;

    public ListAdapter(Context context, ArrayList<String> item) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false),
                itemClickListener, list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(list != null){
            String s = list.get(position);
            holder.textView.setText(s);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView textView;
        ItemClickListener itemClickListener;
        ArrayList<String> list;

        public ViewHolder(View itemView,
                          ItemClickListener itemClickListener, ArrayList<String> list) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);

            this.list = list;
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.setOnItemClickListener(view, getAdapterPosition(), list);
            }
        }
    }
}
