package com.nhnacademy.bookpub.bookpubfront.inquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 상품문의를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class AdminInquiryController {
    private final InquiryService inquiryService;

    @Auth
    @PostMapping("/admin/inquiryForm")
    public String inquirySubmit(@ModelAttribute CreateInquiryRequestDto request,
                                HttpServletRequest servletRequest) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        inquiryService.submitInquiry(memberNo, request);

        return "redirect:" + servletRequest.getHeader("Referer");
    }

    @Auth
    @PostMapping("/admin/inquiries/{inquiryNo}/cancel")
    public String inquiryAnswerCancel(@PathVariable("inquiryNo") Long inquiryNo,
                                      HttpServletRequest servletRequest) {
        inquiryService.cancelInquiryAnswer(inquiryNo);

        return "redirect:" + servletRequest.getHeader("Referer");
    }

    @Auth
    @PostMapping("/admin/inquiries/{inquiryNo}/complete")
    public String inquiryComplete(@PathVariable("inquiryNo") Long inquiryNo) {
        inquiryService.inquiryComplete(inquiryNo);

        return "redirect:/admin/inquiries";
    }

    @Auth
    @GetMapping("/admin/inquiries")
    public String inquiryList(@PageableDefault(size = 20) Pageable pageable,
                              Model model) {
        PageResponse<GetInquirySummaryResponseDto> inquiryMemberList =
                inquiryService.getInquiryList(pageable);

        model.addAttribute("inquiryList", inquiryMemberList.getContent());
        model.addAttribute("totalPages", inquiryMemberList.getTotalPages());
        model.addAttribute("currentPage", inquiryMemberList.getNumber());
        model.addAttribute("isNext", inquiryMemberList.isNext());
        model.addAttribute("isPrevious", inquiryMemberList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/inquiry/inquiryIndex";
    }

    @Auth
    @GetMapping("/admin/inquiries/{inquiryNo}/private")
    public String privateInquiryDetails(@PathVariable("inquiryNo") Long inquiryNo,
                                        Model model) {
        model.addAttribute("inquiry", inquiryService.getPrivateInquiry(inquiryNo));

        return "admin/inquiry/inquiryDetail";
    }

    @GetMapping("/admin/inquiries/{inquiryNo}")
    public String publicInquiryDetails(@PathVariable("inquiryNo") Long inquiryNo,
                                       Model model) {
        model.addAttribute("inquiry", inquiryService.getPublicInquiry(inquiryNo));

        return "admin/inquiry/inquiryDetail";
    }
}
