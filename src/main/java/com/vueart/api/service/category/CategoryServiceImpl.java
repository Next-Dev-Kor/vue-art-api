package com.vueart.api.service.category;

import com.vueart.api.dto.request.category.CategoryRequest;
import com.vueart.api.dto.response.category.CategoryResponse;
import com.vueart.api.entity.Category;
import com.vueart.api.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .categoryName(request.getCategoryNam:q
    e())
                .build();
        Category saved = categoryRepository.save(category);
        return new CategoryResponse(saved.getCategoryId(), saved.getCategoryName());
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponse(c.getCategoryId(), c.getCategoryName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        return new CategoryResponse(category.getCategoryId(), category.getCategoryName());
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
