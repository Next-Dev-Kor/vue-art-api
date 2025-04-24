package com.vueart.api.service.favorite;


import com.vueart.api.dto.request.favorite.AddFavoriteCategoryRequest;

public interface FavoriteCategoryService {
    void addFavoriteCategories(AddFavoriteCategoryRequest request);
}
