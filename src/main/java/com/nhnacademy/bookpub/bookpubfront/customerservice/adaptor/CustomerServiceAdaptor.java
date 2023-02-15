package com.nhnacademy.bookpub.bookpubfront.customerservice.adaptor;

import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.GetCustomerServiceListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

/**
 * 고객서비스의 아답터.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface CustomerServiceAdaptor {

    /**
     * 고객서비스를 생성합니다.
     *
     * @param requestMap 생성에 필요한 정보들
     */
    void createCustomerService(MultiValueMap<String, Object> requestMap);

    /**
     * 모든 고객서비스를 조회합니다.
     * @param pageable 페이징
     * @return 고객서비스 리스트
     */
    PageResponse<GetCustomerServiceListResponseDto> getCustomerServices(Pageable pageable);

    /**
     * 코드명으로 고객서비스를 조회합니다.
     *
     * @param name 코드명
     * @param pageable 페이징
     * @return 고객서비스 리스트
     */
    PageResponse<GetCustomerServiceListResponseDto> getCustomerServiceByCodeName(String name, Pageable pageable);

    /**
     *
     * 카테고리로 고객서비스를 조회합니다.
     * @param category 카테고리
     * @param pageable 페이징
     * @return 고객서비스 리스트
     */
    PageResponse<GetCustomerServiceListResponseDto> getCustomerServiceByCategory(String category, Pageable pageable);

    /**
     * 고객서비스 단건 조회.
     *
     * @param serviceNo 서비스 번호
     * @return 고객서비스 단건
     */
    GetCustomerServiceListResponseDto getCustomerServiceByNo(Integer serviceNo);

    /**
     * 고객서비스를 삭제합니다.
     *
     * @param serviceNo 서비스 번호
     */
    void deleteCustomerService(Integer serviceNo);
}
