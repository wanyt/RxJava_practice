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
 *              all();用于检查发射的数据是否都满足某一条件，如果满足返回一个发射true的observable，只要有一个不满足就返回一个发射false的observable
 *              exits();如果发射的数据中有一个满足条件就返回发射true的observable，否则返回发射false的observable
 *              contain();检查数据序列中是否有相同的数据，如果有返回发射true的observable，否则返回发射false的observable
 *              defaultIfEmpty();如果发射的数据序列中没有发射任何数据，会强制返回一个默认的值
 *              elementAt();发射数据序列中指定位置的数据
 *              sequenceEqual();用来比较两个同类型等长的数据序列中的元素是否相同，如果都相同就返回发射true的observable，否则返回发射false的observable
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
