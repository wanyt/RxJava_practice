package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observer;

/**
 * Created on 2016/7/10 17:24
 * <p>
 * author wanyt
 * <p>
 * Description:skip(int count)以及skipLast(int count)的使用
 */
public class FragmentSkip extends BaseFragment {

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
                .take(10)//获取数据序列的前十个元素，看起来比较清晰
                .skip(3)//跳过前十个元素的前三个数据
//                .skipLast(3)//跳过前十个元素的最后三个
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
