package com.nhnacademy.bookpub.bookpubfront.product.adaptor;

import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetOrderCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateRelationProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductDescriptionRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductInfoRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.RestModifyProductCategoryRequestDto;
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
     * @param memberNo  로그인한 유저 No.
     * @return 상품 별 쿠폰 리스트.
     */
    ResponseEntity<List<GetOrderCouponResponseDto>> requestOrderCoupons(Long productNo,
                                                                        Long memberNo);

    /**
     * 상품 정보 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param request   상품 수정 정보가 담긴 dto
     */
    void requestModifyProductInfo(Long productNo, ModifyProductInfoRequestDto request);

    /**
     * 상품 카테고리 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param request   수정할 카테고리
     */
    void requestModifyProductCategory(Long productNo, RestModifyProductCategoryRequestDto request);

    /**
     * 상품 저자 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param request   수정할 저자
     */
    void requestModifyProductAuthor(Long productNo, ModifyProductAuthorRequestDto request);

    /**
     * 상품 태그 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param request   수정할 태그
     */
    void requestModifyProductTag(Long productNo, ModifyProductTagRequestDto request);

    /**
     * 상품 유형 수정 API 호출 메서드.
     *
     * @param productNo   상품 번호
     * @param typeStateNo 수정할 유형 번호
     */
    void requestModifyProductType(Long productNo, Integer typeStateNo);

    /**
     * 상품 판매 유형 수정 API 호출 메서드.
     *
     * @param productNo   상품 번호
     * @param saleStateNo 수정할 판매 유형 번호
     */
    void requestModifyProductSale(Long productNo, Integer saleStateNo);

    /**
     * 상품 포인트 정책 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param policyNo  수정할 포인트 정책 번호
     */
    void requestModifyProductPolicy(Long productNo, Integer policyNo);

    /**
     * 상품 설명 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param request   수정할 상품 설명
     */
    void requestModifyProductDescription(Long productNo, ModifyProductDescriptionRequestDto request);

    /**
     * E-Book 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param fileMap   E-Book 정보가 담긴 Map
     */
    void requestModifyProductEBook(Long productNo, Map<String, MultipartFile> fileMap);

    /**
     * Image 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param fileMap   Image 정보가 담긴 Map
     */
    void requestModifyProductImage(Long productNo, Map<String, MultipartFile> fileMap);

    /**
     * Detail Image 수정 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param fileMap   Detail Image 정보가 담긴 Map
     */
    void requestModifyProductDetailImage(Long productNo, Map<String, MultipartFile> fileMap);

    /**
     * Image 추가 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param fileMap   Image 정보가 담긴 Map
     */
    void requestAddProductImage(Long productNo, Map<String, MultipartFile> fileMap);

    /**
     * Detail Image 추가 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param fileMap   Detail Image 정보가 담긴 Map
     */
    void requestAddProductDetailImage(Long productNo, Map<String, MultipartFile> fileMap);

    /**
     * 연관 관계 상품 등록 API 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param request   등록할 연관 관계 상품 번호
     */
    void requestAddRelationProducts(Long productNo, CreateRelationProductRequestDto request);

    /**
     * 연관 관계 삭제 API 호출 메서드.
     *
     * @param childNo 자식 상품 번호
     */
    void requestDisconnectRelationProduct(Long childNo);

    /**
     * 상품 유형으로 상품을 조회합니다.
     *
     * @param typeNo   유형번호
     * @param pageable 페이징
     * @return 상품들
     */
    PageResponse<GetProductByCategoryResponseDto> requestProductsByType(Integer typeNo, Pageable pageable);

    /**
     * ebook 구매이력 정보를 받아오는 메소드.
     *
     * @param productNo 상품번호.
     * @param memberNo  회원번호.
     * @return true, false.
     */
    Boolean isPurchaseProduct(String productNo, String memberNo);

}
