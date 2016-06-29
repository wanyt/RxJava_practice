package com.applistwithrxjava.wanyt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.Constants;
import com.applistwithrxjava.wanyt.R;
import com.applistwithrxjava.wanyt.bean.CatalogBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2016/6/23 16:50
 * <p>
 * author wanyt
 * <p>
 * Description:所有fragment的基类
 */
public abstract class BaseFragment extends Fragment {

    private final String tag = ".fragment.BaseFragment";

    CatalogBean catalog;
    Unbinder bind;

    @BindView(R.id.tv_fragment_base_method)
    TextView tvMethod;
    @BindView(R.id.tv_fragment_base_desc)
    TextView tvDesc;
    @BindView(R.id.iv_fragment_base_bulletgraph)
    ImageView ivBulletGraph;
    @BindView(R.id.rv_fragment_base_list)
    RecyclerView rvList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if(arguments != null){
            catalog = (CatalogBean) arguments.getSerializable(Constants.CATALOG_PARAMS);
        }

        View view = inflater.inflate(R.layout.fragment_base, container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListView();
    }

    protected abstract void initView();

    protected abstract void setListView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
