package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Brands;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/add")
    public ResponseEntity<?> addBrand(@RequestBody Brands brands){
        brandService.addBrand(brands);
        if(brands==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create error"));
        }
        return ResponseEntity.ok(brands);
    }

    @GetMapping
    public List<Brands> getBrands(){
        return brandService.getBrands();
    }

    @GetMapping("/get")
    public Brands getBrand(@RequestParam Long brandId){
        return brandService.getBrand(brandId);
    }
    @PutMapping("update/{brandId}")
    public ResponseEntity<Void> updateBrand(@PathVariable Long brandId, @RequestBody Brands brands){
        brandService.updateBrand(brandId, brands);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("delete/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long brandId){
        brandService.deleteBrand(brandId);
        return ResponseEntity.noContent().build();
    }
}
