package com.nhnacademy.bookpub.bookpubfront.tag.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.tag.adaptor.TagAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * TagAdaptor 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class TagAdaptorImpl implements TagAdaptor {

    private final RestTemplate restTemplate;
    private final GateWayConfig gateWayConfig;

    private static final String API_PATH = "/api/tags";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetTagResponseDto> getTags() {
        String url = gateWayConfig.getGatewayUrl() + API_PATH;

        return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(makeHeaders()),
                    new ParameterizedTypeReference<List<GetTagResponseDto>>() {
                    })
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTag(AddTagRequestDto request) {
        String url = gateWayConfig.getGatewayUrl() + API_PATH;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity<>(request, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyTag(ModifyTagRequestDto request) {
        String url = gateWayConfig.getGatewayUrl() + API_PATH;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity<>(request, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
        );

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTag(Integer tagNo) {
        String url = gateWayConfig.getGatewayUrl() + API_PATH + "/" + tagNo;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(makeHeaders()),
                Void.class
        );
    }

    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }

    private static HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
