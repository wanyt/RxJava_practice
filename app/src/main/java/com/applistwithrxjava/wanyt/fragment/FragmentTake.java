package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/7/10 16:17
 * <p>
 * author wanyt
 * <p>
 * Description:take(int count)以及takeLast(int count)的使用
 *              first()
 *              last()
 */
public class FragmentTake extends BaseFragment {

    @Override
    protected void initView() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FromAdapter(getActivity());
        rvList.setAdapter(adapter);
    }

    FromAdapter adapter;

    @Override
    protected void setListView() {
        getAppObservable()
                .take(6)//获取数据序列的前六个
                .takeLast(2)//获取前六个的最后两个
//                .first()
//                .last()
                .subscribeOn(Schedulers.newThread())//线程切换，性能优化
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppListBean appListBean) {
                        adapter.addItem(appListBean);
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }

}
