package com.applistwithrxjava.wanyt.fragment;

import android.view.View;

import com.applistwithrxjava.wanyt.R;
import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.bumptech.glide.Glide;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/7/21 16:51
 * <p>
 * author wanyt
 * <p>
 * Description:reduce()和scan()
 */
public class FragmentReduce extends BaseFragment {

    @Override
    protected void initView() {
        rvList.setVisibility(View.GONE);
        tvText.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(R.mipmap.pic_reduce)
                .into(ivBulletGraph);
    }

    @Override
    protected void setListView() {
        getAppObservable()
                .take(5)
                .reduce(new Func2<AppListBean, AppListBean, AppListBean>() {
                    @Override
                    public AppListBean call(AppListBean appListBean, AppListBean appListBean2) {
                        String reduceName = appListBean.name + appListBean2.name;
                        appListBean.name = reduceName;
                        return appListBean;
                    }
                })//用于对数据进行累加，用整型举例比较贴切，本例中用string进行了演示。
//                .scan(new Func2<AppListBean, AppListBean, AppListBean>() {
//                    @Override
//                    public AppListBean call(AppListBean appListBean, AppListBean appListBean2) {
//                        String reduceName = appListBean.name + appListBean2.name;
//                        appListBean.name = reduceName;
//                        return appListBean;
//                    }//这个函数和reduce()是相似的，他们的主要不同在于每次累加后Scan会返回一次结果，而reduce只会返回最后的结果。
//                })//如果你用这个在这个例子中运行scan(),运行结果是一样的。
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppListBean appListBean) {
                        tvText.setText(appListBean.name);
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }
}
