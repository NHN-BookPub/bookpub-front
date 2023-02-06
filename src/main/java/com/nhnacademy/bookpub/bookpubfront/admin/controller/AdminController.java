package com.nhnacademy.bookpub.bookpubfront.admin.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin을 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    @Auth
    @GetMapping
    public String adminIndex(Model model) {
        MemberStatisticsResponseDto memberStatisticsResponseDto = memberService.memberStatistics();
        List<MemberTierStatisticsResponseDto> tierStatistics = memberService.memberTierStatistics();
        log.warn("statistics : {}",tierStatistics.get(0).getTierCnt());
        log.error("statistics : {}",tierStatistics.get(0).getTierName());
        model.addAttribute("memberStatistics", memberStatisticsResponseDto);
        model.addAttribute("tierStatistics", tierStatistics);

        return "admin/index";
    }
}
