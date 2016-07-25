package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/7/10 17:03
 * <p>
 * author wanyt
 * <p>
 * Description:distinct()的使用
 *          distinctUntilChanged()在发送的数据序列中，只有当发送的下一个数据和当前数据不相同时才会发射数据。
 */
public class FragmentDistinct extends BaseFragment {

    FromAdapter adapter;
    @Override
    protected void initView() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FromAdapter(getActivity());
        rvList.setAdapter(adapter);
    }

    @Override
    protected void setListView() {
        Observable.from(getAppList())
                .take(2)//获取数据序列的前两个
                .repeat(2)//产生重复数据
                .distinct()//对重复的元素进行去重
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "distinct completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Something goes wrong with distinct()", Toast.LENGTH_SHORT).show();
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
