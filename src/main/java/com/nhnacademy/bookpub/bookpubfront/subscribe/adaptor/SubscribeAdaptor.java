package com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor;

import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 정보를 Shop 서버와 통신하기위한 Adaptor 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 */
public interface SubscribeAdaptor {

    /**
     * 구독을 추가하기위한 shop 서버로 요청입니다.
     *
     * @param dto the dto
     */
    void addSubscribeRequest(CreateSubscribeRequestDto dto, MultipartFile image);

    /**
     * 구독정보를 수정하는 shop 서버로의 요청입니다.
     *
     * @param subscribeNo 구독번호
     * @param dto         수정할 정보
     */
    void modifySubscribeRequest(Long subscribeNo, MultipartFile image, ModifySubscribeRequestDto dto);

    /**
     * 구독정보들을 받기위한 shop 서버로의 GET 요청입니다.
     *
     * @param pageable 페이징정보
     * @return 구독\정보 반환
     */
    PageResponse<GetSubscribeResponseDto> getSubscribesRequest(Pageable pageable);

    /**
     * 구독상품에 연관상품까지 조회하는 메서드입니다.
     *
     * @param subscribeNo 구독번호.
     * @return 구독상세정보 반환.
     */
    GetSubscribeDetailResponseDto getSubscribeDetailRequest(Long subscribeNo);

    /**
     * 삭제요청을 보냅니다.
     *
     * @param subscribeNo 구독번호.
     * @param deleted     삭제여부.
     */
    void deletedSubscribeRequest(Long subscribeNo, boolean deleted);

    /**
     * 구독갱신여부 요청.
     *
     * @param subscribeNo 구독번호 기입.
     * @param renewed     갱신여부 기입.
     */
    void renewedSubscribeRequest(Long subscribeNo, boolean renewed);

    /**
     * 구독에 상품정보 추가하기.
     *
     * @param subscribeNo  구독번호.
     * @param products    상품번호들.
     */
    void addSubscribeProducts(Long subscribeNo, List<Long> products);
}
