package com.bookpub.bookpubfront.order.service;

import com.bookpub.bookpubfront.order.dto.response.GetAddressResponseDto;
import java.util.List;

/**
 * 주문에 사용되는 서비스 인터페이스.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
public interface OrderService {
    List<GetAddressResponseDto> getMemberAddresses();
}
