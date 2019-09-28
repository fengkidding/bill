package com.bill.config.db;

/**
 * 数据源持有 读写
 *
 * @author f
 * @date 2018-07-25
 */
public final class DynamicDataSourceHolder {

    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal();

    private DynamicDataSourceHolder() {
    }

    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        holder.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource() {
        return (DynamicDataSourceGlobal) holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}

