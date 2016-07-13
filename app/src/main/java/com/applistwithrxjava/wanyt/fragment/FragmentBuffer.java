package com.applistwithrxjava.wanyt.fragment;

import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;

/**
 * Created on 2016/7/12 11:27
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class FragmentBuffer extends BaseFragment {
    @Override
    protected void initView() {

    }

    @Override
    protected void setListView() {
        getAppObservable()
                .buffer(3)
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new Observer<List<AppListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<AppListBean> lists) {
                        Logger.d(lists.size());
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }
}
