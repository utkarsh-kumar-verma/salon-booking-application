package com.itsmerishab08.categoryservice.service.impl;

import com.itsmerishab08.categoryservice.dao.CategoryServiceDAO;
import com.itsmerishab08.categoryservice.entity.Category;
import com.itsmerishab08.categoryservice.model.CategoryModel;
import com.itsmerishab08.categoryservice.request.CategoryRequest;
import com.itsmerishab08.categoryservice.response.BaseResponse;
import com.itsmerishab08.categoryservice.response.CategoryResponse;
import com.itsmerishab08.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryServiceDAO categoryServiceDAO;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public BaseResponse createUpdateCategory(CategoryRequest request) {
        BaseResponse response = new BaseResponse();
        try{
            if(request.getId() != null)
            {
                Category category = categoryServiceDAO.findById(request.getId());
                if(category == null)
                    throw new Exception("Category not found with id " + request.getId());
                category.setName(request.getName());
                category.setDescription(request.getDescription());
                category.setImage(request.getImage());
                category.setUpdatedAt(new Date());
                categoryServiceDAO.save(category);
                response.setMessage("Category Updated successfully");
            }else{
                Category category = new Category();
                category.setName(request.getName());
                category.setDescription(request.getDescription());
                category.setImage(request.getImage());
                category.setSalonId(request.getSalonId());
                category.setUpdatedAt(new Date());
                category.setCreatedAt(new Date());
                categoryServiceDAO.save(category);
                response.setMessage("Category Created successfully");
            }
            response.setStatus(HttpStatus.OK.toString());
        }
        catch(Exception e){
           logger.error("Error in creating/updating category {}", e.getMessage());
           response.setMessage("Error in creating/updating category "+e.getMessage());
           response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }
        return response;
    }

    @Override
    public BaseResponse deleteCategory(CategoryRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            Category category = categoryServiceDAO.findById(request.getId());
            if(category == null)
                throw new Exception("Category not found with id " + request.getId());
            category.setDeletedAt(new Date());
            categoryServiceDAO.save(category);
            response.setMessage("Category Deleted successfully");
            response.setStatus(HttpStatus.OK.toString());

        } catch (Exception e) {
            logger.error("Error in deleting category {}", e.getMessage());
            response.setMessage("Error in deleting category "+e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }
        return response;
    }

    @Override
    public CategoryResponse getCategoryById(CategoryRequest request) {
        CategoryResponse response = new CategoryResponse();
        try{
            Category category = categoryServiceDAO.findById(request.getId());
            if(category == null)
                throw new Exception("Category not found with id " + request.getId());

            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(category.getId());
            categoryModel.setName(category.getName());
            categoryModel.setDescription(category.getDescription());
            categoryModel.setImage(category.getImage());
            categoryModel.setSalonId(category.getSalonId());
            response.setCategory(categoryModel);
            response.setStatus(HttpStatus.OK.toString());
        } catch (Exception e) {
            logger.error("Error in fetching the category {}", e.getMessage());
            response.setMessage("Error in fetching category "+e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }

        return response;
    }

    @Override
    public CategoryResponse getCategoryBySalonId(String salonId) {
        CategoryResponse response = new CategoryResponse();
        try {
            if (salonId == null || salonId.isBlank()) {
                throw new Exception("Salon ID must be provided");
            }

            List<Category> categories = categoryServiceDAO.findBySalonId(salonId);

            if (categories == null || categories.isEmpty()) {
                throw new Exception("No categories found for salon ID: " + salonId);
            }

            List<CategoryModel> categoryModels = new ArrayList<>();
            for (Category category : categories) {
                CategoryModel model = new CategoryModel();
                model.setId(category.getId());
                model.setName(category.getName());
                model.setDescription(category.getDescription());
                model.setImage(category.getImage());
                model.setSalonId(category.getSalonId());
                categoryModels.add(model);
            }

            response.setCategoryModelList(categoryModels);

            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("Categories fetched successfully for salon ID: " + salonId);

        } catch (Exception e) {
            logger.error("Error in fetching categories by salon ID: {}", e.getMessage());
            response.setMessage("Error in fetching categories: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }

        return response;
    }

}
