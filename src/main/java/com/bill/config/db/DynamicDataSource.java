package com.bill.config.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据源
 *
 * @author f
 * @date 2018-07-25
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Object writeDataSource;
    private Object readDataSource;

    public DynamicDataSource() {
    }

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        } else {
            this.setDefaultTargetDataSource(this.writeDataSource);
            Map<Object, Object> targetDataSources = new HashMap();
            targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), this.writeDataSource);
            if (this.readDataSource != null) {
                targetDataSources.put(DynamicDataSourceGlobal.READ.name(), this.readDataSource);
            }

            this.setTargetDataSources(targetDataSources);
            super.afterPropertiesSet();
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();
        return dynamicDataSourceGlobal != null && dynamicDataSourceGlobal != DynamicDataSourceGlobal.WRITE ? DynamicDataSourceGlobal.READ.name() : DynamicDataSourceGlobal.WRITE.name();
    }

    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    public Object getWriteDataSource() {
        return this.writeDataSource;
    }

    public Object getReadDataSource() {
        return this.readDataSource;
    }

    public void setReadDataSource(Object readDataSource) {
        this.readDataSource = readDataSource;
    }
}