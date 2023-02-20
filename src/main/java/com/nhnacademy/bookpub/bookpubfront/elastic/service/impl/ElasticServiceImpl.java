package com.nhnacademy.bookpub.bookpubfront.elastic.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.elastic.adaptor.ElasticAdaptor;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.AllResponseHit;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.AllSearchResponseDto;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.ProductResponseHit;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.ProductSearchResultDto;
import com.nhnacademy.bookpub.bookpubfront.elastic.service.ElasticService;
import com.nhnacademy.bookpub.bookpubfront.exception.ServerErrorException;
import com.nhnacademy.bookpub.bookpubfront.state.SearchState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            throw new ServerErrorException();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<AllSearchResponseDto>> searchAll(String keyword) {
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String result = elasticAdaptor.requestSearchAll(keyword);
        AllResponseHit allResponseHit;

        try {
            allResponseHit = objectMapper.readValue(result, AllResponseHit.class);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException();
        }

        Map<String, List<AllSearchResponseDto>> map = new HashMap<>();

        List<AllSearchResponseDto> productSearch = new ArrayList<>();
        List<AllSearchResponseDto> faqSearch = new ArrayList<>();
        List<AllSearchResponseDto> noticeSearch = new ArrayList<>();

        for (int i = 0; i < allResponseHit.getHits().getHits().size(); i++) {
            String csCategory = getCsCategory(allResponseHit, i);
            getProductSearchResult(allResponseHit, productSearch, i);
            getFaqSearchResult(allResponseHit, faqSearch, i, csCategory);
            getNoticeSearchResult(allResponseHit, noticeSearch, i, csCategory);
        }

        map.put(SearchState.PRODUCT.getKey(), productSearch);
        map.put(SearchState.FAQ.getKey(), faqSearch);
        map.put(SearchState.NOTICE.getKey(), noticeSearch);

        return map;
    }

    /**
     * 공지사항 검색 결과를 저장하는 메서드.
     *
     * @param allResponseHit 엘라스틱 검색 결과
     * @param noticeSearch   공지사항 검색 결과
     * @param i              반복문 i
     * @param csCategory     고객서비스 카테고리
     */
    private void getNoticeSearchResult(AllResponseHit allResponseHit, List<AllSearchResponseDto> noticeSearch, int i, String csCategory) {
        if (allResponseHit.getHits().getHits().get(i).get_source().get(0).getCscodename().equals("공지사항")) {
            noticeSearch.add(new AllSearchResponseDto(
                    null, null, null, null, null,
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCsid(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCscodename(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCstitle(),
                    csCategory,
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCsdate()
            ));
        }
    }

    /**
     * FAQ 검색 결과를 저장하는 메서드.
     *
     * @param allResponseHit 엘라스틱 검색 결과
     * @param faqSearch      FAQ 검색 결과
     * @param i              반복문 i
     * @param csCategory     고객서비스 카테고리
     */
    private void getFaqSearchResult(AllResponseHit allResponseHit, List<AllSearchResponseDto> faqSearch, int i, String csCategory) {
        if (allResponseHit.getHits().getHits()
                .get(i).get_source().get(0).getCscodename().equals("FAQ")) {
            faqSearch.add(new AllSearchResponseDto(
                    null, null, null, null, null,
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCsid(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCscodename(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCstitle(),
                    csCategory,
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getCsdate()
            ));
        }
    }

    /**
     * 상품 검색 결과를 저장하는 메서드.
     *
     * @param allResponseHit 엘라스틱 검색 결과
     * @param productSearch  상품 검색 결과
     * @param i              반복문 i
     */
    private void getProductSearchResult(AllResponseHit allResponseHit, List<AllSearchResponseDto> productSearch, int i) {
        if (Objects.nonNull(allResponseHit.getHits().getHits()
                .get(i).get_source().get(0).getId())) {
            productSearch.add(new AllSearchResponseDto(
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getId(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getTitle(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getSalesprice(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getSalesrate(),
                    allResponseHit.getHits().getHits().get(i).get_source().get(0).getFilepath(),
                    null, null, null, null, null
            ));
        }
    }

    /**
     * 고객서비스 카테고리를 convert 메서드.
     *
     * @param allResponseHit 엘라스틱 검색 결과
     * @param i              반복문 i
     * @return 고객서비스 카테고리
     */
    private String getCsCategory(AllResponseHit allResponseHit, int i) {
        String csCategory;

        if (Objects.isNull(allResponseHit.getHits().getHits()
                .get(i).get_source().get(0).getCscategory())) {
            csCategory = "";
        } else {
            switch (allResponseHit.getHits().getHits().get(i).get_source().get(0).getCscategory()) {
                case "faqUsing":
                    csCategory = "이용안내";
                    break;
                case "faqAccount":
                    csCategory = "계정안내";
                    break;
                case "faqPayment":
                    csCategory = "결제안내";
                    break;
                case "faqOthers":
                    csCategory = "기타안내";
                    break;
                case "noteNormal":
                    csCategory = "일반";
                    break;
                case "noteServer":
                    csCategory = "서버";
                    break;
                case "notePayment":
                    csCategory = "결제";
                    break;
                case "noteOthers":
                    csCategory = "기타";
                    break;
                default:
                    csCategory = "";
                    break;
            }
        }

        return csCategory;
    }
}