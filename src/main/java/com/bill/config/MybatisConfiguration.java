package com.bill.config;

import com.bill.config.db.DynamicDataSource;
import com.bill.config.db.DynamicDataSourceTransactionManager;
import com.bill.config.db.DynamicPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/***
 * mybatis 配置，在DataSource之后init
 *
 * @author f
 * @date 2018-07-25
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration {


    @Resource(name = "dataSourceMaster")
    private DataSource masterDataSource;

    @Resource(name = "dataSourceSlave")
    private DataSource slaveDataSource;

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        DynamicPlugin plugin = new DynamicPlugin();
        Interceptor[] plugins = {plugin};
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setWriteDataSource(masterDataSource);
        dynamicDataSource.setReadDataSource(slaveDataSource);
        /*默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put(DynamicDataSourceGlobal.WRITE, masterDataSource);
        dsMap.put(DynamicDataSourceGlobal.READ, slaveDataSource);
        dynamicDataSource.setTargetDataSources(dsMap);*/
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
        return template;

    }

    @Bean(name = "transactionManager")
    public DynamicDataSourceTransactionManager transactionManager() {
        DynamicDataSourceTransactionManager manager = new DynamicDataSourceTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManager());
        return template;
    }

}
