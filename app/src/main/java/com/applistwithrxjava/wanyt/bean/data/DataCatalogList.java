package com.applistwithrxjava.wanyt.bean.data;

import com.applistwithrxjava.wanyt.bean.CatalogBean;

import java.util.ArrayList;

/**
 * Created on 2016/6/23 10:08
 * <p/>
 * author wanyt
 * <p/>
 * Description:RxJava方法目录的摘要
 */
public class DataCatalogList {

    private DataCatalogList() {}

    public static final String FROM = "from";
    public static final String JUST = "just";
    public static final String INTERVAL = "interval";
    public static final String FILTER = "filter";
    public static final String TAKE = "take";
    public static final String DISTINCT = "distinct";
    public static final String SKIP = "skip";

    private static DataCatalogList instance;

    public static DataCatalogList getInstance(){
        if(instance == null){
            instance = new DataCatalogList();
        }
        return instance;
    }

    public ArrayList<CatalogBean> getCatalogList(){
        ArrayList<CatalogBean> catalogList = new ArrayList<>();

        CatalogBean from = new CatalogBean();

        from.flag = FROM;
        from.method = "from(List)";
        from.fullName = "Observable.from(List list)";
        from.describe = "接收一个集合/数组，并将集合/数组中的元素一个一个的发射出去";

        CatalogBean just = new CatalogBean();
        just.flag = JUST;
        just.method = "just(T t)";
        just.fullName = "Observable.just(T v1, T v2, T v3, …);";
        just.describe = "Observable会依次发送参数，当没有数据发送时调用onCompleted()";

        CatalogBean interval = new CatalogBean();
        interval.flag = INTERVAL;
        interval.method = "interval()";
        interval.fullName = "Observable.interval();";
        interval.describe = "创建一个无限计时序列，从整数0开始每间隔指定时间收发射递增数据。只有调用Subscription.unsubscribe()之后才会停止发射数据。";

        CatalogBean filter = new CatalogBean();
        filter.flag = FILTER;
        filter.method = "filter()";
        filter.fullName = "Observable.filter()";
        filter.describe = "该方法返回一个布尔值，只有符合条件的数据才会被发射出去";

        CatalogBean take = new CatalogBean();
        take.flag = TAKE;
        take.method = "take()";
        take.fullName = "Observable.take(int count)";
        take.describe = "发射该数据序列的前count个数据";

        CatalogBean distinct = new CatalogBean();
        distinct.flag = DISTINCT;
        distinct.method = "distinct()";
        distinct.fullName = "Observable.distinct()";
        distinct.describe = "distinct()作用于整个序列，可以对序列中的数据进行去重操作";

        CatalogBean skip = new CatalogBean();
        skip.flag = SKIP;
        skip.method = "skip()";
        skip.fullName = "Observable.skip(int count)";
        skip.describe = "跳过数据序列的前count个数据然后发射剩余的数据";

        catalogList.add(from);
        catalogList.add(just);
        catalogList.add(interval);
        catalogList.add(filter);
        catalogList.add(take);
        catalogList.add(distinct);
        catalogList.add(skip);

        return catalogList;
    }
}
