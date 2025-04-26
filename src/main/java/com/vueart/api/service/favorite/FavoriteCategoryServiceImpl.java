package com.vueart.api.service.favorite;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vueart.api.dto.request.favorite.AddFavoriteCategoryRequest;
import com.vueart.api.entity.FavoriteCategory;
import com.vueart.api.repository.favorite.FavoriteCategoryRepository;
import com.vueart.api.repository.category.CategoryRepository;
import com.vueart.api.repository.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteCategoryServiceImpl implements FavoriteCategoryService {

    private final FavoriteCategoryRepository favoriteCategoryRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void addFavoriteCategories(AddFavoriteCategoryRequest request) {
        Long userId = request.getUserId();
        List<Long> categoryIds = request.getCategoryIds();

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        for (Long categoryId : categoryIds) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

            if (favoriteCategoryRepository.existsByUserIdAndCategoryId(userId, categoryId)) {
                continue;
            }

            FavoriteCategory favoriteCategory = FavoriteCategory.builder()
                    .userId(userId)
                    .categoryId(categoryId)
                    .build();

            favoriteCategoryRepository.save(favoriteCategory);
        }
    }
}
