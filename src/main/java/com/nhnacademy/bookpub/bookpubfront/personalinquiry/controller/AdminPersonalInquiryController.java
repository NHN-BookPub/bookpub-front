package com.nhnacademy.bookpub.bookpubfront.personalinquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetSimplePersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.service.PersonalInquiryService;
import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request.CreatePersonalInquiryAnswerRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.service.PersonalInquiryAnswerService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 관리자의 1대1문의 뷰를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class AdminPersonalInquiryController {
    private final PersonalInquiryService personalInquiryService;
    private final PersonalInquiryAnswerService personalInquiryAnswerService;

    /**
     * 1대1문의 간단한 정보 리스트를 보여주기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @param model    the model
     * @return 1대1문의 리스트를 보여주는 뷰
     */
    @Auth
    @GetMapping("/admin/personal-inquiries")
    public String personalInquiryList(@PageableDefault Pageable pageable,
                                      Model model) {
        PageResponse<GetSimplePersonalInquiryResponseDto> inquiryPages =
                personalInquiryService.getPersonalInquiries(pageable);

        model.addAttribute("personalInquiryList", inquiryPages.getContent());
        model.addAttribute("totalPages", inquiryPages.getTotalPages());
        model.addAttribute("currentPage", inquiryPages.getNumber());
        model.addAttribute("isNext", inquiryPages.isNext());
        model.addAttribute("isPrevious", inquiryPages.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/personalinquiry/personalInquiryIndex";
    }

    /**
     * 1대1문의 상세 정보 조회를 위한 메서드입니다.
     *
     * @param inquiryNo 조회할 1대1문의 번호
     * @param model     the model
     * @return 1대1문의의 질문 및 답변 내용이 담긴 뷰
     */
    @Auth
    @GetMapping("/admin/personal-inquiries/{inquiryNo}")
    public String personalInquiryDetail(@PathVariable("inquiryNo") Long inquiryNo,
                                        Model model) {
        model.addAttribute("personalInquiry", personalInquiryService.getPersonalInquiry(inquiryNo));

        return "admin/personalinquiry/personalInquiryDetail";
    }

    /**
     * 1대1문의 답변을 위한 메서드입니다.
     *
     * @param createDto      1대1문의답변 생성에 필요한 정보가 담긴 dto
     * @param servletRequest the servlet request
     * @return 1대1문의의 질문 및 답변 내용이 담긴 뷰로 redirect
     */
    @Auth
    @PostMapping("/admin/personal-inquiry-answers")
    public String personalInquiryAnswer(CreatePersonalInquiryAnswerRequestDto createDto,
                                        HttpServletRequest servletRequest) {
        personalInquiryAnswerService.createPersonalInquiryAnswer(createDto);

        return "redirect:" + servletRequest.getHeader("Referer");
    }

    /**
     * 1대1문의 답변 삭제를 위한 메서드입니다.
     *
     * @param answerNo       삭제할 1대1문의답변
     * @param servletRequest the servlet request
     * @return 1대1문의의 질문 및 답변 내용이 담긴 뷰로 redirect
     */
    @Auth
    @PostMapping("/admin/personal-inquiry-answers/{answerNo}/cancel")
    public String personalInquiryAnswerCancel(@PathVariable("answerNo") Long answerNo,
                                              HttpServletRequest servletRequest) {
        personalInquiryAnswerService.deletePersonalInquiryAnswer(answerNo);

        return "redirect:" + servletRequest.getHeader("Referer");
    }
}
