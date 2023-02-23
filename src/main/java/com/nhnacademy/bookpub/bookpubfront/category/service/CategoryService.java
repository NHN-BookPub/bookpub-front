package com.nhnacademy.bookpub.bookpubfront.category.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.CreateCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.ModifyCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * 카테고리를 다루기 위한 Service 인터페이스입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
public interface CategoryService {

    /**
     * 카테고리 등록 메소드.
     *
     * @param createCategoryRequestDto 카테고리 등록을 위한 dto.
     * @throws JsonProcessingException Json 파싱 중 발생하는 오류.
     */
    void createCategory(CreateCategoryRequestDto createCategoryRequestDto)
            throws JsonProcessingException;

    /**
     * 카테고리 수정 메소드.
     *
     * @param modifyCategoryRequestDto 카테고리 수정을 위한 dto.
     * @throws JsonProcessingException Json 파싱 중 발생하는 오류.
     */
    void modifyCategory(ModifyCategoryRequestDto modifyCategoryRequestDto)
            throws JsonProcessingException;

    /**
     * 전체 카테고리 조회하는 메소드.
     *
     * @return 카레고리 리스트 페이지.
     */
    PageResponse<GetCategoryResponseDto> getCategories(Pageable pageable);

    /**
     * 최상위 카테고리 조회하는 메소드.
     *
     * @return 최상위 카테고리 반환.
     */
    List<GetCategoryResponseDto> getParentCategories();

    /**
     * 단건 카테고리 조회하는 메소드.
     *
     * @param categoryNo 카테고리 번호.
     * @return 해당 카테고리 정보 반환.
     */
    GetCategoryResponseDto getCategory(Integer categoryNo);

    /**
     * 최상위 카테고리와 그 하위 카테고리 조회하는 메소드.
     *
     * @return 최상위 카테고리와 그 하위 카테고리 반환.
     */
    List<GetParentCategoryWithChildrenResponseDto> getParentCategoryWithChildren();

    /**
     * 관리자 좋아요 현황을 위한 카테고리 검색을 위한 메서드.
     *
     * @return 카테고리 정보 반환.
     */
    List<GetCategoryInfoResponseDto> getAllCategories();
}
