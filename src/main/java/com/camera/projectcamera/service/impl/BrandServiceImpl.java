package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Brands;
import com.camera.projectcamera.repository.BrandRepository;
import com.camera.projectcamera.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public void addBrand(Brands brands) {
        if(brands.getImage()==null) {
            throw new RuntimeException("please input full info!");
        }

        brandRepository.save(brands);
    }

    @Override
    public List<Brands> getBrands() {

        return brandRepository.findAll();
    }

    @Override
    public Brands getBrand(Long brandId) {
        Brands brand = brandRepository
                .findById(brandId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nhãn hàng không được tìm thấy với id"+ brandId));
        return brand;
    }

    @Override
    public void updateBrand(Long brandId, Brands brands) {
        brandRepository
                .findById(brandId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong th update brand cos id"+ brandId));
        brands.setBrandId(brandId);
        brandRepository.save(brands);
    }

    @Override
    public void deleteBrand(Long brandId) {
        Brands brands = brandRepository
                .findById(brandId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "khong the xoa brand có id"+ brandId));
        brandRepository.delete(brands);
    }
}
