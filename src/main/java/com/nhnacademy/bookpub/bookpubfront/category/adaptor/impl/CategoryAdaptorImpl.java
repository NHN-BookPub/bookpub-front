package com.nhnacademy.bookpub.bookpubfront.category.adaptor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.category.adaptor.CategoryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.CreateCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.ModifyCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * shop 과 연동을 위한 CategoryAdaptor 구현.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CategoryAdaptorImpl implements CategoryAdaptor {

    private final GateWayConfig gateWayConfig;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private static final String CATEGORY_URI = "/api/categories";
    private static final String CATEGORY_AUTH_URI = "/token/categories";


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddCategory(CreateCategoryRequestDto createCategoryRequestDto)
            throws JsonProcessingException {

        String request = objectMapper.writeValueAsString(createCategoryRequestDto);

        String url = gateWayConfig.getGatewayUrl() + CATEGORY_AUTH_URI;

        HttpEntity<String> httpEntity = new HttpEntity<>(request, Utils.makeHeader());

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
                Void.class);

        Utils.checkError(response);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyCategory(ModifyCategoryRequestDto modifyCategoryRequestDto)
            throws JsonProcessingException {

        String request = objectMapper.writeValueAsString(modifyCategoryRequestDto);
        String url = gateWayConfig.getGatewayUrl() + CATEGORY_AUTH_URI;

        HttpEntity<String> httpEntity = new HttpEntity<>(request, Utils.makeHeader());

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity,
                Void.class);

        Utils.checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCategoryResponseDto> requestCategoryList() {

        String url = gateWayConfig.getGatewayUrl() + CATEGORY_URI;

        ResponseEntity<List<GetCategoryResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        Utils.checkError(response);
        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCategoryResponseDto> requestParentCategoryList() {
        String url = gateWayConfig.getGatewayUrl() + CATEGORY_URI + "/parent";

        ResponseEntity<List<GetCategoryResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        Utils.checkError(response);
        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetCategoryResponseDto requestCategory(Integer categoryNo) {

        String url = gateWayConfig.getGatewayUrl() + CATEGORY_URI + "/" + categoryNo;

        ResponseEntity<GetCategoryResponseDto> response = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        Utils.checkError(response);
        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetParentCategoryWithChildrenResponseDto> requestParentWithChildList() {
        String url = gateWayConfig.getGatewayUrl() + CATEGORY_URI + "/parent-child";

        ResponseEntity<List<GetParentCategoryWithChildrenResponseDto>> response = restTemplate
                .exchange(url, HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        Utils.checkError(response);
        return response.getBody();
    }
}
