package com.nhnacademy.bookpub.bookpubfront.product.service.impl;

import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetOrderCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.OrderProductDto;
import com.nhnacademy.bookpub.bookpubfront.product.adaptor.ProductAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 상품 서비스 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductAdaptor productAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCategory(InputProductFormRequestDto dto, List<Integer> tagList,
                               Map<String, MultipartFile> fileMap) {
        dto.setSalePrice(dto.getProductPrice(), dto.getSaleRate());

        final boolean subscribed = dto.getSubscribed().equals("구독가능");

        String[] authorTmp = dto.getAuthors().split(",");
        List<Integer> authors = new ArrayList<>();
        for (String tmp : authorTmp) {
            authors.add(Integer.parseInt(tmp));
        }

        List<Integer> categories = new ArrayList<>();
        categories.add(Integer.parseInt(dto.getCategoryOne()));

        if (!dto.getCategoryTwo().equals("")) {
            categories.add(Integer.parseInt(dto.getCategoryTwo()));
        }

        if (!dto.getCategoryThree().equals("")) {
            categories.add(Integer.parseInt(dto.getCategoryThree()));
        }

        CreateProductRequestDto request = new CreateProductRequestDto(
                dto.getProductIsbn(),
                dto.getTitle(),
                dto.getProductPublisher(),
                dto.getPageCount(),
                dto.getProductDescription(),
                dto.getSalePrice(),
                dto.getProductPrice(),
                dto.getSaleRate(),
                dto.getPriority(),
                0,
                dto.getPublishedAt(),
                subscribed,
                dto.getPolicyNo(),
                dto.getSaleStateNo(),
                dto.getTypeStateNo(),
                authors,
                categories,
                tagList,
                new ArrayList<>()
        );

        productAdaptor.requestCreateProduct(request, fileMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductListResponseDto> findAllProducts(Pageable pageable) {
        return productAdaptor.requestProducts(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProductDeleted(Long productNo) {
        productAdaptor.requestSetProductDeleted(productNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetProductDetailResponseDto findProduct(Long productNo) {
        return productAdaptor.requestProductDetail(productNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductByTypeResponseDto> findProductsByType(Integer typeNo, Integer limit) {
        return productAdaptor.requestProductByType(typeNo, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductDetailResponseDto> findProductInCart(List<Long> productsNo) {
        return productAdaptor.requestProductInCart(productsNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderProductDto> orderProductInCart(List<String> products, boolean isLoginUser) {
        List<OrderProductDto> orderProductList = new ArrayList<>();

        List<Long> productNos =
                products.stream()
                        .map(s -> Long.parseLong(s.split("-")[0]))
                        .collect(Collectors.toList());

        List<Integer> productCounts =
                products.stream()
                        .map(s -> Integer.parseInt(s.split("-")[1]))
                        .collect(Collectors.toList());

        List<GetProductDetailResponseDto> productsDtos =
                productAdaptor.requestProductInCart(productNos);


        for (int i = 0; i < productCounts.size(); i++) {
            orderProductList.add(convertDto(productsDtos.get(i), productCounts.get(i)));
        }

        if (isLoginUser) {
            orderProductList
                    .forEach(p -> p.addCouponInfo(availableCouponProduct(p.getProductNo())));
        }

        return orderProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderProductDto convertDto(GetProductDetailResponseDto product, int count) {
        return OrderProductDto.builder()
                .title(product.getTitle())
                .salesPrice(product.getSalesPrice())
                .productNo(product.getProductNo())
                .categoriesNo(product.getCategoriesNo())
                .count(count)
                .thumbnail(product.getThumbnail())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductByCategoryResponseDto> findProductByCategory(Integer categoryNo,
                                                                               Pageable pageable) {
        return productAdaptor.requestProductsByCategory(categoryNo, pageable);
    }

    @Override
    public List<GetOrderCouponResponseDto> availableCouponProduct(Long productNo) {
        Long memberNo = Long.parseLong(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        ResponseEntity<List<GetOrderCouponResponseDto>> listResponseEntity =
                productAdaptor.requestOrderCoupons(productNo, memberNo);

        return listResponseEntity.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductByCategoryResponseDto> getEbooks(Pageable pageable) {
        return productAdaptor.requestEbooks(pageable);
    }
}
