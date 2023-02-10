package com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor.SubscribeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * Shop 서버와 통신하기위한 Adaptor 구현 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class SubscribeAdaptorImpl implements SubscribeAdaptor {

    private final RestTemplate restTemplate;
    private static final String SUBSCRIBE_TOKEN_URL = GateWayConfig.getGatewayUrl() + "/token/subscribes";

    private static final String SUBSCRIBE_API = GateWayConfig.getGatewayUrl() + "/api/subscribes";

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubscribeRequest(CreateSubscribeRequestDto dto, MultipartFile image) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("dto", dto);
        body.add("image", image.getResource());
        HttpEntity<MultiValueMap<String, Object>> http = new HttpEntity<>(body, makeHeader(MediaType.MULTIPART_FORM_DATA));

        restTemplate.exchange(SUBSCRIBE_TOKEN_URL,
                HttpMethod.POST,
                http,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifySubscribeRequest(Long subscribeNo, MultipartFile image, ModifySubscribeRequestDto dto) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("dto", dto);
        body.add("image", image.getResource());
        HttpEntity<MultiValueMap<String, Object>> http = new HttpEntity<>(body, makeHeader(MediaType.MULTIPART_FORM_DATA));

        restTemplate.exchange(SUBSCRIBE_TOKEN_URL + "/" + subscribeNo,
                HttpMethod.POST,
                http,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetSubscribeResponseDto> getSubscribesRequest(Pageable pageable) {
        String url = SUBSCRIBE_API + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
        return restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetSubscribeResponseDto>>() {
                }).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetSubscribeDetailResponseDto getSubscribeDetailRequest(Long subscribeNo) {
        String url = SUBSCRIBE_API + "/" + subscribeNo;
        return restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        GetSubscribeDetailResponseDto.class)
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletedSubscribeRequest(Long subscribeNo, boolean deleted) {
        String url = SUBSCRIBE_TOKEN_URL + "/" + subscribeNo + "?isDeleted=" + !deleted;
        restTemplate.exchange(url,
                HttpMethod.DELETE,
                new HttpEntity<>(makeHeader()),
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renewedSubscribeRequest(Long subscribeNo, boolean renewed) {
        String url = SUBSCRIBE_TOKEN_URL + "/" + subscribeNo + "?isRenewed=" + !renewed;

        restTemplate.exchange(url,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubscribeProducts(Long subscribeNo, List<Long> products) {
        CreateSubscribeProductRequestDto dto = new CreateSubscribeProductRequestDto(products);
        String url = SUBSCRIBE_TOKEN_URL + "/" + subscribeNo + "/product-list";
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(dto, makeHeader()),
                Void.class
        );
    }

}
