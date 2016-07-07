package com.applistwithrxjava.wanyt.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.applistwithrxjava.wanyt.R;
import com.applistwithrxjava.wanyt.adapter.FromAdapter;
import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.applistwithrxjava.wanyt.recyclerdivider.GridDivider;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

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
        tvMethod.setText(catalog.fullName);
        tvDesc.setText(catalog.describe);
        ivBulletGraph.setBackgroundResource(R.mipmap.pic_from);

        list = new ArrayList<>();
        initList(list);
    }


    private FromAdapter adapter;
    private ArrayList<AppListBean> list;

    private void initList(ArrayList<AppListBean> list) {
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvList.addItemDecoration(new GridDivider(getActivity(), R.drawable.grid_divider));
        FromAdapter listAdapter = new FromAdapter(getActivity(), list);
        rvList.setAdapter(listAdapter);
    }

    @Override
    protected void setListView() {
        Observable.from(getAppList())
                .subscribe(new Observer<AppListBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "onCompleted()", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppListBean appListBean) {
                        Logger.d(list.size());
                        list.add(appListBean);
//                        adapter.addItem(list.size() - 1, appListBean);
                    }
                });
    }


}
