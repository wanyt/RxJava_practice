package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.applistwithrxjava.wanyt.Constants;
import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created on 2016/7/8 17:21
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class FragmentFilter extends BaseFragment {

    private final String tag = ".fragment.FragmentFilter";
    private final String imageUrl = "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/filter.png";
    private FromAdapter adapter;
    private Subscription subscribe;

    @Override
    protected void initView() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FromAdapter(getActivity());
        rvList.setAdapter(adapter);
    }

    @Override
    protected void setListView() {
        subscribe = getAppObservable()
                .filter(new Func1<AppListBean, Boolean>() {
                    @Override
                    public Boolean call(AppListBean appListBean) {
                        String name = appListBean.name;
                        Log.i("app", name);
                        if (name.length() == 4) {
                            return true;
                        } else {
                            return false;
                        }
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
                    public void onNext(AppListBean appListBean) {
                        Logger.d(appListBean.name);
                        adapter.addItem(appListBean);
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
