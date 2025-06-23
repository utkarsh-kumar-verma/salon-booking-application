package com.itsmerishab08.categoryservice.controller;

import com.itsmerishab08.categoryservice.request.CategoryRequest;
import com.itsmerishab08.categoryservice.response.BaseResponse;
import com.itsmerishab08.categoryservice.response.CategoryResponse;
import com.itsmerishab08.categoryservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add_update")
    public BaseResponse addUpdateCategory(@RequestBody @Valid CategoryRequest category) {
        return categoryService.createUpdateCategory(category);
    }

    @GetMapping("/get_by_id")
    public CategoryResponse getCategoryById(@RequestParam("id") String id) {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(id);
        return categoryService.getCategoryById(categoryRequest);
    }

    @PostMapping("/delete_by_id")
    public BaseResponse deleteCategoryById(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.deleteCategory(categoryRequest);
    }

    @GetMapping("/get_by_salon_id")
    public CategoryResponse getCategoryBySalonId(@RequestParam("salon_id") String salonId) {
        return categoryService.getCategoryBySalonId(salonId);
    }
}
