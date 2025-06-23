package com.itsmerishab08.categoryservice.repository;


import com.itsmerishab08.categoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findBySalonIdAndDeletedAtIsNull(String salonId);

    Category findByIdAndDeletedAtIsNull(String id);

    List<Category> findAllByDeletedAtIsNull();
}
