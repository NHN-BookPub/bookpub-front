package com.nhnacademy.bookpub.bookpubfront.test;

import static com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil.findCookie;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * 게이트웨이와의 테스트를 위해 만들어둔 controller.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class TestController {
    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;

    @GetMapping("/test")
    public String requestMemberPassword(
            HttpServletRequest request,
            Model model) {
        String cookieValue = findCookie(JwtUtil.JWT_COOKIE).getValue();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put(JwtUtil.AUTH_HEADER, List.of(JwtUtil.TOKEN_TYPE + cookieValue));
        HttpHeaders httpHeaders = new HttpHeaders(map);
        new HttpHeaders();
        String body = restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/test",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class).getBody();

        model.addAttribute("str", body);
        return "test";
    }
}
