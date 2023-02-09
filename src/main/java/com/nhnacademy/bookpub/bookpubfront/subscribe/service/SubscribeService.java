package com.nhnacademy.bookpub.bookpubfront.subscribe.service;

import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 서비스 인터페이스 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
public interface SubscribeService {
    /**
     * 구독을 추가하는 메서드입니다.
     *
     * @param dto 생성할 정보가 기입됩니다.
     */
    void addSubscribe(CreateSubscribeRequestDto dto, MultipartFile image);

    /**
     * 구독을 수정하기위한 메서드입니다.
     *
     * @param scribeNo 구독번호
     * @param dto      수정할 구독 정보
     */
    void modifySubscribe(Long scribeNo, MultipartFile image, ModifySubscribeRequestDto dto);

    /**
     * 구독들의 정보들이 반환됩니다.
     *
     * @param pageable 페이징 정보
     * @return 페이징된 정보가 반환됩니다.
     */
    PageResponse<GetSubscribeResponseDto> getSubscribeList(Pageable pageable);

    /**
     * 구독상품의 상세정보가 반환됩니다.
     *
     * @param subscribeNo 구독번호.
     * @return 구독상세정보가 반환됩니다.
     */
    GetSubscribeDetailResponseDto getSubscribeDetail(Long subscribeNo);

    /**
     * 구독 정보 삭제상태로 변경
     *
     * @param subscribeNo the subscribe no
     * @param deleted     the deleted
     */
    void deleteSubscribe(Long subscribeNo, boolean deleted);


    /**
     * 갱신 여부 변경
     *
     * @param subscribeNo 구독번호 기입
     * @param renewed     갱신여부
     */
    void renewedSubscribe(Long subscribeNo, boolean renewed);

    /**
     * 구독에 구독상품을 추가하기위한 메서드입니다.
     *
     * @param subscribeNo 구독번호
     * @param products    상품번호들.
     */
    void addSubscribeProductList(Long subscribeNo, String products);
}
