package com.nhnacademy.bookpub.bookpubfront.oauth.controller;

import com.nhnacademy.bookpub.bookpubfront.oauth.dto.request.OauthMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.oauth.factory.OauthFactory;
import com.nhnacademy.bookpub.bookpubfront.oauth.service.OauthService;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * oatuh controller입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class OauthController {
    private final OauthFactory oauthFactory;
    private static final String AUTH_MEMBER = "oauthMember";


    /**
     * oauth 도메인의 redirect 위치. oauth 사용자 정보를 가져오는 로직이 수행됩니다.
     *
     * @param code 로그인 성공 코드.
     * @return 결과에 따른 signUp page, login Processing Page.
     */
    @GetMapping("/code")
    public String authLogin(@RequestParam String code,
                            HttpServletRequest request, HttpServletResponse response,
                            Model model) {
        Cookie cookie = CookieUtil.findCookie(Utils.DOMAIN);
        if (Objects.isNull(cookie)) {
            return "redirect:/";
        }

        OauthService oauthService = oauthFactory.getMatchedService(cookie.getValue());
        CookieUtil.deleteCookie(response, Utils.DOMAIN);

        String token =
                oauthService.getToken(oauthService.tokenRequestUrl(code));
        Map<String, Object> userInfo =
                oauthService.getUserInfo(token, oauthService.userInfoRequestUrl());

        OauthMemberRequestDto oauthMemberRequestDto = oauthService.convertDto(userInfo);
        boolean oauthMember = oauthService.isOauthMember(oauthMemberRequestDto.getId());

        if (!oauthMember) {
            request.setAttribute(AUTH_MEMBER, oauthMemberRequestDto);
            return "forward:/oauth/signup";
        }

        model.addAttribute(AUTH_MEMBER, oauthMemberRequestDto);
        return "member/popup";
    }
}
