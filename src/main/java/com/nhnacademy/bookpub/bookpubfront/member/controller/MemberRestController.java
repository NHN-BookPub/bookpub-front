package com.nhnacademy.bookpub.bookpubfront.member.controller;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.config.DoorayConfig;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 프론트와 view가 통신을 하기 위해 필요한 restController.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;
    private final DoorayConfig doorayConfig;
    private final PasswordEncoder passwordEncoder;
    private final MemberUtils memberUtils;
    private final Random random = new Random();

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
        String authNumber = Integer.toString(random.nextInt(89998) + 10001);
        doorayConfig.doorayHookSender().send(
                DoorayHook.builder()
                        .botName("bookpub")
                        .text(authNumber)
                        .build()
        );

        return authNumber;
    }

    /**
     * 패스워드 값을 확인하기위한 메서드입니다.
     *
     * @param rawPassword 땡값 패스워드
     * @return the boolean
     */
    @PostMapping("/members/password-check")
    @Auth
    public boolean passwordCheck(@RequestParam("rawPassword") String rawPassword) {
        Long memberNo = memberUtils.getMemberNo();
        MemberPasswordResponseDto memberPassword = memberService.getMemberPassword(memberNo);

        return passwordEncoder.matches(rawPassword, memberPassword.getPassword());
    }

    /**
     * 멤버의 등급을 조회하는 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @return 등급 번호
     */

    @Auth
    @GetMapping("/members/member")
    public Integer getTierNoByMemberNo(@RequestParam Long memberNo) {
        return memberService.getTierByMemberNo(memberNo);
    }
}
