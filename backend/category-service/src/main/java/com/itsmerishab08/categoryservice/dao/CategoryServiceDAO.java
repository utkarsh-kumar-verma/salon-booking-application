package com.itsmerishab08.categoryservice.dao;

import com.itsmerishab08.categoryservice.entity.Category;
import com.itsmerishab08.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceDAO {

    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAllByDeletedAtIsNull();
    }

    public Category findById(String id) {
        return categoryRepository.findByIdAndDeletedAtIsNull(id);
    }

    public List<Category> findBySalonId(String salonId) {
        return categoryRepository.findBySalonIdAndDeletedAtIsNull(salonId);
    }

}
