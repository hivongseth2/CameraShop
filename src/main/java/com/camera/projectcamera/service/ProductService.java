package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Products;
import com.camera.projectcamera.model.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    Products addProduct(Products products);

    List<ProductRequest> getProducts();

    ProductRequest getProduct(Long productId);

    void updateProudct(Long productId, Products products);

    void updateProductStatus(Long productId, int status);
}