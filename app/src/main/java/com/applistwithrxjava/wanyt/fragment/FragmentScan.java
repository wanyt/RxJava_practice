package com.applistwithrxjava.wanyt.fragment;

import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

/**
 * Created on 2016/7/14 14:50
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class FragmentScan extends BaseFragment {
    @Override
    protected void initView() {

    }

    @Override
    protected void setListView() {
        Observable<Integer> range = Observable.range(0, 5);

        range.scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        })
        .subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Logger.d(integer);
            }
        });

    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }
}
