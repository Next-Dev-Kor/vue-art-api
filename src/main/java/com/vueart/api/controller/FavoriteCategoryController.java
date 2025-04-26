package com.vueart.api.controller;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.favorite.AddFavoriteCategoryRequest;
import com.vueart.api.service.favorite.FavoriteCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites/categories")
@Tag(name="Favorite Category")
public class FavoriteCategoryController {

    private final FavoriteCategoryService favoriteCategoryService;

    @PostMapping("/add")
    public SuccessResponse addFavoriteCategories(@RequestBody AddFavoriteCategoryRequest request) {
        favoriteCategoryService.addFavoriteCategories(request);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }
}
