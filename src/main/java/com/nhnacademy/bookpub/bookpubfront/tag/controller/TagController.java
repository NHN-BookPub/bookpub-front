package com.nhnacademy.bookpub.bookpubfront.tag.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지에서 태그 관리를 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Log4j2
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class TagController {
    private static final String REDIRECT_URL = "redirect:/admin/tags";
    private final TagService tagService;

    /**
     * 관레자 페이지에서 태그 목록을 조회해서 보여주는 메서드.
     *
     * @param model view 로 정보를 보내는 request
     * @return 태그 목록
     */
    @GetMapping("/tags")
    public String tagList(Model model) {
        List<GetTagResponseDto> tags = tagService.findAllTags();

        model.addAttribute("tags", tags);

        return "admin/tag/tagIndex";
    }

    /**
     * 관리자 페이지에서 태그 등록을 처리하는 메서드.
     *
     * @param request 등록 폼에서 입력된 값을 받는 DTO
     * @return 태그 목록으로 redirect
     */
    @PostMapping("/add/tag")
    @Auth
    public String addTag(@ModelAttribute AddTagRequestDto request) {
        tagService.addTag(request);

        return REDIRECT_URL;
    }

    /**
     * 관리자 페이지에서 태그 수정을 처리하는 메서드.
     *
     * @param request 수정 폼에서 입력된 값을 받는 DTO
     * @return 태그 목록으로 redirect
     */
    @PostMapping("/modify/tag")
    @Auth
    public String modifyTag(@ModelAttribute ModifyTagRequestDto request) {
        tagService.modifyTag(request);

        return REDIRECT_URL;
    }

    /**
     * 관리자 페이지에서 삭제를 처리하는 메서드.
     *
     * @param tagNo 삭제를 하려는 태그 번호
     * @return 태그 목록으로 redirect
     */
    @PostMapping("/delete/tags/{tagNo}")
    @Auth
    public String deleteTag(@PathVariable Integer tagNo) {
        tagService.deleteTag(tagNo);

        return REDIRECT_URL;
    }
}
