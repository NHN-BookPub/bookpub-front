package com.nhnacademy.bookpub.bookpubfront.elastic.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.elastic.adaptor.ElasticAdaptor;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.ProductResponseHit;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.ProductSearchResultDto;
import com.nhnacademy.bookpub.bookpubfront.elastic.service.ElasticService;
import com.nhnacademy.bookpub.bookpubfront.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ElasticService 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ElasticServiceImpl implements ElasticService {

    private final ElasticAdaptor elasticAdaptor;
    private final ObjectMapper objectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductSearchResultDto> searchProduct(String keyword) {
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ProductResponseHit productHit;
        String result = elasticAdaptor.requestSearchProduct(keyword);

        try {
            productHit = objectMapper.readValue(result, ProductResponseHit.class);
        } catch (JsonProcessingException e) {
            throw new NotFoundException();
        }

        List<ProductSearchResultDto> searchResult = new ArrayList<>();

        for (int i = 0; i < productHit.getHits().getHits().size(); i++) {
            searchResult.add(new ProductSearchResultDto(
                    productHit.getHits().getHits().get(i).get_source().get(0).getId(),
                    productHit.getHits().getHits().get(i).get_source().get(0).getTitle(),
                    productHit.getHits().getHits().get(i).get_source().get(0).getSalesprice(),
                    productHit.getHits().getHits().get(i).get_source().get(0).getSalesrate(),
                    productHit.getHits().getHits().get(i).get_source().get(0).getFilepath()
            ));
        }

        return searchResult;
    }
}
