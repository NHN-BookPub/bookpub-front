package com.bookpub.bookpubfront.product.service;

import com.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * 상품을 다루기 위한 서비스.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface ProductService {

    /**
     * 상품 등록을 처리하는 메서드.
     *
     * @param dto     상품 등록 폼에서 입력을 받은 DTO
     * @param tagList 상품 등록 폼에서 입력을 받는 태그 리스트
     */
    void createCategory(InputProductFormRequestDto dto, List<Integer> tagList);

    /**
     * 전체 상품을 받아오려는 메서드.
     *
     * @param pageable 페이징 정보
     * @return 페이징정보를 담은 전체 상품 DTO
     */
    PageResponse<GetProductListResponseDto> findAllProducts(Pageable pageable);

    /**
     * 상품 삭제 여부를 처리하는 메서드.
     *
     * @param productNo 상품 번호
     */
    void setProductDeleted(Long productNo);

    /**
     * 상품 단건을 조회하려는 메서드.
     *
     * @param productNo 상품 번호
     * @return 상품 상세 정보를 담은 DTO
     */
    GetProductDetailResponseDto findProduct(Long productNo);
}
