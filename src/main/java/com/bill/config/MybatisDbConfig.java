package com.bill.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.bill.config.db.DynamicDataSource;
import com.bill.config.db.DynamicDataSourceTransactionManager;
import com.bill.config.db.DynamicPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 安全 数据库配置
 *
 * @author f
 * @date 2019-12-27
 */
@Configuration
@MapperScan(basePackages = "com.bill.dao.db", sqlSessionTemplateRef = "dbSqlSessionTemplate")
public class MybatisDbConfig {

    @Value("${druid.master.url}")
    private String masterUrl;

    @Value("${druid.master.username}")
    private String masterUserName;

    @Value("${druid.master.password}")
    private String masterPasseord;

    @Value("${druid.slave.url}")
    private String slaveUrl;

    @Value("${druid.slave.username}")
    private String slaveUserName;

    @Value("${druid.slave.password}")
    private String slavePassword;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.configuration.map-underscore-to-camel-case}")
    private boolean mapUnderscoreToCamelCase;

    @Value("${mybatis.configuration.default-statement-timeout}")
    private Integer defaultDtatementTimeout;

    @Autowired
    private DataSourcePoolConfig dataSourcePoolConfig;

    /**
     * 创建主库
     *
     * @return
     */
    @Bean("dbDataSourceMaster")
    public DataSource createDataSourceMaster() {
        return createDateSource(masterUrl, masterUserName, masterPasseord, dataSourcePoolConfig.getMasterProperties());
    }

    /**
     * 创建从库
     *
     * @return
     */
    @Bean("dbDataSourceSlave")
    public DataSource createDataSourceSlave() {
        return createDateSource(slaveUrl, slaveUserName, slavePassword, dataSourcePoolConfig.getSlaveProperties());
    }

    /**
     * 读写分离
     *
     * @param masterDataSource
     * @param slaveDataSource
     * @return
     */
    @Bean("dbDynamicDataSource")
    public DataSource createDynamicDataSource(@Qualifier("dbDataSourceMaster") DataSource masterDataSource, @Qualifier("dbDataSourceSlave") DataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setWriteDataSource(masterDataSource);
        dynamicDataSource.setReadDataSource(slaveDataSource);
        return dynamicDataSource;
    }

    /**
     * 工厂
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("dbSqlSessionFactoryBean")
    public SqlSessionFactoryBean createSqlSessionFactoryBean(@Qualifier("dbDynamicDataSource") DataSource dataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        sqlSessionFactoryBean.getObject().getConfiguration().setDefaultExecutorType(ExecutorType.BATCH);
        sqlSessionFactoryBean.getObject().getConfiguration().setDefaultStatementTimeout(defaultDtatementTimeout);
        sqlSessionFactoryBean.setConfiguration(sqlSessionFactoryBean.getObject().getConfiguration());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        DynamicPlugin plugin = new DynamicPlugin();
        Interceptor[] plugins = {plugin};
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean;
    }

    /**
     * session
     *
     * @param sqlSessionFactoryBean
     * @return
     * @throws Exception
     */
    @Bean("dbSqlSessionTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("dbSqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
        return template;

    }

    /**
     * 事务
     *
     * @param dataSource
     * @return
     */
    @Bean("dbTransactionManager")
    public DynamicDataSourceTransactionManager createTransactionManager(@Qualifier("dbDynamicDataSource") DataSource dataSource) {
        DynamicDataSourceTransactionManager manager = new DynamicDataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    /**
     * 创建数据源
     *
     * @param url
     * @param userName
     * @param password
     * @param properties
     * @return
     */
    private DataSource createDateSource(String url, String userName, String password, Properties properties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setConnectProperties(properties);
        return dataSource;
    }

}

