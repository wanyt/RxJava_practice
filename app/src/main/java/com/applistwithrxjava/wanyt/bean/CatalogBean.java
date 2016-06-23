package com.applistwithrxjava.wanyt.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created on 2016/6/23 10:04
 * <p/>
 * author wanyt
 * <p/>
 * Description:RxJava方法list的数据模型
 */
public class CatalogBean implements Serializable {

    public String method;         //方法名称
    public String describe;       //方法描述
    public Drawable bulletGraphs; //子弹图
    public String fullName;       //方法全称
    public boolean isCheck;       //当前方法是否被选中

}
