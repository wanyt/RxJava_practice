package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import rx.Observable;
import rx.Observer;

/**
 * Created on 2016/7/13 14:18
 * <p>
 * author wanyt
 * <p>
 * Description:merge()的
 */
public class FragmentMerge extends BaseFragment {

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
                .merge(observable_1, observable_2)//合并多个Observable,在合并过程中如果有异常发生会立即出发onError(),并停止
//                .mergeDelayError(observable_1, observable_2)//合并多个Observable,在合并过程中如果有异常发生，会继续进行合并，当合并完成之后触发onError()
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
