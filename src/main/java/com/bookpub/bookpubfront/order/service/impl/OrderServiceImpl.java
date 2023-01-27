package com.bookpub.bookpubfront.order.service.impl;

import com.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.bookpub.bookpubfront.order.dto.response.GetAddressResponseDto;
import com.bookpub.bookpubfront.order.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 주문 서비스의 구현체입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdaptor orderAdaptor;
    @Override
    public List<GetAddressResponseDto> getMemberAddresses() {
        return orderAdaptor.getMemberAddresses();
    }
}
