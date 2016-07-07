package com.applistwithrxjava.wanyt.fragment;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applistwithrxjava.wanyt.Constants;
import com.applistwithrxjava.wanyt.R;
import com.applistwithrxjava.wanyt.bean.AppListBean;
import com.applistwithrxjava.wanyt.bean.CatalogBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/6/23 16:50
 * <p>
 * author wanyt
 * <p>
 * Description:所有fragment的基类
 */
public abstract class BaseFragment extends Fragment {

    private final String tag = ".fragment.BaseFragment";

    CatalogBean catalog;
    Unbinder bind;

    @BindView(R.id.ll_fragment_base_root)
    LinearLayout llRoot;
    @BindView(R.id.tv_fragment_base_method)
    TextView tvMethod;
    @BindView(R.id.tv_fragment_base_desc)
    TextView tvDesc;
    @BindView(R.id.iv_fragment_base_bulletgraph)
    ImageView ivBulletGraph;
    @BindView(R.id.rv_fragment_base_list)
    RecyclerView rvList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if(arguments != null){
            catalog = (CatalogBean) arguments.getSerializable(Constants.CATALOG_PARAMS);
        }

        View view = inflater.inflate(R.layout.fragment_base, container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListView();
    }

    protected abstract void initView();

    protected abstract void setListView();



    protected Observable<AppListBean> getAppObservable(){

        return Observable.create(new Observable.OnSubscribe<AppListBean>() {
            @Override
            public void call(Subscriber<? super AppListBean> subscriber) {

                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> resolveInfos =
                        getActivity().getPackageManager().queryIntentActivities(intent, 0);

                List<AppListBean> list = new ArrayList<>();

                for(ResolveInfo info : resolveInfos){
                    String packageName = info.activityInfo.applicationInfo.packageName;//获取包名
                    try {
                        PackageInfo packageInfo = //获取包信息
                                getActivity().getPackageManager().getPackageInfo(packageName, 0);
                        ApplicationInfo applicationInfo = packageInfo.applicationInfo;//获取应用信息

                        String name = applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
                        Drawable drawable = applicationInfo.loadIcon(getActivity().getPackageManager());
                        AppListBean app = new AppListBean(name, drawable);
                        Logger.d("name"+name);
                        subscriber.onNext(app);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if(!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }
            }
        });
    }

    protected List<AppListBean> getAppList(){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos =
                getActivity().getPackageManager().queryIntentActivities(intent, 0);

        List<AppListBean> list = new ArrayList<>();

        for(ResolveInfo info : resolveInfos){
            String packageName = info.activityInfo.applicationInfo.packageName;//获取包名
            try {
                PackageInfo packageInfo = //获取包信息
                        getActivity().getPackageManager().getPackageInfo(packageName, 0);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;//获取应用信息

                String name = applicationInfo.loadLabel(getActivity().getPackageManager()).toString();//获取应用名称
                Drawable drawable = applicationInfo.loadIcon(getActivity().getPackageManager());//获取应用icon
                AppListBean app = new AppListBean(name, drawable);
                list.add(app);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
