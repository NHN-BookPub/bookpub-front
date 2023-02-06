package com.nhnacademy.bookpub.bookpubfront.subscribe.controller;

import com.nhnacademy.bookpub.bookpubfront.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 구독관련 컨트롤러 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService service;

    @PostMapping("/admin/subscribes")
    public String subscribeAdd(){
        return "";
    }
}
