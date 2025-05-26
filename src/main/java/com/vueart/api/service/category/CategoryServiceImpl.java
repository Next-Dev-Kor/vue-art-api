package com.vueart.api.service.category;

import com.vueart.api.core.enums.Code;
import com.vueart.api.core.exception.VueArtApiException;
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
    public void createCategory(CategoryRequest request) {
        if (request.categoryName().isEmpty() ) {
            throw new VueArtApiException(Code.ErrorCode.INVALID_INPUT_VALUE);
        }

        if (categoryRepository.findByCategoryName(request.categoryName()).isPresent()) {
            throw new VueArtApiException(Code.ErrorCode.ALREADY_REGISTERED_USER);
        }

        Category category = Category.builder()
                .categoryName(request.categoryName())
                .build();
        categoryRepository.save(category);

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
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 카테고리입니다.");
        }
        categoryRepository.deleteById(id);
    }
}
