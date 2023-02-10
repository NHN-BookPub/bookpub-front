package com.nhnacademy.bookpub.bookpubfront.subscribe.service.impl;

import com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor.SubscribeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.service.SubscribeService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 서비스 구현체입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeAdaptor subscribeAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubscribe(CreateSubscribeRequestDto dto, MultipartFile image) {
        subscribeAdaptor.addSubscribeRequest(dto, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifySubscribe(Long scribeNo, MultipartFile image, ModifySubscribeRequestDto dto) {
        subscribeAdaptor.modifySubscribeRequest(scribeNo, image, dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetSubscribeResponseDto> getSubscribeList(Pageable pageable) {
        return subscribeAdaptor.getSubscribesRequest(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetSubscribeDetailResponseDto getSubscribeDetail(Long subscribeNo) {
        return subscribeAdaptor.getSubscribeDetailRequest(subscribeNo);
    }

    @Override
    public void deleteSubscribe(Long subscribeNo, boolean deleted) {
        subscribeAdaptor.deletedSubscribeRequest(subscribeNo, deleted);
    }

    @Override
    public void renewedSubscribe(Long subscribeNo, boolean renewed) {
        subscribeAdaptor.renewedSubscribeRequest(subscribeNo, renewed);
    }

    @Override
    public void addSubscribeProductList(Long subscribeNo, String products) {
        List<Long> subscribeProducts = Arrays.stream(products.replace(",", " ")
                        .split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        subscribeAdaptor.addSubscribeProducts(subscribeNo, subscribeProducts);
    }

}
