package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created on 2016/7/12 10:43
 * <p>
 * author wanyt
 * <p>
 * Description:flatMap()的使用
 */
public class FragmentFlatmap extends BaseFragment {

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
                //这个函数比较抽象，它接收可观测序列的一个数据，返回一个Observable，
                // 这个Observable可以是任何类型的，不仅限于源数据的类型
                .flatMap(new Func1<AppListBean, Observable<AppListBean>>() {
                    @Override
                    public Observable<AppListBean> call(AppListBean appListBean) {
                        String name = appListBean.name;
                        appListBean.name = name + "flag";
                        return Observable.just(appListBean);
                    }
                })
//                .concatMap(new Func1<AppListBean, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(AppListBean appListBean) {
//                        return null;
//                    }
//                })
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
