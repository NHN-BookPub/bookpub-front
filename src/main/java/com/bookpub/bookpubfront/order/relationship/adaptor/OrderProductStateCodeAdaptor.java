package com.bookpub.bookpubfront.order.relationship.adaptor;

import com.bookpub.bookpubfront.order.relationship.dto.CreateOrderProductStateCodeRequestDto;
import com.bookpub.bookpubfront.order.relationship.dto.GetOrderProductStateCodeResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * 주문상품상태코드 api 와 연결하기 위한 어댑터입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface OrderProductStateCodeAdaptor {
    /**
     * 모든 주문상품상태코드를 반환합니다.
     *
     * @return 모든 주문상품상태코드 리스트.
     */
    List<GetOrderProductStateCodeResponseDto> getAllOrderProductStateCodeRequest();

    /**
     * 코드번호로 주문상품상태코드를 반환합니다.
     *
     * @param codeNo 코드번호
     * @return 주문상품상태코드 단건
     */
    GetOrderProductStateCodeResponseDto getOrderProductStateCodeByCodeNoRequest(Integer codeNo);

    /**
     * 주문상품상태코드를 등록합니다.
     *
     * @param requestDto 등록용 Dto
     * @throws JsonProcessingException Json 변환시 발생할 수 있는 예외.
     */
    void createOrderProductStateCodeRequest(
            CreateOrderProductStateCodeRequestDto requestDto)
            throws JsonProcessingException;

    /**
     * 주문상품상태코드의 사용여부를 수정합니다.
     *
     * @param codeNo 코드번호
     * @param used 사용여부
     */
    void modifyOrderProductStateCodeUsedRequest(Integer codeNo, boolean used);
}
