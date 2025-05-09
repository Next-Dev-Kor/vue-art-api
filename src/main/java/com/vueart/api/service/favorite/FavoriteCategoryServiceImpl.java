package com.vueart.api.service.favorite;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.core.exception.VueArtApiException;
import com.vueart.api.dto.response.category.CategoryResponse;

import com.vueart.api.entity.Category;
import com.vueart.api.entity.User;
import io.micrometer.common.KeyValues;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vueart.api.entity.FavoriteCategory;
import com.vueart.api.repository.favorite.FavoriteCategoryRepository;
import com.vueart.api.repository.category.CategoryRepository;
import com.vueart.api.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteCategoryServiceImpl implements FavoriteCategoryService {

    private final FavoriteCategoryRepository favoriteCategoryRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public SuccessResponse addFavoriteCategory(Long userId, Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        boolean exists = favoriteCategoryRepository.findByUserAndCategory(user, category).isPresent();
        if (!exists) {

            FavoriteCategory favoriteCategory = FavoriteCategory.builder()
                    .user(user)
                    .category(category)
                    .build();

            favoriteCategoryRepository.save(favoriteCategory);
        } else {
            throw new IllegalStateException("이미 추가되어 있습니다");
        }

        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    public SuccessResponse addFavoriteCategories(Long userId, List<Long> categoryIds) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Category> categories = categoryRepository.findAllById(categoryIds);

        List<FavoriteCategory> favoriteCategoriesToSave = new ArrayList<>();

        for (Category category : categories) {
            if (favoriteCategoryRepository.findByUserAndCategory(user, category).isEmpty()) {
                FavoriteCategory favoriteCategory = FavoriteCategory.builder()
                        .user(user)
                        .category(category)
                        .build();
                favoriteCategoriesToSave.add(favoriteCategory);

            }
        }
        favoriteCategoryRepository.saveAll(favoriteCategoriesToSave);

        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @Transactional
    @Override
    public List<CategoryResponse> getFavoriteCategoryByUserId(Long userId) {
        List<FavoriteCategory> favoriteCategories = favoriteCategoryRepository.getFavoriteCategoryByUserId(userId);
        return favoriteCategories.stream()
                .map(favoriteCategory -> new CategoryResponse(
                        favoriteCategory.getCategory().getCategoryId(),
                        favoriteCategory.getCategory().getCategoryName()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByUserIdAndCategoryId(Long userId, Long categoryId) {

        boolean exists = favoriteCategoryRepository.findByUser_IdAndCategory_CategoryId(userId, categoryId)
                .isPresent();

        if (!exists) {
            throw new VueArtApiException(Code.ErrorCode.NOT_FOUND_FAVORITE_CATEGORY);
        }

        favoriteCategoryRepository.deleteByUserIdAndCategoryId(userId, categoryId);
    }
}
