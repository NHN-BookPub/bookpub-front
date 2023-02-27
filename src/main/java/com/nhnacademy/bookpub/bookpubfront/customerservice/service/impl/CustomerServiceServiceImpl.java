package com.nhnacademy.bookpub.bookpubfront.customerservice.service.impl;

import com.nhnacademy.bookpub.bookpubfront.customerservice.adaptor.CustomerServiceAdaptor;
import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.CreateCustomerServiceRequestDto;
import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.GetCustomerServiceListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.customerservice.service.CustomerServiceService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

/**
 * 고객서비스 서비스의 구현체.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CustomerServiceServiceImpl implements CustomerServiceService {
    private final CustomerServiceAdaptor customerServiceAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "customerService", allEntries = true)
    public void createCustomerService(CreateCustomerServiceRequestDto requestDto, MultipartFile image) {
        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("requestDto", requestDto);
        requestMap.add("image", image.getResource());

        customerServiceAdaptor.createCustomerService(requestMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(cacheNames = "customerService", key = "'service:' + #pageable.pageNumber")
    public PageResponse<GetCustomerServiceListResponseDto> getCustomerServices(Pageable pageable) {
        return customerServiceAdaptor.getCustomerServices(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(cacheNames = "customerService", key = "'code:'+#name+'page'+#pageable.pageNumber")
    public PageResponse<GetCustomerServiceListResponseDto> getCustomerServiceByCodeName(String name, Pageable pageable) {
        return customerServiceAdaptor.getCustomerServiceByCodeName(name, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(cacheNames = "customerService", key = "'category:'+#category+'page'+#pageable.pageNumber")
    public PageResponse<GetCustomerServiceListResponseDto> getCustomerServiceByCategory(String category, Pageable pageable) {
        return customerServiceAdaptor.getCustomerServiceByCategory(category, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetCustomerServiceListResponseDto getCustomerServiceByNo(Integer serviceNo) {
        return customerServiceAdaptor.getCustomerServiceByNo(serviceNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "customerService", allEntries = true)
    public void deleteCustomerService(Integer serviceNo) {
        customerServiceAdaptor.deleteCustomerService(serviceNo);
    }
}
