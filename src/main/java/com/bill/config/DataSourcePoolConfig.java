package com.bill.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库druid配置
 *
 * @author f
 * @date 2019-12-27
 */
@Component
@ConfigurationProperties(prefix = "druid")
public class DataSourcePoolConfig {

    private final String prefix = "druid.";

    private Properties masterProperties;

    private Properties slaveProperties;

    private Map<String, String> master;

    private Map<String, String> slave;

    /**
     * 启动时加载主从配置
     */
    @PostConstruct
    public void init() {
        masterProperties = convert(master);
        slaveProperties = convert(slave);
    }

    /**
     * map转换成Properties
     *
     * @param map
     * @return
     */
    private Properties convert(Map<String, String> map) {
        Properties properties = new Properties();
        if (!CollectionUtils.isEmpty(map)) {
            map.forEach((key, value) -> {
                if (StringUtils.isNotBlank(value)) {
                    properties.setProperty(prefix + key, value);
                }
            });
        }
        return properties;
    }

    public void setMaster(Map<String, String> master) {
        this.master = master;
    }

    public void setSlave(Map<String, String> slave) {
        this.slave = slave;
    }

    public Map<String, String> getMaster() {
        return master;
    }

    public Map<String, String> getSlave() {
        return slave;
    }

    public Properties getMasterProperties() {
        return masterProperties;
    }

    public Properties getSlaveProperties() {
        return slaveProperties;
    }
}
