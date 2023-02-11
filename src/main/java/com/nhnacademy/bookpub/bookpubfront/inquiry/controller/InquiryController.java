package com.nhnacademy.bookpub.bookpubfront.inquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service.InquiryStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 마이페이지의 상품문의를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final InquiryStateCodeService inquiryStateCodeService;
    private final MemberService memberService;

    /**
     * 회원이 상품문의 작성 요청 시 상품 구매 이력이 있는지 검증하기 위한 메서드입니다.
     * 검증이 완료되면 상품문의 작성 폼으로 이동하고, 실패하면 주문목록으로 redirect 됩니다.
     *
     * @param memberNo  the member no
     * @param productNo the product no
     * @param model     the model
     * @return the string
     */
    @Auth
    @GetMapping("/members/{memberNo}/inquiryForm/products/{productNo}")
    public String inquiryForm(@PathVariable("memberNo") Long memberNo,
                              @PathVariable("productNo") Long productNo, Model model) {
        boolean verify = inquiryService.verifyPurchaseProduct(memberNo, productNo);

        model.addAttribute("member", memberService.getTokenMember(memberNo));
        model.addAttribute("inquiryStateCodeList",
                inquiryStateCodeService.getInquiryStateCodeListForMember());
        model.addAttribute("productNo", productNo);

        if (verify) {
            return "mypage/myPageInquiryForm";
        }
        return "redirect:/orders/list";
    }

    /**
     * 상품문의 작성 폼을 제출했을 시 실행되는 메서드입니다.
     * 해당 회원의 마이페이지 상품문의 내역 페이지로 redirect 됩니다.
     *
     * @param memberNo the member no
     * @param request  the request
     * @return the string
     */
    @Auth
    @PostMapping("/members/{memberNo}/inquiryForm")
    public String inquirySubmit(@PathVariable("memberNo") Long memberNo,
                                @ModelAttribute CreateInquiryRequestDto request) {
        inquiryService.submitInquiry(memberNo, request);

        return "redirect:/members/" + memberNo + "/inquiries";
    }

    /**
     * 해당 회원의 마이페이지 상품문의 내역 페이지로 이동하는 메서드입니다.
     *
     * @param memberNo the member no
     * @param model    the model
     * @return the string
     */
    @Auth
    @GetMapping("/members/{memberNo}/inquiries")
    public String inquiryMemberList(@PageableDefault Pageable pageable,
                                    @PathVariable("memberNo") Long memberNo,
                                    Model model) {
        PageResponse<GetInquirySummaryMemberResponseDto> inquiryMemberList =
                inquiryService.getInquiryMemberList(pageable, memberNo);

        model.addAttribute("member", memberService.getTokenMember(memberNo));
        model.addAttribute("inquiryMemberList", inquiryMemberList.getContent());
        model.addAttribute("totalPages", inquiryMemberList.getTotalPages());
        model.addAttribute("currentPage", inquiryMemberList.getNumber());
        model.addAttribute("isNext", inquiryMemberList.isNext());
        model.addAttribute("isPrevious", inquiryMemberList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageInquiryList";
    }

    @Auth
    @GetMapping("/members/{memberNo}/inquiries/{inquiryNo}/private")
    public String myPagePrivateInquiryDetails(@PathVariable("memberNo") Long memberNo,
                                              @PathVariable("inquiryNo") Long inquiryNo,
                                              Model model) {
        model.addAttribute("member", memberService.getTokenMember(memberNo));
        model.addAttribute("inquiry", inquiryService.getPrivateInquiry(inquiryNo));

        return "mypage/myPageInquiryDetail";
    }

    @GetMapping("/members/{memberNo}/inquiries/{inquiryNo}")
    public String myPagePublicInquiryDetails(@PathVariable("memberNo") Long memberNo,
                                             @PathVariable("inquiryNo") Long inquiryNo,
                                             Model model) {
        model.addAttribute("member", memberService.getApiMember(memberNo));
        model.addAttribute("inquiry", inquiryService.getPublicInquiry(inquiryNo));


        return "mypage/myPageInquiryDetail";
    }
}
