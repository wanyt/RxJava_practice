package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.applistwithrxjava.wanyt.Constants;
import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/7/8 11:31
 * <p>
 * author wanyt
 * <p>
 * Description:just()的fragment，并且使用repeat(),比较简单
 *              range(int start, int count),这个方法是从一个整型的值开始发射数据，每次递增发射count个数据
 *              timer(int delay, TimeUnit time)，延迟delay，再发射数据
 */
public class FragmentJust extends BaseFragment{

    @Override
    protected void initView() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FromAdapter(getActivity());
        rvList.setAdapter(adapter);
    }

    private Subscription subscribe;
    private FromAdapter adapter;

    @Override
    protected void setListView() {
        List<AppListBean> appList = getAppList();

        if(appList != null && appList.size() >= 5){
            subscribe = Observable.just(appList.get(0),//将参数一次发射出去
                    appList.get(1), appList.get(2), appList.get(3))
//                    .repeat()//无限次数地重复发射数据源,谨慎使用
                    .repeat(2)//发射 参数次 数据源
                    .subscribeOn(Schedulers.newThread())//线程切换，性能优化
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AppListBean>() {
                        @Override
                        public void onCompleted() {
                            Toast.makeText(getActivity(), "Just Completed", Toast.LENGTH_SHORT).show();
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
