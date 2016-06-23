package com.applistwithrxjava.wanyt.fragment;

/**
 * Created on 2016/6/23 16:51
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class FragmentFrom extends BaseFragment {

    private final String tag = ".fragment.FragmentFrom";


    @Override
    protected void initView() {
        tvMethod.setText(catalog.method);
        tvDesc.setText(catalog.describe);

    }

    @Override
    protected void setListView() {

    }

}
