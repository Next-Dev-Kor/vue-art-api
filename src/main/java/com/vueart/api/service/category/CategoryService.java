package com.vueart.api.service.category;

import com.vueart.api.dto.request.category.CategoryRequest;
import com.vueart.api.dto.response.category.CategoryResponse;
import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    void deleteCategory(Long id);
}
