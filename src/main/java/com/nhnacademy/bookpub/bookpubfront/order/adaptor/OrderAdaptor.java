package com.nhnacademy.bookpub.bookpubfront.order.adaptor;

import com.nhnacademy.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderListForAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
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
    void createOrderRequest(CreateOrderRequestDto requestDto);

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
    GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo);

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
}
