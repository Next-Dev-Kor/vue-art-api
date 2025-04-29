package com.vueart.api.repository.favorite;

import com.vueart.api.entity.Category;
import com.vueart.api.entity.FavoriteCategory;
import com.vueart.api.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteCategoryRepository extends JpaRepository<FavoriteCategory, Long> {

    Optional<FavoriteCategory> findByUserAndCategory(User user, Category category);

    List<FavoriteCategory> getFavoriteCategoryByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FavoriteCategory fc WHERE fc.user.id = :userId AND fc.category.categoryId = :categoryId")
    void deleteByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);


}

