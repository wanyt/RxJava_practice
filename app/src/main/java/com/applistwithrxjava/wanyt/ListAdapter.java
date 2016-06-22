package com.applistwithrxjava.wanyt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.listener.ItemClickListener;
import com.applistwithrxjava.wanyt.listener.ItemLongClickListener;

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
    ItemLongClickListener itemLongClickListener;

    public ListAdapter(Context context, ArrayList<String> item) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list, null, false),
                itemClickListener, itemLongClickListener);
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

    public void setOnItemLongClickListener(ItemLongClickListener itemLongClickListener){
        this.itemLongClickListener = itemLongClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        TextView textView;
        ItemClickListener itemClickListener;
        ItemLongClickListener itemLongClickListener;

        public ViewHolder(View itemView,
                          ItemClickListener itemClickListener,
                          ItemLongClickListener itemLongClickListener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);

            this.itemClickListener = itemClickListener;
            this.itemLongClickListener = itemLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.setOnItemClickListener(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(itemLongClickListener != null){
                itemLongClickListener.setOnItemLongClickListener(view, getAdapterPosition());
            }
            return true;
        }

    }

}
