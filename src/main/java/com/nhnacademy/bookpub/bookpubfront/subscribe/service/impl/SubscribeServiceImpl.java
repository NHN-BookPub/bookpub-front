package com.nhnacademy.bookpub.bookpubfront.subscribe.service.impl;

import com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor.SubscribeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
