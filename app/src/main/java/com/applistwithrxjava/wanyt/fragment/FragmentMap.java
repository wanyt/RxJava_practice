package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observer;
import rx.functions.Func1;

/**
 * Created on 2016/7/12 10:34
 * <p>
 * author wanyt
 * <p>
 * Description:map()的使用
 */
public class FragmentMap extends BaseFragment {

    FromAdapter adapter;
    @Override
    protected void initView() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FromAdapter(getActivity());
        rvList.setAdapter(adapter);
    }

    @Override
    protected void setListView() {
        getAppObservable()
                .map(new Func1<AppListBean, AppListBean>() {
                    @Override
                    public AppListBean call(AppListBean appListBean) {
                        String name = appListBean.name;
                        appListBean.name = name + "maped";
                        return appListBean;//给应用的名称添加了“maped”并返回给观察者
                    }
                })
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppListBean app) {
                        adapter.addItem(app);
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }

}
