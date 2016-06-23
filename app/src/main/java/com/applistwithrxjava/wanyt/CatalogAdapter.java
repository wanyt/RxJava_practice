package com.applistwithrxjava.wanyt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.bean.CatalogBean;
import com.applistwithrxjava.wanyt.listener.ItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/6/22 14:10
 * <p>
 * author wanyt
 * <p>
 * Description:方法目录的适配器
 */
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    LayoutInflater from;
    ArrayList<CatalogBean> catalogList;

    public CatalogAdapter(Context context, ArrayList<CatalogBean> catalog) {
        from = LayoutInflater.from(context);
        this.catalogList = catalog;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from.inflate(R.layout.item_catalog, parent, false);
        return new CatalogViewHolder(view, itemClickListener, catalogList);
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {
        if(catalogList != null){
            CatalogBean catalog = catalogList.get(position);
            holder.tvCatalog.setText(catalog.method);
        }
    }

    private ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return catalogList == null ? 0 : catalogList.size();
    }

    class CatalogViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

       @BindView(R.id.tv_catalog)
       TextView tvCatalog;
       private ItemClickListener itemClickListener;
        private ArrayList<CatalogBean> catalogList;

       public CatalogViewHolder(View itemView, ItemClickListener itemClickListener,
                                ArrayList<CatalogBean> catalogList) {
           super(itemView);
           ButterKnife.bind(this, itemView);

           this.catalogList = catalogList;
           this.itemClickListener = itemClickListener;
           itemView.setOnClickListener(this);
       }

        /**
         * 设置item的点击回调
         * @param view
         */
        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.setOnItemClickListener(view, getAdapterPosition(), catalogList);
            }
        }
    }
}
