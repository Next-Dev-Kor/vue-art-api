package com.vueart.api.repository.favorite;

import com.vueart.api.entity.FavoriteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCategoryRepository extends JpaRepository<FavoriteCategory, Long> {
    boolean existsByUserIdAndCategoryId(Long userId, Long categoryId);
}