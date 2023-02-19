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
    private String productIndexName;
    private String csIndexName;

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
     * 검색할 ElasticSearch 상품 인덱스 조회.
     *
     * @return 인덱스명
     */
    public String getProductIndexName() {
        return productIndexName;
    }

    /**
     * 상품 인덱스명 세팅.
     *
     * @param productIndexName 인덱스명
     */
    public void setProductIndexName(String productIndexName) {
        this.productIndexName = productIndexName;
    }

    /**
     * 고객 서비스 인덱스 조회.
     *
     * @return 인덱스명
     */
    public String getCsIndexName() {
        return csIndexName;
    }

    /**
     * 고객 서비스 인덱스 세팅.
     *
     * @param csIndexName 인덱스명
     */
    public void setCsIndexName(String csIndexName) {
        this.csIndexName = csIndexName;
    }
}
