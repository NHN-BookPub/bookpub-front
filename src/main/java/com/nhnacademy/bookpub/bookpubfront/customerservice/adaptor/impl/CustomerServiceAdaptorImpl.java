package com.nhnacademy.bookpub.bookpubfront.customerservice.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.customerservice.adaptor.CustomerServiceAdaptor;
import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.GetCustomerServiceListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 고객서비스 아답터의 구현체.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CustomerServiceAdaptorImpl implements CustomerServiceAdaptor {
    private final RestTemplate restTemplate;
    private static final String AUTH_SERVICE_URL = "/token/services";
    private static final String SERVICE_URL = "/api/services";

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCustomerService(MultiValueMap<String, Object> requestMap) {
        String url = GateWayConfig.getGatewayUrl() + AUTH_SERVICE_URL;

        HttpEntity<MultiValueMap<String, Object>> entity =
                new HttpEntity<>(requestMap,
                        makeHeader(MediaType.MULTIPART_FORM_DATA,
                                List.of(MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON)));

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCustomerServiceListResponseDto> getCustomerServices(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + AUTH_SERVICE_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<PageResponse<GetCustomerServiceListResponseDto>>() {
                        })
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCustomerServiceListResponseDto>
    getCustomerServiceByCodeName(String name, Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl()
                        + SERVICE_URL
                        + "/" + name)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<PageResponse<GetCustomerServiceListResponseDto>>() {
                        })
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCustomerServiceListResponseDto> getCustomerServiceByCategory(String category, Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl()
                        + SERVICE_URL
                        + "/category/" + category)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<PageResponse<GetCustomerServiceListResponseDto>>() {
                        })
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetCustomerServiceListResponseDto getCustomerServiceByNo(Integer serviceNo) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl()
                + "/api/service/"
                + serviceNo)
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<GetCustomerServiceListResponseDto>() {

                }).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomerService(Integer serviceNo) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl()
                + AUTH_SERVICE_URL + "/" + serviceNo)
                .toUriString();

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class);
    }
}
