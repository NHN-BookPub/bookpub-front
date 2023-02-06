package com.nhnacademy.bookpub.bookpubfront.tag.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.tag.adaptor.TagAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    private static final String TOKEN_API_PATH = getGatewayUrl() + "/token/tags";
    private static final String TAG_API_PATH = getGatewayUrl() + "/api/tags";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetTagResponseDto> getTags() {
        return restTemplate.exchange(
                        TAG_API_PATH,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<List<GetTagResponseDto>>() {
                        })
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTag(AddTagRequestDto request) {
        restTemplate.exchange(
                TOKEN_API_PATH,
                HttpMethod.POST,
                new HttpEntity<>(request, makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyTag(ModifyTagRequestDto request) {
        restTemplate.exchange(
                TOKEN_API_PATH,
                HttpMethod.PUT,
                new HttpEntity<>(request, makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTag(Integer tagNo) {
        String url = TOKEN_API_PATH + "/" + tagNo;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }
}
