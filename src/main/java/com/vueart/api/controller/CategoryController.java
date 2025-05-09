package com.vueart.api.controller;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.category.CategoryRequest;
import com.vueart.api.dto.response.category.CategoryResponse;
import com.vueart.api.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = "Category", description="카테고리")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(
            summary = "신규 카테고리 등록",
            description = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "카테고리 등록이 완료되었습니다.",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못 되었습니다."),
            @ApiResponse(responseCode = "409", description = "이미 등록된 카테고리입니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public SuccessResponse createCategory(@RequestBody CategoryRequest request) {
        categoryService.createCategory(request);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
