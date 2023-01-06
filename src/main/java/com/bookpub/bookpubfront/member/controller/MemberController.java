package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.member.dto.MemberSignupRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

/**
 * 멤버 api 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final RestTemplate restTemplate;

    @Value("${book-pub.gateway}")
    private String gatewayUrl;

    @GetMapping("/signup")
    public String signupPageForm() {
        return "member/signupPage";
    }

    @PostMapping("/signup")
    public String signupComplete(@Valid MemberSignupRequest memberSignupRequest,
                                 Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MemberSignupRequest> entity = new HttpEntity<>(memberSignupRequest, headers);

        ResponseEntity<String> exchange = restTemplate.exchange(
                gatewayUrl + "/shopping/signup",
                HttpMethod.POST,
                entity,
                String.class
        );

        String body = exchange.getBody();

        model.addAttribute("body", body);

        return "member/signupComplete";
    }

}
