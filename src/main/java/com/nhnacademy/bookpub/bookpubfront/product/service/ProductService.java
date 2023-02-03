package com.nhnacademy.bookpub.bookpubfront.product.service;

import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.OrderProductDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
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
    void createCategory(InputProductFormRequestDto dto, List<Integer> tagList, Map<String, MultipartFile> fileMap);

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
    List<OrderProductDto> orderProductInCart(List<String> products);

    /**
     * dto를 주문용 dto로 변환시켜주는 메소드.
     *
     * @param product 상품.
     * @param count   개수.
     * @return 상품이 몇개인지 알 수 있는 dto.
     */
    OrderProductDto convertDto(GetProductDetailResponseDto product, int count);

    /**
     * 카테고리별 상품 조회 메서드
     *
     * @param categoryNo 카테고리 번호
     * @param pageable   페이징 정보
     * @return 페이징 정보를 담은 상품들
     */
    PageResponse<GetProductByCategoryResponseDto> findProductByCategory(Integer categoryNo, Pageable pageable);
}
