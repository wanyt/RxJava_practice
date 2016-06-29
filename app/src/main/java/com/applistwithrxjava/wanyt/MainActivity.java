package com.applistwithrxjava.wanyt;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.applistwithrxjava.wanyt.bean.CatalogBean;
import com.applistwithrxjava.wanyt.bean.data.DataCatalogList;
import com.applistwithrxjava.wanyt.fragment.BaseFragment;
import com.applistwithrxjava.wanyt.fragment.FragmentFrom;
import com.applistwithrxjava.wanyt.listener.ItemClickListener;
import com.applistwithrxjava.wanyt.recyclerdivider.LinearDivider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    private final String tag = ".wanyt.MainActivity";

    @BindView(R.id.rv_catalog)
    RecyclerView recyclerView_catalog;
    @BindView(R.id.fl_main_container)
    FrameLayout flMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<CatalogBean> catalogList = DataCatalogList.getInstance().getCatalogList();

        initCatalog(catalogList);
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
            public void setOnItemClickListener(View view, int position, Object obj) {
                setCatalogItemClick((CatalogBean) obj);
            }
        });
    }

    /**
     * 方法目录的点击事件
     * @param item
     */
    private void setCatalogItemClick(CatalogBean item) {

        BaseFragment fragment = null;

        switch (item.flag){
            case DataCatalogList.FROM:
                fragment = new FragmentFrom();
                break;
        }

        if(fragment == null){
            throw new NullPointerException("装载app list的fragment为空 " + tag);
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.CATALOG_PARAMS, item);
        fragment.setArguments(bundle);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main_container, fragment)
                .commit();

    }

//    /**
//     * app 列表
//     * @param list
//     */
//    private void initAppList(ArrayList<String> list) {
//
//        recyclerView_list.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView_list.addItemDecoration(new GridDivider(this, R.drawable.grid_divider));
//        ListAdapter listAdapter = new ListAdapter(this, list);
//        recyclerView_list.setAdapter(listAdapter);
//        listAdapter.setOnItemClickListener(new ItemClickListener() {
//            @Override
//            public void setOnItemClickListener(View view, int position) {
//                Log.v(tag, "list click:"+position);
//            }
//        });
//    }

}
