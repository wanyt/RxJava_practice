package com.applistwithrxjava.wanyt.fragment;

import android.view.View;

import com.applistwithrxjava.wanyt.Constants;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/7/8 11:56
 * <p>
 * author wanyt
 * <p>
 * Description:interval()的使用，十分简单
 *              但是这个例子有必要认真的研究一下，因为，涉及到了线程问题
 *              interval()默认发生在computation（）线程，也就是主线程之外
 *              在53行，tvText.setText(aLong + "");这个操作属于UI操作，发生在主线程
 *              也就是说Observable和Observer两个动作发生在不同的线程
 *              这时就需要处理线程线程调度问题
 */
public class FragmentInterval extends BaseFragment {

    private final String tag = ".fragment.FragmentJust";
    private final String imageUrl = "http://goo.gl/gEgYUd";

    @Override
    protected void initView() {
        rvList.setVisibility(View.GONE);
        tvText.setVisibility(View.VISIBLE);
    }

    Subscription subscribe;

    @Override
    protected void setListView() {
        subscribe =
                Observable.interval(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.computation())//加不加这一行都没有差别，主要是看起来方便知道interval发生的线程
                    .observeOn(AndroidSchedulers.mainThread())//AndroidScheduler在RxAndroid包中，可以把这一行注释掉，观察一下现象
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Long aLong) {
                            Logger.d(aLong);
                            tvText.setText(aLong + "");
                        }
                    });
    }

    @Override
    protected void internalMessage(String flag, Object event) {
        switch (flag){
            case Constants.EVENT_OBSERVER_UNREGISTER://保证一旦离开当前界面Observable就会被注销订阅，节省资源
                if((boolean)event)
                    subscribe.unsubscribe();
                break;
        }
    }

}
