package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.dto.CategoriesDTO;
import com.camera.projectcamera.entity.Categories;
import com.camera.projectcamera.repository.CategoriesRepository;
import com.camera.projectcamera.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;


    @Override
    public void addCategories(Categories categories) {
        categoriesRepository.save(categories);
    }

    @Override
    public List<Categories> getCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategory(Long id) {
        Categories categories = categoriesRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Mặt hàng không được tìm thấy với id" + id));
        return categories;
    }

    @Override
    public void updateCategory(Long id, Categories categories) {
        categoriesRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + id));

        categories.setCategoryId(id);
        categoriesRepository.save(categories);

    }

    @Override
    public void deleteCategory(Long id) {
        Categories categories = categoriesRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + id));

        categoriesRepository.delete(categories);
    }

    @Override
    public void updateNameCategory(Long id, CategoriesDTO categoriesDTO) {
        Categories categories = categoriesRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + id));

        categories.setName(categoriesDTO.getName());

        categoriesRepository.save(categories);
    }
}
