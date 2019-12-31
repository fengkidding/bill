package com.bill.config;

import com.bill.common.log.LogBackUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * elasticsearch配置
 *
 * @author f
 * @date 2019-11-11
 */
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchConfig {

    private String host;

    private Integer port;

    private String clusterName;

    private String schema;

    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = null;
        try {
            HttpHost[] httpHosts = new HttpHost[1];
            httpHosts[0] = new HttpHost(host, port, schema);
            RestClientBuilder clientBuilder = RestClient.builder(httpHosts);
            client = new RestHighLevelClient(clientBuilder);
        } catch (Exception e) {
            LogBackUtils.error("restHighLevelClient error", e);
        }
        return client;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
