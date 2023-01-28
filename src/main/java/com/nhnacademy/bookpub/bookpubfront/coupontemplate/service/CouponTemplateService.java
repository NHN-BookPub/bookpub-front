package com.nhnacademy.bookpub.bookpubfront.coupontemplate.service;

import com.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.io.IOException;
import org.springframework.data.domain.Pageable;

/**
 * 쿠폰템플릿을 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 */
public interface CouponTemplateService {
    /**
     * 쿠폰템플릿 페이지를 조회하기 위한 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @return 쿠폰템플릿 정보 Dto를 담은 페이지 정보
     */
    PageResponse<GetCouponTemplateResponseDto> getCouponTemplates(Pageable pageable);

    /**
     * 쿠폰템플릿 상세 정보를 조회하기 위한 메소드입니다.
     *
     * @param templateNo 조회할 템플릿 번호
     * @return 쿠폰템플릿 상세 정보를 담은 Dto
     */
    GetDetailCouponTemplateResponseDto getDetailCouponTemplate(Long templateNo);

    /**
     * 쿠폰템플릿을 등록하기 위한 메소드입니다.
     *
     * @param createRequestDto 등록할 템플릿 정보를 담은 Dto.
     * @throws IOException 파일 등록시 IOException
     */
    void createCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) throws IOException;

    /**
     * 쿠폰템플릿을 수정하기 위한 메소드입니다.
     *
     * @param templateNo       수정할 템플릿 번호
     * @param modifyRequestDto 수정할 템플릿 정보를 담은 Dto.
     */
    void modifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto);

    /**
     * 해당 쿠폰 템플릿이 존재하는지 확인하기 위한 메서드입니다.
     *
     * @param templateNo 쿠폰템플릿 번호
     * @return the boolean
     */
    boolean existTemplateCheck(Long templateNo);

    /**
     * 오브젝트 스토리지 접근을 위한 정보를 반환하기 위한 메서드입니다.
     *
     * @param templateNo 템플릿 번호
     * @return 스토리지 접근을 위한 정보를 담은 Dto
     */
    GetDownloadInfo downloadInfo(Long templateNo);

    /**
     * 파일을 다운로드 받을 때 사용하는 메서드입니다.
     *
     * @param path  파일 경로
     * @param token 오브젝트 스토리지 접근을 위한 토큰
     * @return 파일
     */
    byte[] downloadFile(String path, String token);
}
