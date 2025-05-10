package com.vueart.api.controller;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.category.CategoryRequest;
import com.vueart.api.dto.request.favorite.AddFavoriteCategoryRequest;
import com.vueart.api.dto.response.category.CategoryResponse;
import com.vueart.api.entity.FavoriteCategory;
import com.vueart.api.service.favorite.FavoriteCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.webauthn.management.MapUserCredentialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CategoryResponse> getFavoriteCategoryById(@PathVariable Long userId) {
        return  favoriteCategoryService.getFavoriteCategoryByUserId(userId);
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
