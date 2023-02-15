package com.nhnacademy.bookpub.bookpubfront.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch 서버와 연동하기 위한 config.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "elastic")
public class ElasticConfig {
    private String url;
    private String indexName;

    /**
     * ElasticSearch URL 조회.
     *
     * @return elasticsearch url
     */
    public String getUrl() {
        return url;
    }

    /**
     * url 세팅.
     *
     * @param url elasticsearch url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 검색할 ElasticSearch 인덱스 조회.
     *
     * @return 인덱스명
     */
    public String getIndexName() {
        return indexName;
    }

    /**
     * 인덱스명 세팅.
     *
     * @param indexName 인덱스명
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}
