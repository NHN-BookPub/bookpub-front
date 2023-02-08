package com.nhnacademy.bookpub.bookpubfront.wishlist.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.CreateWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.DeleteWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.ModifyWishlistAlarmRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 위시리스트 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final MemberService memberService;
    private static final String MEMBER = "member";

    /**
     * 멤버별 위시리스트 view controller.
     *
     * @param memberNo 멤버 번호
     * @param pageable 페이징 정보
     * @param model    model
     * @return 멤버별 위시리스트 화면
     */
    @Auth
    @GetMapping("/members/{memberNo}/wishlist")
    public String memberWishList(@PathVariable("memberNo") Long memberNo,
                                 @PageableDefault(size = 8) Pageable pageable,
                                 Model model) {
        PageResponse<GetWishlistResponseDto> wishlist =
                wishlistService.getWishlistByMember(memberNo, pageable);
        MemberDetailResponseDto member = memberService.getTokenMember(memberNo);
        model.addAttribute(MEMBER, member);

        model.addAttribute("wishlists", wishlist.getContent());
        model.addAttribute("totalPages", wishlist.getTotalPages());
        model.addAttribute("currentPage", wishlist.getNumber());
        model.addAttribute("isNext", wishlist.isNext());
        model.addAttribute("isPrevious", wishlist.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myWishlist";
    }

    /**
     * 위시리스트에 추가할 메서드.
     *
     * @param memberNo  멤버 번호
     * @param productNo 상품 번호
     */
    @Auth
    @PostMapping("/wishlist")
    public @ResponseBody void addProductToWishList(Long memberNo, Long productNo) {
        CreateWishlistRequestDto request = new CreateWishlistRequestDto(productNo);
        wishlistService.createWishlist(memberNo, request);
    }

    /**
     * 위시리스트에서 상품을 삭제할 메서드.
     *
     * @param memberNo  멤버 번호
     * @param productNo 상품 번호
     */
    @Auth
    @DeleteMapping("/wishlist")
    public @ResponseBody void deleteWishlist(Long memberNo, Long productNo) {
        DeleteWishlistRequestDto request = new DeleteWishlistRequestDto(productNo);
        wishlistService.deleteWishlist(memberNo, request);
    }

    /**
     * 위시리스트에서 상품의 입고 알람 여부를 변경하는 메서드.
     *
     * @param memberNo  멤버 번호
     * @param productNo 상품 번호
     */
    @Auth
    @PutMapping("/wishlist")
    public @ResponseBody void modifyAlarm(Long memberNo, Long productNo) {
        ModifyWishlistAlarmRequestDto request = new ModifyWishlistAlarmRequestDto(productNo);
        wishlistService.modifyWishlistAlarm(memberNo, request);
    }

}
