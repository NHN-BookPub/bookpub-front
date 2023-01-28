package com.nhnacademy.bookpub.bookpubfront.order.relationship.adaptor;

import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.CreateOrderStateCodeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.GetOrderStateCodeResponseDto;
import java.util.List;

/**
 * 주문상태코드 api 와 연동하기 위한 adaptor 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface OrderStateCodeAdaptor {
    /**
     * 모든주문상태코드를 반환합니다.
     *
     * @return 주문상태코드 리스트
     */
    List<GetOrderStateCodeResponseDto> getAllOrderStateCodeRequest();

    /**
     * 코드 번호로 주문상태코드를 조회합니다.
     *
     * @param codeNo 코드번호
     * @return 단건 주문상태코드
     */
    GetOrderStateCodeResponseDto getOrderStateCodeByCodeNoRequest(Integer codeNo);

    /**
     * 주문상태코드를 등록합니다.
     *
     * @param requestDto 등록용 Dto
     */
    void createOrderStateCodeRequest(
            CreateOrderStateCodeRequestDto requestDto);

    /**
     * 사용여부를 수정합니다.
     *
     * @param codeNo 코드번호.
     */
    void modifyOrderStateCodeUsedRequest(Integer codeNo);
}
