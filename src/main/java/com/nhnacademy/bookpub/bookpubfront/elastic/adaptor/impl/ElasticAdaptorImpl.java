package com.nhnacademy.bookpub.bookpubfront.elastic.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.ElasticConfig;
import com.nhnacademy.bookpub.bookpubfront.elastic.adaptor.ElasticAdaptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * ElasticSearch 서버와 통신하는 어댑터 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ElasticAdaptorImpl implements ElasticAdaptor {

    private final RestTemplate restTemplate;
    private final ElasticConfig elasticConfig;

    /**
     * {@inheritDoc}
     */
    @Override
    public String requestSearchProduct(String keyword) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        elasticConfig.getUrl() + "/" + elasticConfig.getProductIndexName() + "/_search")
                .encode()
                .toUriString();

        String body = "{\n" +
                "    \"query\": {\n" +
                "        \"multi_match\": {\n" +
                "            \"analyzer\": \"suggest_search_analyzer\",\n" +
                "            \"query\": \"" + keyword + "\",\n" +
                "            \"fields\": [\n" +
                "                \"title\",\n" +
                "                \"titlenori\",\n" +
                "                \"titlejaso\"\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String requestSearchAll(String keyword) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        elasticConfig.getUrl() + "/" + elasticConfig.getProductIndexName() + "," + elasticConfig.getCsIndexName() + "/_search")
                .encode()
                .toUriString();

        String body = "{\n" +
                "  \"query\": {\n" +
                "    \"multi_match\": {\n" +
                "      \"analyzer\": \"suggest_search_analyzer\", \n" +
                "      \"query\": \"" + keyword + "\",\n" +
                "      \"fields\": [\n" +
                "        \"title\",\n" +
                "        \"titlenori\",\n" +
                "        \"titlejaso\",\n" +
                "        \"cstitlejaso^100\",\n" +
                "        \"cstitlenori^100\",\n" +
                "        \"cscontentjaso\",\n" +
                "        \"cscontentnori\"\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class
        ).getBody();
    }
}
