package com.bill.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/***
 * DataSource 配置 构建主从库信息
 *
 * @author f
 * @date 2018-07-25
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.bill.dao.db")
public class DataSourceConfiguration {

    /**
     * 主库
     *
     * @return
     */
    @Primary
    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties("druid.master")
    public DataSource dataSourceMaster() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从库
     *
     * @return
     */
    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties("druid.slave")
    public DataSource dataSourceSlave() {
        return DruidDataSourceBuilder.create().build();
    }
}

