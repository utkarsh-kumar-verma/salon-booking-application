package com.itsmerishab08.categoryservice.service;

import com.itsmerishab08.categoryservice.request.CategoryRequest;
import com.itsmerishab08.categoryservice.response.BaseResponse;
import com.itsmerishab08.categoryservice.response.CategoryResponse;

public interface CategoryService {

    BaseResponse createUpdateCategory(CategoryRequest request);
    BaseResponse deleteCategory(CategoryRequest request);
    CategoryResponse getCategoryById(CategoryRequest request);
    CategoryResponse getCategoryBySalonId(String name);
}
