package com.nhnacademy.bookpub.bookpubfront.inquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 상품문의를 다루기 위한 Rest controller 입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class InquiryRestController {
    private final InquiryService inquiryService;

    /**
     * 상품문의의 이미지 저장을 위한 메서드입니다.
     * tui editor 적용 시 사용되는 메서드입니다.
     *
     * @param image 저장할 이미지
     * @return 저장된 이미지 경로
     */
    @Auth
    @PostMapping("/inquiries/image/save")
    public String saveInquiryImage(@RequestPart("image") MultipartFile image) {
        return inquiryService.addInquiryImage(image);
    }

    /**
     * 해당 회원이 해당 상품문의글을 조회할 수 있는 권한이 있는지 확인하기 위한 메서드입니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param memberNo  회원 번호
     * @return the boolean
     */
    @Auth
    @GetMapping("/inquiries/{inquiryNo}/verify/{memberNo}")
    public boolean verifyCanView(@PathVariable("inquiryNo") Long inquiryNo,
                                 @PathVariable("memberNo") Long memberNo) {
        return inquiryService.verifyCanView(inquiryNo, memberNo);
    }

    /**
     * 상품문의 상세 조회를 위한 메서드입니다.
     * 해당 상품문의가 비밀글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @return 상품문의 상세 정보를 담은 dto
     */
    @Auth
    @GetMapping("/members/inquiries/{inquiryNo}/private/modal")
    public GetInquiryResponseDto privateInquiryDetails(
            @PathVariable("inquiryNo") Long inquiryNo) {
        return inquiryService.getPrivateInquiry(inquiryNo);
    }

    /**
     * 상품문의 상세 조회를 위한 메서드입니다.
     * 해당 상품문의가 공개글 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @return 상품문의 상세 정보를 담은 dto
     */
    @GetMapping("/members/inquiries/{inquiryNo}/modal")
    public GetInquiryResponseDto publicInquiryDetails(
            @PathVariable("inquiryNo") Long inquiryNo) {
        return inquiryService.getPublicInquiry(inquiryNo);
    }
}
