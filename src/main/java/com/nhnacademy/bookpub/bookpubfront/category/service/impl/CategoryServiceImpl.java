package com.nhnacademy.bookpub.bookpubfront.category.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.category.adaptor.CategoryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.CreateCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.ModifyCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 카테고리를 위한 실 클래스입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryAdaptor categoryAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCategory(CreateCategoryRequestDto createCategoryRequestDto)
            throws JsonProcessingException {
        categoryAdaptor.requestAddCategory(createCategoryRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyCategory(ModifyCategoryRequestDto modifyCategoryRequestDto)
            throws JsonProcessingException {
        categoryAdaptor.requestModifyCategory(modifyCategoryRequestDto);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCategoryResponseDto> getCategories(Pageable pageable) {
        return categoryAdaptor.requestCategoryList(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCategoryResponseDto> getParentCategories() {
        return categoryAdaptor.requestParentCategoryList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetCategoryResponseDto getCategory(Integer categoryNo) {
        return categoryAdaptor.requestCategory(categoryNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetParentCategoryWithChildrenResponseDto> getParentCategoryWithChildren() {
        return categoryAdaptor.requestParentWithChildList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCategoryInfoResponseDto> getAllCategories() {
        return categoryAdaptor.requestAllCategoryList();
    }
}
