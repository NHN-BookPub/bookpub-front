package com.nhnacademy.bookpub.bookpubfront.product.service.impl;

import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetOrderCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.OrderProductDto;
import com.nhnacademy.bookpub.bookpubfront.product.adaptor.ProductAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateRelationProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductDescriptionRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductInfoRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.RestModifyProductCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
public class ProductServiceImpl implements ProductService {

    private final ProductAdaptor productAdaptor;

    /**
     * {@inheritDoc}
     */
    @CacheEvict(cacheNames = "products", allEntries = true)
    @Override
    public void createProduct(InputProductFormRequestDto dto, List<Integer> tagList,
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
                tagList
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
    @CacheEvict(cacheNames = "products", allEntries = true)
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
    public List<GetProductDetailResponseDto> findProductInCart(List<Long> productsNo) {
        return productAdaptor.requestProductInCart(productsNo);
    }

    /**
     * {@inheritDoc}
     */
    @Cacheable(cacheNames = "products", key = "'products:'+#typeNo")
    @Override
    public List<GetProductByTypeResponseDto> findProductsByType(Integer typeNo, Integer limit) {
        return productAdaptor.requestProductByType(typeNo, limit);
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
                .policyMethod(product.getPolicyMethod())
                .policySaved(product.isPolicySaved())
                .policySaveRate(product.getPolicySaveRate())
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

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductInfo(Long productNo, ModifyProductInfoRequestDto request) {
        request.setSalePrice(request.getProductPrice(), request.getSalesRate());

        productAdaptor.requestModifyProductInfo(productNo, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductCategory(Long productNo, ModifyProductCategoryRequestDto request) {
        RestModifyProductCategoryRequestDto dto = new RestModifyProductCategoryRequestDto();
        dto.getCategoriesNo().add(Integer.parseInt(request.getCategoryOne()));

        if (!request.getCategoryTwo().equals("")) {
            dto.getCategoriesNo().add(Integer.parseInt(request.getCategoryTwo()));
        }
        if (!request.getCategoryThree().equals("")) {
            dto.getCategoriesNo().add(Integer.parseInt(request.getCategoryThree()));
        }

        productAdaptor.requestModifyProductCategory(productNo, dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductAuthor(Long productNo, String authors) {
        String[] split = authors.split(",");
        List<Integer> tmp = new ArrayList<>();

        for (String s : split) {
            tmp.add(Integer.parseInt(s));
        }

        ModifyProductAuthorRequestDto dto = new ModifyProductAuthorRequestDto(tmp);

        productAdaptor.requestModifyProductAuthor(productNo, dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductTag(Long productNo, List<Integer> tagList) {
        ModifyProductTagRequestDto dto = new ModifyProductTagRequestDto(tagList);

        productAdaptor.requestModifyProductTag(productNo, dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductType(Long productNo, Integer typeStateNo) {
        productAdaptor.requestModifyProductType(productNo, typeStateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductSale(Long productNo, Integer saleStateNo) {
        productAdaptor.requestModifyProductSale(productNo, saleStateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductPolicy(Long productNo, Integer policyNo) {
        productAdaptor.requestModifyProductPolicy(productNo, policyNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyDescription(Long productNo, String productDescription) {
        ModifyProductDescriptionRequestDto dto =
                new ModifyProductDescriptionRequestDto(productDescription);

        productAdaptor.requestModifyProductDescription(productNo, dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductEBook(Long productNo, MultipartFile eBook) {
        Map<String, MultipartFile> fileMap = new HashMap<>();
        fileMap.put("eBook", eBook);

        productAdaptor.requestModifyProductEBook(productNo, fileMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductImage(Long productNo, MultipartFile image) {
        Map<String, MultipartFile> fileMap = new HashMap<>();
        fileMap.put("image", image);

        productAdaptor.requestModifyProductImage(productNo, fileMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void modifyProductDetailImage(Long productNo, MultipartFile detailImage) {
        Map<String, MultipartFile> fileMap = new HashMap<>();
        fileMap.put("detailImage", detailImage);

        productAdaptor.requestModifyProductDetailImage(productNo, fileMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void addNewImage(Long productNo, MultipartFile image) {
        Map<String, MultipartFile> fileMap = new HashMap<>();
        fileMap.put("image", image);

        productAdaptor.requestAddProductImage(productNo, fileMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void addNewDetailImage(Long productNo, MultipartFile detailImage) {
        Map<String, MultipartFile> fileMap = new HashMap<>();
        fileMap.put("detailImage", detailImage);

        productAdaptor.requestAddProductDetailImage(productNo, fileMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void addRelationProduct(Long productNo, String relationProducts) {
        String[] tmp = relationProducts.split(",");
        List<Long> list = new ArrayList<>();

        for (String str : tmp) {
            list.add(Long.parseLong(str));
        }

        CreateRelationProductRequestDto request = new CreateRelationProductRequestDto(list);
        productAdaptor.requestAddRelationProducts(productNo, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void disconnectRelationProduct(Long childNo) {
        productAdaptor.requestDisconnectRelationProduct(childNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductByCategoryResponseDto> findProductByType(Integer typeNo, Pageable pageable) {
        return productAdaptor.requestProductsByType(typeNo, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPurchaseProduct(String productNo, String memberNo) {
        return productAdaptor.isPurchaseProduct(productNo, memberNo);
    }
}
