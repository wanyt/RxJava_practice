package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

/**
 * Created on 2016/7/13 14:58
 * <p>
 * author wanyt
 * <p>
 * Description:zip()的使用，个人感觉比较实用
 */
public class FragmentZip extends BaseFragment {

    FromAdapter adapter;
    @Override
    protected void initView() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FromAdapter(getActivity());
        rvList.setAdapter(adapter);
    }

    @Override
    protected void setListView() {
        Observable<AppListBean> observable_1 = getAppObservable().take(9);
        Observable<AppListBean> observable_2 = getAppObservable().take(9);

        Observable
                //zip使用Func合并observable，需要合并x个Observable就用Funcx就可以操作了
                .zip(observable_1, observable_2, new Func2<AppListBean, AppListBean, AppListBean>() {
                    @Override
                    public AppListBean call(AppListBean appListBean, AppListBean appListBean2) {
                        String name = appListBean.name;
                        appListBean.name = appListBean2.name + name;
                        return appListBean;
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
                    public void onNext(AppListBean o) {
                        adapter.addItem(o);
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }
}
