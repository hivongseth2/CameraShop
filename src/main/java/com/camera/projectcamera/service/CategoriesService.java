package com.camera.projectcamera.service;

import com.camera.projectcamera.dto.CategoriesDTO;
import com.camera.projectcamera.entity.Categories;

import java.util.List;

public interface CategoriesService {
    void addCategories(Categories categories);

    List<Categories> getCategories();

    Categories getCategory(Long id);

    void updateCategory(Long categoryId, Categories categories);

    void deleteCategory(Long categoryId);

    void updateNameCategory(Long categoryId, CategoriesDTO categoriesDTO);
}
