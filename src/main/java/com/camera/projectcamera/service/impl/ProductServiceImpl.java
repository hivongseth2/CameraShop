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
        Brands brand = brandService.getBrand(products.getBrand().getBrandId());
        Categories cate = categoriesService.getCategory(products.getCategory().getCategoryId());
        products.setBrand(brand);
        products.setCategory(cate);
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

        // Chuyển đổi từ Products sang ProductDTO
        return convertToProductDTO(product);
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

}
