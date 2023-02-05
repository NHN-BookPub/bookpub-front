package com.nhnacademy.bookpub.bookpubfront.author.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.AddAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.ModifyAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.nhnacademy.bookpub.bookpubfront.author.service.AuthorService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지에서 저자 관리를 위한 컨트롤러.
 *
 * @author : 박경서, 정유진, 김서현
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
    @Auth
    @GetMapping
    public String findAllAuthors(@PageableDefault Pageable pageable, Model model) {
        PageResponse<GetAuthorResponseDto> authors = authorService.getAuthors(pageable);

        model.addAttribute("authors", authors.getContent());
        model.addAttribute("totalPages", authors.getTotalPages());
        model.addAttribute("currentPage", authors.getNumber());

        model.addAttribute("isPrevious", authors.isPrevious());
        model.addAttribute("isNext", authors.isNext());
        model.addAttribute("pageButtonNum", 100);

        return "admin/author/authorIndex";
    }

    /**
     * 관리자 페이지에서 저자 등록을 처리하는 메서드.
     *
     * @param request 등록 폼에서 입력된 값을 받는 DTO.
     * @return 저자 목록으로 redirect
     */

    @Auth
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute AddAuthorRequestDto request) {
        authorService.addAuthor(request);

        return "redirect:/admin/authors";
    }

    /**
     * 관리자 페이지에서 저자 수정을 처리하는 메소드.
     *
     * @param request 수정 폼에서 입력된 값을 받는 DTO.
     * @return 저자 목록으로 redirect
     */
    @Auth
    @PostMapping("/modify")
    public String modifyAuthor(@ModelAttribute ModifyAuthorRequestDto request) {
        authorService.modifyAuthor(request);

        return "redirect:/admin/authors";
    }
}
