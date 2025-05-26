package com.vueart.api.service.favorite;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.dto.response.category.CategoryResponse;

import java.util.List;

public interface FavoriteCategoryService {
    SuccessResponse addFavoriteCategory(Long userId, Long categoryId);
    SuccessResponse addFavoriteCategories(Long userId, List<Long> categoryIds);
    List<CategoryResponse> getFavoriteCategoryByUserId(Long userId);
    void deleteByUserIdAndCategoryId(Long userId, Long categoryId);

}
