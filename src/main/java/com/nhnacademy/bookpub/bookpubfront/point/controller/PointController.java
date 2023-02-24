package com.nhnacademy.bookpub.bookpubfront.point.controller;

import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.point.dto.request.PointGiftRequestDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointResponseDto;
import com.nhnacademy.bookpub.bookpubfront.point.service.PointService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 포인트 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequestMapping
@RequiredArgsConstructor
public class PointController {
    private static final String ALL = "1";
    private final MemberUtils memberUtils;
    private final PointService pointService;
    private final MemberService memberService;

    /**
     * 로그인 한 회원의 포인트 기록을 모두 가져와서 보여주는 메소드.
     *
     * @param type     포인트 조회 타입.
     * @param pageable 페이저블.
     * @param model    모델.
     * @return 마이페이지 - 포인트 페이지.
     */
    @GetMapping("/members/point")
    public String getMemberPoint(@RequestParam(defaultValue = ALL) String type,
                                 @PageableDefault(size = 20) Pageable pageable,
                                 Model model) {
        Long memberNo = memberUtils.getMemberNo();
        MemberDetailResponseDto member = memberService.getTokenMember(memberNo);

        PageResponse<GetPointResponseDto> memberPointHistory
                = pointService.getMemberPointHistory(pageable, memberNo, type);

        model.addAttribute("member", member);
        model.addAttribute("type", type);
        Utils.settingPagination(model, memberPointHistory, "pointHistories");

        return "mypage/point";
    }

    /**
     * 포인트 선물 메소드.
     *
     * @param requestDto 선물 dto.
     * @return 멤버 포인트 화면.
     */
    @PostMapping("/members/point")
    public String giftMemberPoint(@Valid PointGiftRequestDto requestDto) {
        pointService.giftPoint(memberUtils.getMemberNo(), requestDto);
        return "redirect:/members/point";
    }

    @GetMapping("/admin/points")
    public String points(@PageableDefault Pageable pageable,
                         @RequestParam(value = "start", required = false)
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                         LocalDateTime start,
                         @RequestParam(value = "end", required = false)
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                         LocalDateTime end,
                         Model model,
                         @RequestParam(required = false) String key) {
        PageResponse<GetPointAdminResponseDto> result;

        if (key == null) {
            result = pointService.getPoints(pageable, start, end);
        } else {
            result = pointService.getPointsBySearch(pageable, start, end, key);
            model.addAttribute("key", key);
        }

        Utils.settingPagination(model, result, "content");
        model.addAttribute("uri", "/admin/points");
        return "admin/point/main";
    }
}

