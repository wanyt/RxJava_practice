package com.applistwithrxjava.wanyt.fragment;

import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created on 2016/7/12 11:27
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class FragmentGroupby extends BaseFragment {

    @Override
    protected void initView() {

    }

    @Override
    protected void setListView() {
        Observable<GroupedObservable<Integer, AppListBean>> groupedObservableObservable = getAppObservable()
                .groupBy(new Func1<AppListBean, Integer>() {
                    @Override
                    public Integer call(AppListBean appListBean) {
                        int length = appListBean.name.length();
                        return length;
                    }
                });

        Observable.concat(groupedObservableObservable)
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppListBean appListBean) {
                        Logger.d(appListBean.name);
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }
}
