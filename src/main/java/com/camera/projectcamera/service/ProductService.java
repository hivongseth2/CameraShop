package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Products;

import java.util.List;

public interface ProductService {
    void addProduct(Products products);

    List<Products> getProducts();

    Products getProduct(Long productId);

    void updateProudct(Long productId, Products products);

    void updateProductStatus(Long productId, int status);
}
