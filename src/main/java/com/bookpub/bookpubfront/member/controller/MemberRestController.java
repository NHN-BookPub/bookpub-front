package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.config.DoorayConfig;
import com.bookpub.bookpubfront.member.service.MemberService;
import com.nhn.dooray.client.DoorayHook;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Some description here
 *
 * @author : 임태원
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;
    private final DoorayConfig doorayConfig;

    @PostMapping("/idCheck")
    public boolean idDuplicateCheck(@RequestParam("id") String id) {
        return memberService.idDuplicateCheck(id);
    }

    @PostMapping("/nickCheck")
    public boolean nickDuplicateCheck(@RequestParam("nickname") String nickname) {
        return memberService.nickDuplicateCheck(nickname);
    }

    @PostMapping("/emailCheck")
    public boolean emailDuplicateCheck(@RequestParam("email") String email) {
        return memberService.emailDuplicateCheck(email);
    }

    @PostMapping("/smsSend")
    public String doorayMessage() {
        String authenticateMessage = UUID.randomUUID().toString();
        doorayConfig.doorayHookSender().send(
                DoorayHook.builder()
                        .botName("bookpub")
                        .text(authenticateMessage)
                        .build()
        );

        return authenticateMessage;
    }
}
