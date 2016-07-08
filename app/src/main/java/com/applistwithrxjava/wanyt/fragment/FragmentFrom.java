package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.bumptech.glide.Glide;

import rx.Observable;
import rx.Observer;

/**
 * Created on 2016/6/23 16:51
 * <p>
 * author wanyt
 * <p>
 * Description:在这个例子中，把获取到的应用列表中的元素一个一个的发射出去
 */
public class FragmentFrom extends BaseFragment {

    private final String tag = ".fragment.FragmentFrom";
    private final String imageUrl = "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/from.png";

    @Override
    protected void initView() {
        Glide.with(FragmentFrom.this)
                .load(imageUrl)
                .into(ivBulletGraph);

        initList();
    }

    private FromAdapter listAdapter;

    private void initList() {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        listAdapter = new FromAdapter(getActivity());
        rvList.setAdapter(listAdapter);
    }

    @Override
    protected void setListView() {

        Observable.from(getAppList())
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "From Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppListBean appListBean) {
                        listAdapter.addItem(0, appListBean);
                    }
                });
    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }

}
