package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Brands;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BrandService {
    void addBrand(Brands brands);

    List<Brands> getBrands();

    Brands getBrand(Long brandId);

    void updateBrand(Long brandId, Brands brands);

    void deleteBrand(Long brandId);
}
