package com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor.SubscribeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
    private static final String SUBSCRIBE_TOKEN_URL =  GateWayConfig.getGatewayUrl()+"/token/subscribes";

    /**
     * 구독을 추가하기위한 shop 서버로 요청입니다.
     *
     * @param dto the dto
     */
    public void addSubscribeRequest(CreateSubscribeRequestDto dto) {
        HttpEntity<CreateSubscribeRequestDto> http = new HttpEntity<>(dto,makeHeader());

        restTemplate.exchange(SUBSCRIBE_TOKEN_URL,
                HttpMethod.POST,
                http,
                Void.class);
    }

    /**
     * 구독정보들을 받기위한 shop 서버로의 GET 요청입니다.
     *
     * @param pageable 페이징정보
     * @return 구독\정보 반환
     */
    public PageResponse<GetSubscribeResponseDto> getSubscribesRequest(Pageable pageable) {
        String url = SUBSCRIBE_TOKEN_URL + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
        return restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetSubscribeResponseDto>>() {
                }).getBody();
    }

}
