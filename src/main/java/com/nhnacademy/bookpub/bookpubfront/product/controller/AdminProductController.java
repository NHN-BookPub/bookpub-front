package com.nhnacademy.bookpub.bookpubfront.product.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.ModifyProductInfoRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductSaleStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductPolicyService;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductSaleStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductTypeStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tag.service.TagService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 관리자페이지에서 상품과 관련된 컨트롤러.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductTypeStateCodeService productTypeStateCodeService;
    private final ProductSaleStateCodeService productSaleStateCodeService;
    private final ProductPolicyService productPolicyService;
    private final TagService tagService;
    private static final String REDIRECT = "redirect:/admin/products";

    /**
     * 관리자 페이지에서 상품 목록을 보기 위한 맵핑.
     *
     * @param pageable 페이지 정보
     * @param model    view 로 보낼 request
     * @return 상품 목록 화면
     */
    @Auth
    @GetMapping
    public String products(@PageableDefault Pageable pageable, Model model) {
        List<GetTagResponseDto> tags = tagService.findAllTags();
        List<GetCategoryResponseDto> parentCategories = categoryService.getParentCategories();
        List<GetParentCategoryWithChildrenResponseDto> categories =
                categoryService.getParentCategoryWithChildren();
        List<GetProductTypeStateCodeResponseDto> typeStateCodes =
                productTypeStateCodeService.getProductTypeStateCodes();
        List<GetProductSaleStateCodeResponseDto> saleStateCodes =
                productSaleStateCodeService.getProductSaleStateCodes();
        List<GetProductPolicyResponseDto> policies = productPolicyService.getProductPolicies();
        PageResponse<GetProductListResponseDto> products = productService.findAllProducts(pageable);

        model.addAttribute("tags", tags);
        model.addAttribute("parentCategories", parentCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("typeStateCodes", typeStateCodes);
        model.addAttribute("saleStateCodes", saleStateCodes);
        model.addAttribute("policies", policies);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("isNext", products.isNext());
        model.addAttribute("isPrevious", products.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/product/productIndex";
    }

    /**
     * 상품 상세 정보 화면을 위한 맵핑.
     *
     * @param productNo 상품 번호
     * @param model     view 로 보낼 request
     * @return 상품 상세 정보 화
     */
    @Auth
    @GetMapping("/{productNo}")
    public String productDetail(@PathVariable("productNo") Long productNo, Model model) {
        List<GetTagResponseDto> tags = tagService.findAllTags();
        List<GetCategoryResponseDto> parentCategories = categoryService.getParentCategories();
        List<GetParentCategoryWithChildrenResponseDto> categories =
                categoryService.getParentCategoryWithChildren();
        List<GetProductTypeStateCodeResponseDto> typeStateCodes =
                productTypeStateCodeService.getProductTypeStateCodes();
        List<GetProductSaleStateCodeResponseDto> saleStateCodes =
                productSaleStateCodeService.getProductSaleStateCodes();
        List<GetProductPolicyResponseDto> policies = productPolicyService.getProductPolicies();

        GetProductDetailResponseDto product = productService.findProduct(productNo);

        model.addAttribute("tags", tags);
        model.addAttribute("parentCategories", parentCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("typeStateCodes", typeStateCodes);
        model.addAttribute("saleStateCodes", saleStateCodes);
        model.addAttribute("policies", policies);
        model.addAttribute("product", product);

        return "admin/product/productDetail";
    }

    /**
     * 상품 등록을 처리하는 매핑.
     *
     * @param inputProduct 상품 등록을 위한 DTO
     * @param tagList      선택된 태그를 담는 리스트
     * @return 상품 목록 화면으로 Redirect
     */
    @Auth
    @PostMapping("/add")
    public String addProduct(@ModelAttribute InputProductFormRequestDto inputProduct,
                             @RequestParam(value = "tagList", required = false) List<Integer> tagList,
                             @RequestPart(required = false) MultipartFile thumbnail,
                             @RequestPart(required = false) MultipartFile detail,
                             @RequestPart(required = false) MultipartFile ebook) {
        Map<String, MultipartFile> fileMap = new HashMap<>();
        if (thumbnail != null) {
            fileMap.put("thumbnail", thumbnail);
        }
        if (detail != null) {
            fileMap.put("detail", detail);
        }
        if (ebook != null) {
            fileMap.put("ebook", ebook);
        }

        productService.createProduct(inputProduct, tagList, fileMap);

        return REDIRECT;
    }

    /**
     * 상품 삭제 여부를 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @return 상품 목록 화면으로 Redirect
     */
    @Auth
    @PostMapping("/deleted/{productNo}")
    public String setDeletedProduct(@PathVariable("productNo") Long productNo) {
        productService.setProductDeleted(productNo);

        return REDIRECT;
    }

    /**
     * 상품 정보 수정을 처리할 매핑.
     *
     * @param productNo  상품 번호
     * @param requestDto 수정 정보
     * @return 상품 상세페이지로 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/info")
    public String modifyProductInfo(@PathVariable("productNo") Long productNo,
                                    @ModelAttribute ModifyProductInfoRequestDto requestDto) {
        productService.modifyProductInfo(productNo, requestDto);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 카테고리 수정을 처리할 매핑.
     *
     * @param productNo  상품 번호
     * @param requestDto 카테고리 수정 정보
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/category")
    public String modifyProductCategory(@PathVariable("productNo") Long productNo,
                                        @ModelAttribute ModifyProductCategoryRequestDto requestDto) {
        productService.modifyProductCategory(productNo, requestDto);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 저자 수정을 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @param authors   저자 수정 정보
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/author")
    public String modifyProductAuthor(@PathVariable("productNo") Long productNo, @RequestParam String authors) {
        productService.modifyProductAuthor(productNo, authors);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 태그 수정을 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @param tagList   태그 수정 정보
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/tag")
    public String modifyProductTag(@PathVariable("productNo") Long productNo,
                                   @RequestParam(value = "tagList", required = false) List<Integer> tagList) {
        productService.modifyProductTag(productNo, tagList);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 유형 수정을 처리할 매핑.
     *
     * @param productNo   상품 번호
     * @param typeStateNo 상품 유형 수정 번호
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/type")
    public String modifyProductType(@PathVariable("productNo") Long productNo,
                                    @RequestParam Integer typeStateNo) {
        productService.modifyProductType(productNo, typeStateNo);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 판매 유형 수정을 처리할 매핑.
     *
     * @param productNo   상품 번호
     * @param saleStateNo 상품 판매 유형 수정 번호
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/sale")
    public String modifyProductSale(@PathVariable("productNo") Long productNo,
                                    @RequestParam Integer saleStateNo) {
        productService.modifyProductSale(productNo, saleStateNo);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 포인트 정책 수정을 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @param policyNo  포인트 정책 수정 번호
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/policy")
    public String modifyProductPolicy(@PathVariable("productNo") Long productNo,
                                      @RequestParam Integer policyNo) {
        productService.modifyProductPolicy(productNo, policyNo);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 설명 수정을 처리할 매핑.
     *
     * @param productNo          상품 번호
     * @param productDescription 수정할 상품 설명
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/description")
    public String modifyDescription(@PathVariable("productNo") Long productNo,
                                    @RequestParam String productDescription) {
        productService.modifyDescription(productNo, productDescription);

        return REDIRECT + "/" + productNo;
    }

    /**
     * E-Book 수정을 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @param eBook     변경할 E-Book
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/eBook")
    public String modifyEBook(@PathVariable("productNo") Long productNo,
                              MultipartFile eBook) {
        productService.modifyProductEBook(productNo, eBook);

        return REDIRECT + "/" + productNo;
    }

    /**
     * Image 수정을 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @param image     변경할 이미지
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/image")
    public String modifyImage(@PathVariable("productNo") Long productNo,
                              MultipartFile image) {
        productService.modifyProductImage(productNo, image);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상세 Image 수정을 처리할 매핑.
     *
     * @param productNo   상품 번호
     * @param detailImage 변경할 이미지
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/detail-image")
    public String modifyDetailImage(@PathVariable("productNo") Long productNo,
                                    MultipartFile detailImage) {
        productService.modifyProductDetailImage(productNo, detailImage);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 Image 추가하는 매핑.
     *
     * @param productNo 상품 번호
     * @param image     추가할 이미지
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/new-image")
    public String addImage(@PathVariable("productNo") Long productNo,
                           MultipartFile image) {
        productService.addNewImage(productNo, image);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 상품 상세 Image 추가하는 매핑.
     *
     * @param productNo   상품 번호
     * @param detailImage 추가할 상세 이미지
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/new-detail-image")
    public String addDetailImage(@PathVariable("productNo") Long productNo,
                                 MultipartFile detailImage) {
        productService.addNewDetailImage(productNo, detailImage);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 연관관계 상품 추가 매핑.
     *
     * @param productNo        상품 번호
     * @param relationProducts 연관관계 상품 번호들
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{productNo}/product-relation")
    public String addRelationProduct(@PathVariable("productNo") Long productNo,
                                     @RequestParam String relationProducts) {
        productService.addRelationProduct(productNo, relationProducts);

        return REDIRECT + "/" + productNo;
    }

    /**
     * 연관 상품을 삭제 매핑.
     *
     * @param parentNo 부모 상품 번호
     * @param childNo  자식 상품 번호
     * @return 상품 상세 페이지 Redirect
     */
    @Auth
    @PostMapping("/{parentNo}/cut/{childNo}")
    public String disconnectRelation(@PathVariable("parentNo") Long parentNo,
                                     @PathVariable("childNo") Long childNo) {
        productService.disconnectRelationProduct(childNo);

        return REDIRECT + "/" + parentNo;
    }
}
