package com.applistwithrxjava.wanyt.bean.data;

import com.applistwithrxjava.wanyt.bean.CatalogBean;

import java.util.ArrayList;

/**
 * Created on 2016/6/23 10:08
 * <p/>
 * author wanyt
 * <p/>
 * Description:RxJava方法目录的集合
 */
public class DataCatalogList {

    private DataCatalogList() {}

    public static final String FROM = "from";
    public static final String JUST = "just";
    public static final String ERROR = "error";
    public static final String EMPTY = "empty";
    public static final String NEVER = "never";
    public static final String INTERVAL = "interval";
    public static final String RANGE = "range";
    public static final String TIMER = "timer";
    public static final String REPEAT = "repeat";
    public static final String DEFER = "defer";

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

        CatalogBean error = new CatalogBean();
        error.flag = ERROR;
        error.method = "error(Exception)";
        error.fullName = "Observable.error(Exception e);";
        error.describe = "Observable发送一个异常之后结束";

        CatalogBean empty = new CatalogBean();
        empty.flag = EMPTY;
        empty.method = "empty()";
        empty.fullName = "Observable.empty();";
        empty.describe = "Observable只调用onCompleted()，然后事件结束";

        CatalogBean never = new CatalogBean();
        never.flag = NEVER;
        never.method = "never()";
        never.fullName = "Observable.never();";
        never.describe = "Observable什么也不调用，事件就结束了，但是代码并没有阻塞线程";

        CatalogBean interval = new CatalogBean();
        interval.flag = INTERVAL;
        interval.method = "interval()";
        interval.fullName = "Observable.interval();";
        interval.describe = "创建一个无限计时序列，从整数0开始每间隔指定时间收发射递增数据。只有调用Subscription.unsubscribe()之后才会停止发射数据。";

        CatalogBean range = new CatalogBean();
        range.flag = RANGE;
        range.method = "range(int, int)";
        range.fullName = "Observable.range(int start, int count);";
        range.describe = "发射一个整数序列从start开始，发射count个。";

        CatalogBean timer = new CatalogBean();
        timer.flag = TIMER;
        timer.method = "timer(long, TimeUnit)";
        timer.fullName = "Observable.timer(long delay, TimeUnit millions)";
        timer.describe = "创建一个序列延迟时间delay后，发射数据0，事件结束。";

        CatalogBean repeat = new CatalogBean();
        repeat.flag = REPEAT;
        repeat.method = "repeat(int)";
        repeat.fullName = "Observable.repeat(int counts);";
        repeat.describe = "将数据重复发送counts次，如果不传参数数据序列会无限次数的发送，直至宕机。";

        CatalogBean defer = new CatalogBean();
        defer.flag = DEFER;
        defer.method = "defer()";
        defer.fullName = "Observable.defer();";
        defer.describe = "数据将延迟发射，直到被订阅才会发射数据";

        catalogList.add(from);
        catalogList.add(just);
        catalogList.add(error);
        catalogList.add(empty);
        catalogList.add(never);
        catalogList.add(interval);
        catalogList.add(range);
        catalogList.add(timer);
        catalogList.add(repeat);
        catalogList.add(defer);

        return catalogList;
    }
}
