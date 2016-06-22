package com.applistwithrxjava.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

        initCatalog(list);
        initAppList(list);
    }

    private void initCatalog(ArrayList<String> list) {
        recyclerView_catalog.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_catalog.addItemDecoration(new LinearDivider(this, LinearLayoutManager.VERTICAL));
        CatalogAdapter catalogAdapter = new CatalogAdapter(this, list);
        recyclerView_catalog.setAdapter(catalogAdapter);
        catalogAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Log.v(tag, "catalog click:"+position);
            }
        });
    }

    private void initAppList(ArrayList<String> list) {
        recyclerView_list.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView_list.addItemDecoration(new GridDivider(this));
//        recyclerView_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

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
