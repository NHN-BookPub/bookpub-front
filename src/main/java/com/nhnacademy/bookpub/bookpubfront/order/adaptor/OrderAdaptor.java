package com.nhnacademy.bookpub.bookpubfront.order.adaptor;

import com.nhnacademy.bookpub.bookpubfront.order.dto.request.CreateOrderRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetExchangeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderAndPaymentResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderConfirmResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListForAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.state.OrderState;
import com.nhnacademy.bookpub.bookpubfront.state.anno.StateCode;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 주문 api 와 연동하기 위한 adaptor 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface OrderAdaptor {

    /**
     * 주문을 생성하기 위한 메서드입니다.
     *
     * @param requestDto 생성을 위한 Dto.
     */
    Long createOrderRequest(CreateOrderRequestDto requestDto);

    /**
     * 관리자가 모든 주문을 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이지.
     * @return 페이지를 반환합니다.(사이즈는 10으로 고정됩니다.)
     */
    PageResponse<GetOrderListForAdminResponseDto> getAllOrdersRequest(Pageable pageable);

    /**
     * 회원번호로 주문목록을 조회합니다.
     *
     * @param pageable 페이지.
     * @param memberNo 회원번호입니다.
     * @return 페이지를 반환합니다.
     */
    PageResponse<GetOrderListResponseDto> getAllOrdersByMemberNoRequest(
            Pageable pageable, Long memberNo);

    /**
     * 주문을 상세조회하기 위한 메서드입니다.
     *
     * @param orderNo 주문번호입니다.
     * @return 주문상세 Dto 반환.
     */
    GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo, Long memberNo);


    /**
     * 주문 버튼 클릭 이후 결제페이지에서 주문 정보를 확인하기 위한 메서드.
     *
     * @param orderNo 주문번호.
     * @return 주문정보.
     */
    GetOrderConfirmResponseDto getOrderConfirmRequest(Long orderNo);

    /**
     * 주문을 상세조회하기 위한 메서드입니다.(비회원)
     *
     * @param orderId 주문번호
     * @param phoneNo 전화번호
     * @return 주문상세정보
     */
    GetOrderDetailResponseDto getOrderDetailByOrderIdRequest(String orderId, String phoneNo);

    /**
     * 송장번호를 수정하기 위한 메서드입니다.
     *
     * @param orderNo 주문번호입니다.
     * @param invoiceNo 송장번호입니다.
     */
    void modifyInvoiceNoRequest(Long orderNo, String invoiceNo);

    /**
     * 주문상태코드를 수정하기 위한 메서드입니다.
     *
     * @param orderNo 주문번호입니다.
     * @param codeName 코드명입니다.
     */
    void modifyStateCodeRequest(Long orderNo,
                                @StateCode(enumClass = OrderState.class)
                                String codeName);

    /**
     * 주문, 결제 정보를 얻어오기 위한 메서드입니다.
     *
     * @param orderId 주문번호.
     * @return 주문, 결제 정보
     */
    GetOrderAndPaymentResponseDto getOrderAndPaymentInfo(String orderId);

    /**
     * 구매한 이북을 조회합니다.
     *
     * @param pageable 페이징
     * @param memberNo 회원번호
     * @return 이북 리스트
     */
    PageResponse<GetProductByCategoryResponseDto> getEbooksByMember(
            Pageable pageable, Long memberNo);

    /**
     * 주문상품 구매확정 메소드.
     *
     * @param orderProductNo 주문상품번호.
     * @param memberNo 회원번호.
     */
    void confirmOrderProduct(String orderProductNo, Long memberNo);

    /**
     * 교환신청한 주문상품리스트 조회 메소드.
     *
     * @param pageable 페이저블.
     * @return 주문상품리스트.
     */
    PageResponse<GetExchangeResponseDto> exchangeOrderList(Pageable pageable);

    /**
     * 주문상품 교환 허락 메소드.
     *
     * @param orderProductNo 주문상품번호.
     */
    void confirmExchange(String orderProductNo);

    /**
     * 주문상태에 따른 주문목록.
     *
     * @param pageable 페이징
     * @param codeName 코드명
     * @return 주문페이지
     */
    PageResponse<GetOrderListForAdminResponseDto>
    exchangeOrderListByCodeName(Pageable pageable, String codeName);
}
