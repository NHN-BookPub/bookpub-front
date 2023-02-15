package com.nhnacademy.bookpub.bookpubfront.personalinquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.request.CreatePersonalInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetSimplePersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.service.PersonalInquiryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 마이페이지의 1대1문의 뷰를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class PersonalInquiryController {
    private final PersonalInquiryService personalInquiryService;
    private final MemberUtils memberUtils;

    /**
     * 마이페이지의 1대1문의 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징정보
     * @param model    the model
     * @return 마이페이지의 1대1문의 리스트를 조회하는 뷰
     */
    @Auth
    @GetMapping("/members/personal-inquiries")
    public String myPersonalInquiryList(@PageableDefault Pageable pageable,
                                        Model model) {
        PageResponse<GetSimplePersonalInquiryResponseDto> inquiryPages =
                personalInquiryService.getMemberPersonalInquiries(pageable);

        memberUtils.modelRequestMemberNo(model);
        model.addAttribute("personalInquiryList", inquiryPages.getContent());
        model.addAttribute("totalPages", inquiryPages.getTotalPages());
        model.addAttribute("currentPage", inquiryPages.getNumber());
        model.addAttribute("isNext", inquiryPages.isNext());
        model.addAttribute("isPrevious", inquiryPages.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPagePersonalInquiryIndex";
    }

    /**
     * 마이페이지의 1대1문의 단건 상세 조회를 위한 메서드입니다.
     *
     * @param inquiryNo 조회할 1대1문의 번호
     * @param model     the model
     * @return 마이페이지의 1대1문의 상세 조회 뷰
     */
    @Auth
    @GetMapping("/members/personal-inquiries/{inquiryNo}")
    public String myPersonalInquiryDetail(@PathVariable("inquiryNo") Long inquiryNo,
                                          Model model) {
        memberUtils.modelRequestMemberNo(model);
        model.addAttribute("personalInquiry", personalInquiryService.getPersonalInquiry(inquiryNo));

        return "mypage/myPagePersonalInquiryDetail";
    }

    /**
     * 마이페이지의 1대1문의 작성 폼으로 이동하기 위한 메서드입니다.
     *
     * @param model the model
     * @return 마이페이지의 1대1문의 작성 폼 뷰
     */
    @Auth
    @GetMapping("/members/personal-inquiries/form")
    public String myPersonalInquiryForm(Model model) {
        memberUtils.modelRequestMemberNo(model);

        return "mypage/myPagePersonalInquiryForm";
    }

    /**
     * 1대1문의 작성을 위한 메서드입니다.
     *
     * @param createDto 1대1문의 작성에 필요한 정보를 담은 dto
     * @return 마이페이지의 1대1문의 리스트를 조회하는 뷰로 redirect
     */
    @Auth
    @PostMapping("/members/personal-inquiries")
    public String personalInquiryAdd(CreatePersonalInquiryRequestDto createDto) {
        personalInquiryService.createPersonalInquiry(createDto);

        return "redirect:/members/personal-inquiries";
    }

    /**
     * 1대1문의 삭제를 위한 메서드입니다.
     *
     * @param inquiryNo 삭제할 1대1문의 번호
     * @return 마이페이지의 1대1문의 리스트를 조회하는 뷰로 redirect
     */
    @Auth
    @PostMapping("/members/personal-inquiries/{inquiryNo}/cancel")
    public String personalInquiryDelete(@PathVariable("inquiryNo") Long inquiryNo) {
        personalInquiryService.deletePersonalInquiry(inquiryNo);

        return "redirect:/members/personal-inquiries";
    }
}
