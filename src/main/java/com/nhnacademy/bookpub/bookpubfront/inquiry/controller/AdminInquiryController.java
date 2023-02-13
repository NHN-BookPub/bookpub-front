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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 상품문의 관리자 페이지를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class AdminInquiryController {
    private final InquiryService inquiryService;

    /**
     * 상품문의 답변 등록을 위한 메서드입니다.
     * 답변 등록 후, 상품문의 상세페이지로 redirect 됩니다.
     *
     * @param request        상품문의 답변 등록에 필요한 정보를 담은 dto
     * @param imagePathList  상품문의 답변에 저장된 이미지 경로 리스트
     * @param servletRequest 요청
     * @return 관리자 상품문의 상세페이지
     */
    @Auth
    @PostMapping("/admin/inquiryForm")
    public String inquirySubmit(@ModelAttribute CreateInquiryRequestDto request,
                                @RequestParam(value = "imagePathList", required = false)
                                String imagePathList,
                                HttpServletRequest servletRequest) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        inquiryService.submitInquiry(memberNo, request, imagePathList);

        return "redirect:" + servletRequest.getHeader("Referer");
    }

    /**
     * 상품문의 답변을 삭제하기 위한 메서드입니다.
     * 답변 삭제 후, 상품문의 상세페이지로 redirect 됩니다.
     *
     * @param inquiryNo      상품문의 번호
     * @param servletRequest request 요청
     * @return 관리자 상품문의 상세페이지
     */
    @Auth
    @PostMapping("/admin/inquiries/{inquiryNo}/cancel")
    public String inquiryAnswerCancel(@PathVariable("inquiryNo") Long inquiryNo,
                                      HttpServletRequest servletRequest) {
        inquiryService.cancelInquiryAnswer(inquiryNo);

        return "redirect:" + servletRequest.getHeader("Referer");
    }

    /**
     * 상품문의 답변 완료 처리를 위한 메서드입니다.
     *
     * @param inquiryNo 답변 완료 여부 수정할 상품문의 번호
     * @return 상품문의 관리자 페이지
     */
    @Auth
    @PostMapping("/admin/inquiries/{inquiryNo}/complete")
    public String inquiryComplete(@PathVariable("inquiryNo") Long inquiryNo) {
        inquiryService.inquiryComplete(inquiryNo);

        return "redirect:/admin/inquiries";
    }

    /**
     * 모든 상품문의 조회(불량상품 조회 제외)를 위한 메서드입니다.
     *
     * @param pageable       페이징 정보
     * @param searchKeyFir   검색 조건
     * @param searchValueFir 검색 값
     * @param searchKeySec   검색 조건 두번째
     * @param searchValueSec 검색 값 두번째
     * @param model          the model
     * @return 상품문의 관리자 페이지
     */
    @Auth
    @GetMapping("/admin/inquiries")
    public String inquiryList(@PageableDefault(size = 20) Pageable pageable,
                              @RequestParam(value = "searchKeyFir", required = false)
                              String searchKeyFir,
                              @RequestParam(value = "searchValueFir", required = false)
                              String searchValueFir,
                              @RequestParam(value = "searchKeySec", required = false)
                              String searchKeySec,
                              @RequestParam(value = "searchValueSec", required = false)
                              String searchValueSec,
                              Model model) {
        PageResponse<GetInquirySummaryResponseDto> inquiryMemberList =
                inquiryService.getInquiryList(pageable,
                        searchKeyFir, searchValueFir, searchKeySec, searchValueSec);

        model.addAttribute("inquiryList", inquiryMemberList.getContent());
        model.addAttribute("totalPages", inquiryMemberList.getTotalPages());
        model.addAttribute("currentPage", inquiryMemberList.getNumber());
        model.addAttribute("isNext", inquiryMemberList.isNext());
        model.addAttribute("isPrevious", inquiryMemberList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/inquiry/inquiryIndex";
    }

    /**
     * 모든 불량상품 문의 조회를 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @param model    the model
     * @return 불량상품 관리자 페이지
     */
    @Auth
    @GetMapping("/admin/error-inquiries")
    public String errorInquiryList(@PageableDefault(size = 20) Pageable pageable,
                                   Model model) {
        PageResponse<GetInquirySummaryResponseDto> errorInquiryList =
                inquiryService.getErrorInquiryList(pageable);

        model.addAttribute("errorInquiryList", errorInquiryList.getContent());
        model.addAttribute("totalPages", errorInquiryList.getTotalPages());
        model.addAttribute("currentPage", errorInquiryList.getNumber());
        model.addAttribute("isNext", errorInquiryList.isNext());
        model.addAttribute("isPrevious", errorInquiryList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/inquiry/errorInquiryIndex";
    }

    /**
     * 상품문의 단건 조회를 위한 메서드입니다.
     * 상품문의가 비밀글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param model     the model
     * @return 관리자 상품문의 단건 조회 페이지
     */
    @Auth
    @GetMapping("/admin/inquiries/{inquiryNo}/private")
    public String privateInquiryDetails(@PathVariable("inquiryNo") Long inquiryNo,
                                        Model model) {
        model.addAttribute("inquiry", inquiryService.getPrivateInquiry(inquiryNo));

        return "admin/inquiry/inquiryDetail";
    }

    /**
     * 상품문의 단건 조회를 위한 메서드입니다.
     * 상품문의가 공개글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param model     the model
     * @return 관리자 상품문의 단건 조회 페이지
     */
    @GetMapping("/admin/inquiries/{inquiryNo}")
    public String publicInquiryDetails(@PathVariable("inquiryNo") Long inquiryNo,
                                       Model model) {
        model.addAttribute("inquiry", inquiryService.getPublicInquiry(inquiryNo));

        return "admin/inquiry/inquiryDetail";
    }
}
