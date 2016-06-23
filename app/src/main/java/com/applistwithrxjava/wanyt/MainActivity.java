package com.applistwithrxjava.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.applistwithrxjava.wanyt.bean.CatalogBean;
import com.applistwithrxjava.wanyt.bean.data.DataCatalogList;
import com.applistwithrxjava.wanyt.listener.ItemClickListener;
import com.applistwithrxjava.wanyt.recyclerdivider.GridDivider;
import com.applistwithrxjava.wanyt.recyclerdivider.LinearDivider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    private final String tag = ".wanyt.MainActivity";

    @BindView(R.id.rv_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.rv_catalog)
    RecyclerView recyclerView_catalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("hehe");
        list.add("haha");
        list.add("heihei");
        list.add("hengheng");
        list.add("aa");
        list.add("oo");
        list.add("nono");
        list.add("yesyes");
        list.add("moumou");
        list.add("gegeda");
        list.add("heheda");

        ArrayList<CatalogBean> catalogList = DataCatalogList.getInstance().getCatalogList();

        initCatalog(catalogList);
        initAppList(list);
    }

    /**
     * 方法目录布局
     * @param catalogList
     */
    private void initCatalog(ArrayList<CatalogBean> catalogList) {
        recyclerView_catalog.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_catalog.addItemDecoration(new LinearDivider(this, LinearLayoutManager.VERTICAL));
        CatalogAdapter catalogAdapter = new CatalogAdapter(this, catalogList);
        recyclerView_catalog.setAdapter(catalogAdapter);
        catalogAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Log.v(tag, "catalogList click:"+position);
            }
        });
    }

    /**
     * app 列表
     * @param list
     */
    private void initAppList(ArrayList<String> list) {

        recyclerView_list.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView_list.addItemDecoration(new GridDivider(this, R.drawable.grid_divider));
        ListAdapter listAdapter = new ListAdapter(this, list);
        recyclerView_list.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Log.v(tag, "list click:"+position);
            }
        });
    }

}
