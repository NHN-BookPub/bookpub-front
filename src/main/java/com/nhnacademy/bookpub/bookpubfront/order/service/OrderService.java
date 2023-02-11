package com.nhnacademy.bookpub.bookpubfront.order.service;

import com.nhnacademy.bookpub.bookpubfront.order.dto.request.OrderFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderAndPaymentResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListForAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
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
    Long createOrder(OrderFormRequestDto requestDto, String productInfo);

    /**
     * 송장번호를 수정합니다.
     *
     * @param orderNo   주문번호.
     * @param invoiceNo 송장번호.
     */
    void modifyInvoiceNo(Long orderNo, String invoiceNo);

    /**
     * 상태코드를 수정합니다.
     *
     * @param orderNo  주문번호.
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
     * 주문번호로 상세정보를 조회합니다.(비회원)
     *
     * @param orderId 주문 Id
     * @param phoneNo 전화번호
     * @return 주문상세
     */
    GetOrderDetailResponseDto getOrderDetailResponseDto(String orderId, String phoneNo);

    /**
     * 모든 주문의 리스트를 반환합니다.
     *
     * @param pageable 페이지.
     * @return 페이지 객체.
     */
    PageResponse<GetOrderListForAdminResponseDto> getOrderList(Pageable pageable);

    /**
     * 회원 번호로 모든 주문 리스트를 반환합니다.
     *
     * @param memberNo 회원번호.
     * @param pageable 페이지.
     * @return 페이지 객체.
     */
    PageResponse<GetOrderListResponseDto> getOrderListByMemberNo(Long memberNo, Pageable pageable);

    GetOrderAndPaymentResponseDto getOrderAndPaymentInfo(String orderId);

    PageResponse<GetProductByCategoryResponseDto> getEbooksByMember(Pageable pageable, Long memberNo);

    void refundOrder(Long orderNo);
}
