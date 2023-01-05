package com.bookpub.bookpubfront.member.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 멤버 api 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
public class MemberController {
    @GetMapping("/signup")
    public String signupPage() {
        return "signupPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public void loginCheck(@RequestParam String id,
                           @RequestParam String pwd) {
        RestTemplate restTemplate = new RestTemplate();
        UserDTO user = new UserDTO(id,pwd);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());

        restTemplate.exchange(
                "http://localhost:7070/auth/token",
                HttpMethod.POST,
                httpEntity,
                String.class
                );
    }


    @AllArgsConstructor
    @Getter
    @ToString
    private static class UserDTO {
        private String id;
        private String pwd;
    }
}
