package com.nhnacademy.bookpub.bookpubfront.product.adaptor;

import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetOrderCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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
     * @param requestDto 상품등록을 위한 DTO
     */
    void requestCreateProduct(CreateProductRequestDto requestDto, Map<String, MultipartFile> fileMap);

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

    /**
     * 장바구니에 있는 상품 번호를 가지고 상품 정보 API를 부르는 메서드.
     *
     * @param productsNo 장바구니에 있는 상품 번호들
     * @return 징바구니에 있는 상품들
     */
    List<GetProductDetailResponseDto> requestProductInCart(List<Long> productsNo);

    /**
     * 카테고리별로 상품을 조회 API를 부르는 메서드.
     *
     * @param categoryNo 카테고리 번호
     * @param pageable   페이징 정보
     * @return 카테고리별 상품들
     */
    PageResponse<GetProductByCategoryResponseDto> requestProductsByCategory(Integer categoryNo, Pageable pageable);


    /**
     * 이북 리스트를 얻어오는 메소드입니다.
     *
     * @param pageable 페이징
     * @return 이북 리스트
     */
    PageResponse<GetProductByCategoryResponseDto> requestEbooks(Pageable pageable);

    /**
     * 상품별 쿠폰 리스트를 얻어오는 메소드.
     *
     * @param productNo 상품번호.
     * @param memberNo 로그인한 유저 No.
     * @return 상품 별 쿠폰 리스트.
     */
    ResponseEntity<List<GetOrderCouponResponseDto>> requestOrderCoupons(Long productNo,
                                                                        Long memberNo);
}
