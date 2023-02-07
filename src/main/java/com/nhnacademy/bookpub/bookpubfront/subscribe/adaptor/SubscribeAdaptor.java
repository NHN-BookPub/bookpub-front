package com.nhnacademy.bookpub.bookpubfront.subscribe.adaptor;

import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 정보를 Shop 서버와 통신하기위한 Adaptor 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
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
    void modifySubscribeRequest(Long subscribeNo, ModifySubscribeRequestDto dto);

    /**
     * 구독정보들을 받기위한 shop 서버로의 GET 요청입니다.
     *
     * @param pageable 페이징정보
     * @return 구독\정보 반환
     */
    PageResponse<GetSubscribeResponseDto> getSubscribesRequest(Pageable pageable);
}
