package com.nhnacademy.bookpub.bookpubfront.product.service;

import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetOrderCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.OrderProductDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductInfoRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

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
    void createProduct(InputProductFormRequestDto dto, List<Integer> tagList, Map<String, MultipartFile> fileMap);

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

    /**
     * 상품 유형을 가지고 상품을 조회하려는 메서드.
     *
     * @param typeNo 상품 유형 번호
     * @param limit  제한 갯수
     * @return 유형별 상품
     */
    List<GetProductByTypeResponseDto> findProductsByType(Integer typeNo, Integer limit);

    /**
     * 장바구니에 있는 상품들을 조회하려는 메서드.
     *
     * @param productsNo 장바구니에 있는 상품 번호들
     * @return 장바구니에 있는 상품들
     */
    List<GetProductDetailResponseDto> findProductInCart(List<Long> productsNo);

    /**
     * 장바구니에서 주문하는 상품들을 가져오는 메소드.
     *
     * @param products 주문하는 상품들.
     * @return 상품에 대한 정보.
     */
    List<OrderProductDto> orderProductInCart(List<String> products, boolean isLoginUser);

    /**
     * dto를 주문용 dto로 변환시켜주는 메소드.
     *
     * @param product 상품.
     * @param count   개수.
     * @return 상품이 몇개인지 알 수 있는 dto.
     */
    OrderProductDto convertDto(GetProductDetailResponseDto product, int count);

    /**
     * 카테고리별 상품 조회 메서드.
     *
     * @param categoryNo 카테고리 번호
     * @param pageable   페이징 정보
     * @return 페이징 정보를 담은 상품들
     */
    PageResponse<GetProductByCategoryResponseDto> findProductByCategory(Integer categoryNo, Pageable pageable);

    /**
     * 상품에 적용가능한 쿠폰 리스트를 조회하는 메소드.
     *
     * @param productsNo 상품번호들.
     * @return 사용가능한 쿠폰 dto 리스트.
     */
    List<GetOrderCouponResponseDto> availableCouponProduct(Long productsNo);

    /**
     * 이북 조회 합니다.
     *
     * @param pageable 페이징
     * @return 이북 페이지
     */
    PageResponse<GetProductByCategoryResponseDto> getEbooks(Pageable pageable);

    /**
     * 상품 정보를 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param request   상품 정보를 수정할 dto
     */
    void modifyProductInfo(Long productNo, ModifyProductInfoRequestDto request);

    /**
     * 상품 카테고리를 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param request   수정할 카테고리 정보
     */
    void modifyProductCategory(Long productNo, ModifyProductCategoryRequestDto request);

    /**
     * 상품 저자를 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param authors   수정할 저자 정
     */
    void modifyProductAuthor(Long productNo, String authors);

    /**
     * 상품 태그를 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param tagList   수정할 태그 정보
     */
    void modifyProductTag(Long productNo, List<Integer> tagList);

    /**
     * 상품 유형을 수정하는 메서드.
     *
     * @param productNo   상품 번호
     * @param typeStateNo 수정할 유형 번호
     */
    void modifyProductType(Long productNo, Integer typeStateNo);

    /**
     * 상품 판매 유형 수정하는 메서드.
     *
     * @param productNo   상품 번호
     * @param saleStateNo 수정할 판매 유형 번호
     */
    void modifyProductSale(Long productNo, Integer saleStateNo);

    /**
     * 상품 포인트 정책 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param policyNo  수정할 포인트 정책 번호
     */
    void modifyProductPolicy(Long productNo, Integer policyNo);

    /**
     * 상품 설명을 수정하는 메서드.
     *
     * @param productNo          상품 번호
     * @param productDescription 수정할 상품 설명
     */
    void modifyDescription(Long productNo, String productDescription);

    /**
     * E-Book 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param eBook     수정할 E-Book
     */
    void modifyProductEBook(Long productNo, MultipartFile eBook);

    /**
     * Image 수정하는 메서드.
     *
     * @param productNo 상품 번호
     * @param image     수정할 Image
     */
    void modifyProductImage(Long productNo, MultipartFile image);

    /**
     * Detail Image 수정하는 메서드.
     *
     * @param productNo   상품 번호
     * @param detailImage 수정할 상세 이미지
     */
    void modifyProductDetailImage(Long productNo, MultipartFile detailImage);

    /**
     * Image 추가 메서드.
     *
     * @param productNo 상품 번호
     * @param image     추가할 Image
     */
    void addNewImage(Long productNo, MultipartFile image);

    /**
     * Detail Image 추가 메서드.
     *
     * @param productNo   상품 번호
     * @param detailImage 추가할 Detail Image
     */
    void addNewDetailImage(Long productNo, MultipartFile detailImage);

    /**
     * 연관관계 상품 추가 메서드.
     *
     * @param productNo        상품 번호
     * @param relationProducts 연관 관계 상품 번호
     */
    void addRelationProduct(Long productNo, String relationProducts);

    /**
     * 연관관계 상품을 삭제하는 메서드.
     *
     * @param childNo 자식 상품 번호
     */
    void disconnectRelationProduct(Long childNo);

    /**
     * 상품 유형으로 상품을 조회합니다.
     *
     * @param typeNo   유형번호
     * @param pageable 페이징
     * @return 상품들
     */
    PageResponse<GetProductByCategoryResponseDto> findProductByType(Integer typeNo, Pageable pageable);

    /**
     * ebook 구매이력이 있는가를 확인하는 메소드.
     *
     * @param productNo 상품번호.
     * @param memberNo  회원번호.
     * @return true, false
     */
    boolean isPurchaseProduct(String productNo, String memberNo);
}
