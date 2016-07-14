package com.applistwithrxjava.wanyt.fragment;

import com.bumptech.glide.Glide;

/**
 * Created on 2016/7/13 15:23
 * <p>
 * author wanyt
 * <p>
 * Description:join()的使用,join()方法没有重载函数
 */
public class FragmentJoin extends BaseFragment {

    @Override
    protected void initView() {
        Glide.with(this)
                .load("https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/timer.png")
                .into(ivBulletGraph);
    }

    @Override
    protected void setListView() {


    }

    @Override
    protected void internalMessage(String flag, Object event) {

    }
}
