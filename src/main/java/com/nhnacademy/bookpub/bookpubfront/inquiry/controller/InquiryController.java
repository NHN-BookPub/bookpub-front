package com.nhnacademy.bookpub.bookpubfront.inquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service.InquiryStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 마이페이지 및 상품 상세 상품문의를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final InquiryStateCodeService inquiryStateCodeService;
    private final MemberUtils memberUtils;


    /**
     * 회원이 상품문의 작성 요청 시 상품 구매 이력이 있는지 검증하기 위한 메서드입니다.
     * 검증이 완료되면 상품문의 작성 폼으로 이동하고, 실패하면 주문목록으로 redirect 됩니다.
     *
     * @param productNo 상품번호
     * @param model     the model
     * @return 성공 시, 상품문의 작성 폼 페이지. 실패 시, 주문목록 페이지로 이동
     */
    @Auth
    @GetMapping("/members/inquiryForm/products/{productNo}")
    public String inquiryForm(@PathVariable("productNo") Long productNo, Model model) {
        boolean verify;
        Long memberNo = memberUtils.getMemberNo();
        verify = inquiryService.verifyPurchaseProduct(memberNo, productNo);

        memberUtils.modelRequestMemberNo(model);
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
     * @param request    상품문의 등록을 위해 필요한 정보를 담은 dto
     * @param imagePaths 상품문의에 같이 등록될 이미지 경로
     * @return 마이페이지의 상품문의 페이지
     */
    @Auth
    @PostMapping("/members/inquiries")
    public String inquirySubmit(@ModelAttribute CreateInquiryRequestDto request,
                                @RequestParam(value = "imagePathList", required = false)
                                String imagePaths) {
        Long memberNo = memberUtils.getMemberNo();
        inquiryService.submitInquiry(memberNo, request, imagePaths);

        return "redirect:/members/inquiries";
    }

    /**
     * 해당 회원의 마이페이지 상품문의 내역 페이지로 이동하는 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @param model    the model
     * @return 마이페이지 상품문의 페이지
     */
    @Auth
    @GetMapping("/members/inquiries")
    public String inquiryMemberList(@PageableDefault Pageable pageable,
                                    Model model) {
        Long memberNo = memberUtils.getMemberNo();
        PageResponse<GetInquirySummaryMemberResponseDto> inquiryMemberList =
                inquiryService.getInquiryMemberList(pageable, memberNo);

        memberUtils.modelRequestMemberNo(model);
        model.addAttribute("inquiryMemberList", inquiryMemberList.getContent());
        model.addAttribute("totalPages", inquiryMemberList.getTotalPages());
        model.addAttribute("currentPage", inquiryMemberList.getNumber());
        model.addAttribute("isNext", inquiryMemberList.isNext());
        model.addAttribute("isPrevious", inquiryMemberList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageInquiryList";
    }

    /**
     * 상품문의를 삭제하기 위한 메서드입니다.
     *
     * @param inquiryNo 삭제할 상품문의 번호
     * @return 마이페이지의 상품문의 페이지
     */
    @Auth
    @PostMapping("/members/inquiries/{inquiryNo}/cancel")
    public String inquiryCancel(@PathVariable Long inquiryNo) {
        inquiryService.cancelInquiry(inquiryNo);

        return "redirect:/members/inquiries";
    }

    /**
     * 마이페이지의 상품문의 상세조회를 위한 메서드입니다.
     * 비공개 문의글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param model     the model
     * @return 마이페이지 상품문의 상세조회 뷰
     */
    @Auth
    @GetMapping("/members/inquiries/{inquiryNo}/private")
    public String myPagePrivateInquiryDetails(@PathVariable("inquiryNo") Long inquiryNo,
                                              Model model) {
        memberUtils.modelRequestMemberNo(model);
        model.addAttribute("inquiry", inquiryService.getPrivateInquiry(inquiryNo));

        return "mypage/myPageInquiryDetail";
    }

    /**
     * 마이페이지의 상품문의 상세조회를 위한 메서드입니다.
     * 공개 문의글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param model     the model
     * @return 마이페이지 상품문의 상세조회 뷰
     */
    @GetMapping("/members/inquiries/{inquiryNo}")
    public String myPagePublicInquiryDetails(@PathVariable("inquiryNo") Long inquiryNo,
                                             Model model) {
        memberUtils.modelRequestMemberNo(model);
        model.addAttribute("inquiry", inquiryService.getPublicInquiry(inquiryNo));

        return "mypage/myPageInquiryDetail";
    }
}
