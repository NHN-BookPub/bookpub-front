package com.bookpub.bookpubfront.order.service;

import com.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 주문 어답터와 연결되는 서비스입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface OrderService {
    /**
     * 주문을 등록합니다.
     *
     * @param requestDto 등록에 필요한 Dto.
     */
    void createOrder(CreateOrderRequestDto requestDto);

    /**
     * 송장번호를 수정합니다.
     *
     * @param orderNo 주문번호.
     * @param invoiceNo 송장번호.
     */
    void modifyInvoiceNo(Long orderNo, String invoiceNo);

    /**
     * 상태코드를 수정합니다.
     *
     * @param orderNo 주문번호.
     * @param codeName 코드명.
     */
    void modifyStateCode(Long orderNo, String codeName);

    /**
     * 주문번호로 상세정보를 조회합니다.
     *
     * @param orderNo 주문번호.
     * @return 상세정보를 반환.
     */
    GetOrderDetailResponseDto getOrderDetailByNo(Long orderNo);

    /**
     * 모든 주문의 리스트를 반환합니다.
     *
     * @param pageable 페이지.
     * @return 페이지 객체.
     */
    PageResponse<GetOrderListResponseDto> getOrderList(Pageable pageable);

    /**
     * 회원 번호로 모든 주문 리스트를 반환합니다.
     *
     * @param memberNo 회원번호.
     * @param pageable 페이지.
     * @return 페이지 객체.
     */
    PageResponse<GetOrderListResponseDto> getOrderListByMemberNo(Long memberNo, Pageable pageable);
}
