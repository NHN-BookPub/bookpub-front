package com.bookpub.bookpubfront.author.controller;

import com.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.bookpub.bookpubfront.author.service.AuthorService;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지에서 저자 관리를 위한 컨트롤러.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/authors")
public class AdminAuthorController {

    private final AuthorService authorService;


    /**
     * 관리자 페이지에서 저자 목록을 보여주기 위한 매핑.
     *
     * @param pageable 페이징 정보
     * @param model    view 로 정보를 보낼 request
     * @return 관리자 목록
     */
    @GetMapping
    public String findAllAuthors(@PageableDefault Pageable pageable, Model model) {
        PageResponse<GetAuthorResponseDto> authors = authorService.getAuthors(pageable);

        model.addAttribute("content", authors.getContent());
        model.addAttribute("next", authors.isNext());

        return "/admin/author/authorIndex";
    }
}
