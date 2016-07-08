package com.applistwithrxjava.wanyt;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.applistwithrxjava.wanyt.adapter.CatalogAdapter;
import com.applistwithrxjava.wanyt.bean.CatalogBean;
import com.applistwithrxjava.wanyt.bean.data.DataCatalogList;
import com.applistwithrxjava.wanyt.fragment.BaseFragment;
import com.applistwithrxjava.wanyt.fragment.FragmentDefault;
import com.applistwithrxjava.wanyt.fragment.FragmentFrom;
import com.applistwithrxjava.wanyt.fragment.FragmentInterval;
import com.applistwithrxjava.wanyt.fragment.FragmentJust;
import com.applistwithrxjava.wanyt.listener.ItemClickListener;
import com.applistwithrxjava.wanyt.recyclerdivider.LinearDivider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
        initDefaultView();
    }

    /**
     * 初始化默认显示
     */
    private void initDefaultView() {
        FragmentDefault fragmentDefault = new FragmentDefault();
        manageFragment(fragmentDefault);
    }

    /**
     * 方法目录布局
     *
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
                catalogItemClick((CatalogBean) obj);
            }
        });
    }

    /**
     * 方法目录的点击事件
     *
     * @param item
     */
    private void catalogItemClick(CatalogBean item) {

        BaseFragment fragment = new FragmentDefault();

        switch (item.flag) {
            case DataCatalogList.FROM:
                fragment = new FragmentFrom();
                break;
            case DataCatalogList.JUST:
                fragment = new FragmentJust();
                break;
            case DataCatalogList.INTERVAL:
                fragment = new FragmentInterval();
                break;
        }

        if (fragment == null) {
            throw new NullPointerException("装载app list的fragment为空 " + tag);
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.CATALOG_PARAMS, item);
        fragment.setArguments(bundle);

        manageFragment(fragment);
        RxBus.getInstance().post(new BusEventModel(Constants.EVENT_OBSERVER_UNREGISTER, true));
    }

    private void manageFragment(BaseFragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main_container, fragment)
                .commit();
    }

}
