package com.bookpub.bookpubfront.product.adaptor;

import com.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * API 서버와 연동을 위한 상품 어댑터 인터페이스.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface ProductAdaptor {

    /**
     * 상품 등록 API 부르는 메서드.
     *
     * @param request 상품등록을 위한 DTO
     */
    void requestCreateProduct(CreateProductRequestDto request);

    /**
     * 상품 목록 API 부르는 메서드.
     *
     * @param pageable 페이징 정보
     * @return 페이지 응답
     */
    PageResponse<GetProductListResponseDto> requestProducts(Pageable pageable);

    /**
     * 상품 삭제 여부를 변경하는 API 메서드.
     *
     * @param productNo 상품번호
     */
    void requestSetProductDeleted(Long productNo);

    /**
     * 상품 상세 정보 API 부르는 메서드.
     *
     * @param productNo 상품 번호
     * @return 상품 상세 정보
     */
    GetProductDetailResponseDto requestProductDetail(Long productNo);

    /**
     * 상품 유형을 가지고 메인페이지에서 보여줄 상품 API 부르는 메서드.
     *
     * @param typeNo 유형 번호
     * @param limit  제한 갯수
     * @return 유형별 상품들
     */
    List<GetProductByTypeResponseDto> requestProductByType(Integer typeNo, Integer limit);
}
