
package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.*;
import com.camera.projectcamera.model.request.ImageProductRequest;
import com.camera.projectcamera.model.request.ProductRequest;
import com.camera.projectcamera.model.request.PropertiesRequest;
import com.camera.projectcamera.repository.BrandRepository;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.BrandService;
import com.camera.projectcamera.service.CategoriesService;
import com.camera.projectcamera.service.ProductService;
import com.camera.projectcamera.service.PropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoriesService categoriesService;
    @Override
    public Products addProduct(Products products) {
        if (products.getBrand() == null || products.getBrand().getBrandId() == null) {
            // Handle the case where the brand is not set
            throw new IllegalArgumentException("Brand must be set in the product.");
        }

        Brands brand = brandService.getBrand(products.getBrand().getBrandId());

        if (brand == null) {
            // Handle the case where the brand is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found with ID " + products.getBrand().getBrandId());
        }

        if (products.getCategory() == null || products.getCategory().getCategoryId() == null) {
            // Handle the case where the category is not set
            throw new IllegalArgumentException("Category must be set in the product.");
        }

        Categories category = categoriesService.getCategory(products.getCategory().getCategoryId());

        if (category == null) {
            // Handle the case where the category is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID " + products.getCategory().getCategoryId());
        }

        products.setBrand(brand);
        products.setCategory(category);

        return productRepository.save(products);
    }


    @Override
    public List<ProductRequest> getProducts() {
        List<Products> products = productRepository.findAll();
        List<ProductRequest> productDTOs = new ArrayList<>();

        for (Products product : products) {
            ProductRequest productDTO = new ProductRequest();
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setModelYear(product.getModelYear());
            productDTO.setYearOfManual(product.getYearOfManual());
            productDTO.setStatus(product.getStatus());
            productDTO.setPrice(product.getPrice());
            if (product.getCategory() != null) {
                productDTO.setCategoryName(product.getCategory().getName());
            }

            if (product.getBrand() != null) {
                productDTO.setBrandName(product.getBrand().getName());
            }
            List<ImageProductRequest> imageRequests = new ArrayList<>();
            for (Images image : product.getImages()) {
                ImageProductRequest imageRequest = new ImageProductRequest();
                imageRequest.setImageId(image.getImageId());
                imageRequest.setId(image.getId());
                imageRequests.add(imageRequest);
            }
            productDTO.setImages(imageRequests);

            List<PropertiesRequest> propertiesRequests = new ArrayList<>();
            for (Properties property : product.getProperties()) {
                PropertiesRequest propertyRequest = new PropertiesRequest();
                propertyRequest.setPropertyId(property.getPropertyId());
                propertyRequest.setName(property.getName());
                propertyRequest.setValue(property.getValue());
                propertiesRequests.add(propertyRequest);
            }
            productDTO.setProperties(propertiesRequests);


            productDTOs.add(productDTO);
        }

        return productDTOs;
    }


    @Override
    public ProductRequest getProduct(Long productId) {
        Products product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể tìm thấy sản phẩm có ID: " + productId));

        return convertToProductRequest(product);
    }

    private ProductRequest convertToProductDTO(Products product) {
        // Thực hiện chuyển đổi từ Products sang ProductDTO
        ProductRequest productDTO = new ProductRequest();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setModelYear(product.getModelYear());
        productDTO.setYearOfManual(product.getYearOfManual());
        productDTO.setStatus(product.getStatus());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryName(product.getCategory().getName());
        productDTO.setBrandName(product.getBrand().getName());

        List<ImageProductRequest> imageProductRequests = new ArrayList<>();
        for (Images image : product.getImages()) {
            ImageProductRequest imageProductRequest = new ImageProductRequest();
            imageProductRequest.setImageId(image.getImageId());
            imageProductRequest.setId(image.getId());
            imageProductRequests.add(imageProductRequest);
        }
        productDTO.setImages(imageProductRequests);

        List<PropertiesRequest> propertiesRequests = new ArrayList<>();
        for (Properties property : product.getProperties()) {
            PropertiesRequest propertyRequest = new PropertiesRequest();
            propertyRequest.setPropertyId(property.getPropertyId());
            propertyRequest.setName(property.getName());
            propertyRequest.setValue(property.getValue());
            propertiesRequests.add(propertyRequest);
        }
        productDTO.setProperties(propertiesRequests);

        return productDTO;
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
        Products product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể tìm thấy sản phẩm có ID: " + productId));
        product.setStatus(status);
        productRepository.save(product);
    }

    public ProductRequest convertToProductRequest(Products product) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId(product.getProductId());
        productRequest.setName(product.getName());
        productRequest.setModelYear(product.getModelYear());

        productRequest.setYearOfManual(product.getYearOfManual());
        productRequest.setStatus(product.getStatus());
        productRequest.setPrice(product.getPrice());
        productRequest.setCategoryName(product.getCategory().getName());
        productRequest.setBrandName(product.getBrand().getName());
        productRequest.setQuantity(product.getQuantity());

        List<PropertiesRequest> propertiesRequests = convertToPropertiesRequestList(product.getProperties());
        List<ImageProductRequest> imageProductRequests = convertToImageProductRequestList(product.getImages());
        productRequest.setProperties(propertiesRequests);
        productRequest.setImages(imageProductRequests);


        return productRequest;
    }
    private List<PropertiesRequest> convertToPropertiesRequestList(List<Properties> properties) {
        List<PropertiesRequest> propertiesRequests = new ArrayList<>();

        for (Properties property : properties) {
            PropertiesRequest propertiesRequest = new PropertiesRequest();
            propertiesRequest.setPropertyId(property.getPropertyId());
            propertiesRequest.setName(property.getName());
            propertiesRequest.setValue(property.getValue());
            propertiesRequests.add(propertiesRequest);
        }
        return propertiesRequests;
    }

    private List<ImageProductRequest> convertToImageProductRequestList(List<Images> imageProducts) {
        List<ImageProductRequest> imageProductRequests = new ArrayList<>();

        for (Images imageProduct : imageProducts) {
            ImageProductRequest imageProductRequest = new ImageProductRequest();
            imageProductRequest.setImageId(imageProduct.getImageId());
            imageProductRequest.setId(imageProduct.getId());
            imageProductRequests.add(imageProductRequest);
        }
        return imageProductRequests;
    }
}