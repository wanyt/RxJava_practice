package com.applistwithrxjava.wanyt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.listener.ItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/6/22 14:10
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    LayoutInflater from;
    ArrayList<String> catalog;

    public CatalogAdapter(Context context, ArrayList<String> catalog) {
        from = LayoutInflater.from(context);
        this.catalog = catalog;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from.inflate(R.layout.item_catalog, null, false);
        return new CatalogViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {
        if(catalog != null){
            holder.tvCatalog.setText(catalog.get(position));
        }
    }

    private ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return catalog == null ? 0 : catalog.size();
    }

    class CatalogViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

       @BindView(R.id.tv_catalog)
       TextView tvCatalog;
       private ItemClickListener itemClickListener;

       public CatalogViewHolder(View itemView, ItemClickListener itemClickListener) {
           super(itemView);
           ButterKnife.bind(this, itemView);

           this.itemClickListener = itemClickListener;
           itemView.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.setOnItemClickListener(view, getAdapterPosition());
            }
        }
    }
}
