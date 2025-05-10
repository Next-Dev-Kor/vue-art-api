package com.vueart.api.repository.category;

import com.vueart.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 필요시 custom 메서드 작성 가능
    Optional<Category> findByCategoryName(String categoryName);
}

