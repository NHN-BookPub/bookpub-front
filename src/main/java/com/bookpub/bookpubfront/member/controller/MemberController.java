package com.bookpub.bookpubfront.member.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * 멤버 api 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
public class MemberController {

    @GetMapping("/signup")
    public String signupPageForm() {
        return "member/signupPage";
    }

    @PostMapping("/signup")
    public String signupComplete() {

        return "member/signupComplete";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@RequestParam String id,
                           @RequestParam String pwd,
                           Model model) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://localhost:7070/auth/token",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        model.addAttribute("token", exchange.getBody());

        return "tokenPage";
    }
}
