package com.nhnacademy.bookpub.bookpubfront.oauth.controller;

import com.nhnacademy.bookpub.bookpubfront.oauth.factory.OauthFactory;
import com.nhnacademy.bookpub.bookpubfront.oauth.service.OauthService;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * oauth rest 컨트롤러 입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class OauthRestController {
    private final OauthFactory oauthFactory;

    /**
     * oauth에 맞는 url을 제작하는 메소드..
     *
     * @param oauthName 사용한 도메인.
     * @param response  응답.
     * @return 적합한 url.
     */
    @GetMapping("/redirect-url")
    public String getRedirectUrl(String oauthName, HttpServletResponse response) {
        OauthService oauthService = oauthFactory.getMatchedService(oauthName);
        CookieUtil.makeCookie(response, Utils.DOMAIN, oauthName);
        return oauthService.makeAuthUrl();
    }
}
