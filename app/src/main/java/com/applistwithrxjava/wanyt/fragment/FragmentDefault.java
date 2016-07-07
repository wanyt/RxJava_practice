package com.applistwithrxjava.wanyt.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.applistwithrxjava.wanyt.R;

/**
 * Created on 2016/6/30 16:08
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class FragmentDefault extends BaseFragment {


    @Override
    protected void initView() {
        llRoot.removeAllViews();
        View view = LayoutInflater.from(getActivity())
                                .inflate(R.layout.fragment_default_layout, null, false);

        llRoot.addView(view);
    }

    @Override
    protected void setListView() {

    }
}
