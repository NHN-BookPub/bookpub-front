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

    /**
     * id 중복체크 restController.
     *
     * @param id 회원가입 아이디.
     * @return id 중복여부 확인.
     */
    @PostMapping("/idCheck")
    public boolean idDuplicateCheck(@RequestParam("id") String id) {
        return memberService.idDuplicateCheck(id);
    }

    /**
     * nickname 중복체크 restController.
     *
     * @param nickname 회원가입 아이디.
     * @return nickname 중복여부 확인.
     */
    @PostMapping("/nickCheck")
    public boolean nickDuplicateCheck(@RequestParam("nickname") String nickname) {
        return memberService.nickDuplicateCheck(nickname);
    }

    /**
     * 전화번호 인증 메소드.
     *
     * @return 해당 번호로 전송된 회원가입 코드.
     */
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
