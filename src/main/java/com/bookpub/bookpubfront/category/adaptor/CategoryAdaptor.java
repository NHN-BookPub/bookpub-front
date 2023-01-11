package com.bookpub.bookpubfront.category.adaptor;

import com.bookpub.bookpubfront.category.dto.request.CreateCategoryRequestDto;
import com.bookpub.bookpubfront.category.dto.request.ModifyCategoryRequestDto;
import com.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * Category 가 api 서버와 연동하기 위한 인터페이스.
 *
 * @author : 김서현
 * @since : 1.0
 **/
public interface CategoryAdaptor {

    /**
     * 카테고리 생성 api 전송 메소드.
     *
     * @param createCategoryRequestDto 카테고리 등록을 위한 dto
     * @throws JsonProcessingException Json 파싱 중 발생하는 오류
     */
    void requestAddCategory(CreateCategoryRequestDto createCategoryRequestDto)
            throws JsonProcessingException;

    /**
     * 카테고리 수정 api 전송 메소드.
     *
     * @param modifyCategoryRequestDto 카테고리 수정을 위한 dto
     * @throws JsonProcessingException Json 파싱 중 발생하는 오류
     */
    void requestModifyCategory(ModifyCategoryRequestDto modifyCategoryRequestDto)
            throws JsonProcessingException;

    /**
     * 카테고리 전체 api 서버에서 받아오는 메소드.
     *
     * @return 전체 카테고리
     */
    List<GetCategoryResponseDto> requestCategoryList();

    /**
     * 최상위 카테고리 api 서버에서 받아오는 메소드.
     *
     * @return 최상의 카테고리 리스트
     */
    List<GetCategoryResponseDto> requestParentCategoryList();

    /**
     * 카테고리 단건 api 서버에서 받아오는 메소드.
     *
     * @param categoryNo 카테고리 번호
     * @return 해당 카테고리번호의 카테고리 정보
     */
    GetCategoryResponseDto requestCategory(Integer categoryNo);

    /**
     * 최상위 카테고리와 그 하위 카테고리를 api 서버에서 받아오는 메소드.
     *
     * @return 최상위 카테고리 그 하위 카테고리 정보
     */
    List<GetParentCategoryWithChildrenResponseDto> requestParentWithChildList();

}
