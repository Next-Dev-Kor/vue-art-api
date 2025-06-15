package com.vueart.api.controller;

import com.vueart.api.common.response.CommonApiResponse;
import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import org.springframework.http.HttpStatus;
import com.vueart.api.dto.response.category.CategoryResponse;
import com.vueart.api.service.favorite.FavoriteCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorite/category")
@Tag(name="Favorite Category")
public class FavoriteCategoryController {

    private final FavoriteCategoryService favoriteCategoryService;

    @Operation(
            summary = "즐겨찾기 추가"
    )
    @GetMapping("/{userId}/{categoryId}")
    public SuccessResponse addFavorite(@PathVariable Long userId, @PathVariable Long categoryId) {
        return favoriteCategoryService.addFavoriteCategory(userId, categoryId);

    }

    @Operation(
            summary = "즐겨찾기 추가"
    )
    @PostMapping("/{userId}")
    public SuccessResponse addFavorites(@PathVariable Long userId, @RequestBody List<Long> categoryIds) {
        return favoriteCategoryService.addFavoriteCategories(userId, categoryIds);


    }

    @Operation(
            summary = "사용자의 즐겨찾기 항목 조회"
    )
    @GetMapping("/{userId}")
    public CommonApiResponse<List<CategoryResponse>> getFavoriteCategoryById(@PathVariable Long userId) {
        List<CategoryResponse> categoryResponse = favoriteCategoryService.getFavoriteCategoryByUserId(userId);
        return new CommonApiResponse<>(HttpStatus.OK.value(), Code.ApiResponseCode.SUCCESS.getCode(), categoryResponse);
    }

    @Operation(
            summary = "즐겨찾기 삭제"
    )
    @DeleteMapping("/{userId}/{categoryId}")
    public SuccessResponse deleteFavoriteCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        favoriteCategoryService.deleteByUserIdAndCategoryId(userId, categoryId);
        return new SuccessResponse("즐겨찾는 카테고리가 삭제되었습니다.");
    }
}
