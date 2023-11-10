package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Products;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void addProduct(Products products) {
        productRepository.save(products);
    }

    @Override
    public List<Products> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products getProduct(Long productId) {
        Products products = productRepository
                .findById(productId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong the get product co id"+productId));
        return products;
    }

    @Override
    public void updateProudct(Long productId, Products products) {
        productRepository
                .findById(productId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Khong the update produc co id: "+ productId));
        products.setProductId(productId);
        productRepository.save(products);
    }

    @Override
    public void updateProductStatus(Long productId, int status) {
        Products products = productRepository
                .findById(productId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "khong the update status product có id: "+ productId));
        products.setProductId(productId);
        products.setStatus(status);
        productRepository.save(products);
    }
}
