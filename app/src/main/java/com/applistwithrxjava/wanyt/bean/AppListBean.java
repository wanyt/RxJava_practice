package com.applistwithrxjava.wanyt.bean;

import android.graphics.drawable.Drawable;

/**
 * Created on 2016/7/7 15:20
 * <p>
 * author wanyt
 * <p>
 * Description:应用列表的数据模型
 */
public class AppListBean {

    public String name;
    public Drawable drawable;

    public AppListBean(String name, Drawable drawable) {
        this.name = name;
        this.drawable = drawable;
    }

}
