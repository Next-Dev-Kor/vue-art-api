package com.vueart.api.controller;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.favorite.AddFavoriteCategoryRequest;
import com.vueart.api.service.favorite.FavoriteCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites/categories")
public class FavoriteCategoryController {

    private final FavoriteCategoryService favoriteCategoryService;

    @PostMapping("/add")
    public SuccessResponse addFavoriteCategories(@RequestBody AddFavoriteCategoryRequest request) {
        favoriteCategoryService.addFavoriteCategories(request);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS_ADD_FAVORITE.getMessage());
    }
}
