package com.vueart.api.repository.category;

import com.vueart.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 필요시 custom 메서드 작성 가능
}

