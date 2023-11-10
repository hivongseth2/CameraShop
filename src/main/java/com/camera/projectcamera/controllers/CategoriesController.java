package com.camera.projectcamera.controllers;

import com.camera.projectcamera.dto.CategoriesDTO;
import com.camera.projectcamera.entity.Categories;
import com.camera.projectcamera.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/add")
    public String addCategories(@RequestBody Categories categories){
        categoriesService.addCategories(categories);
        return  "success add categories";
    }

    @GetMapping
    public List<Categories>  getCategories(){
        return categoriesService.getCategories();
    }

    @GetMapping("/get")
    public Categories getCategory(@RequestParam Long categoryId){
        return  categoriesService.getCategory(categoryId);
    }

    @PutMapping("update/{categoryId}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long categoryId, @RequestBody Categories categories){
        categoriesService.updateCategory(categoryId, categories);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        categoriesService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("update-name/{categoryId}")
    public ResponseEntity<Void> updateNameCategory(@PathVariable Long categoryId, @RequestBody CategoriesDTO categoriesDTO){
        categoriesService.updateNameCategory(categoryId, categoriesDTO);
        return  ResponseEntity.noContent().build();
    }

}
