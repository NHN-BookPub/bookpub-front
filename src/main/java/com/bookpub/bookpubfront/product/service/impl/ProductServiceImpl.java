package com.bookpub.bookpubfront.product.service.impl;

import com.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.bookpub.bookpubfront.product.adaptor.ProductAdaptor;
import com.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.bookpub.bookpubfront.product.service.ProductService;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    @Override
    public void createCategory(InputProductFormRequestDto dto, List<Integer> tagList) {
        dto.setSalePrice(dto.getProductPrice(), dto.getSaleRate());

        boolean subscribed = dto.getSubscribed().equals("구독가능");

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

        productAdaptor.requestCreateProduct(request);
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
}
