package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Products;
import com.camera.projectcamera.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    private String addProduct(@RequestBody Products products){
        productService.addProduct(products);
        return "success add product";
    }
    @GetMapping
    public List<Products> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/get")
    public Products getProduct(@RequestParam Long productId){
        return productService.getProduct(productId);
    }
    @PutMapping("update/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,@RequestBody Products products){
        productService.updateProudct(productId, products);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("updateStatus/{productId}/{status}")
    public ResponseEntity<Void> updateProductStatus(
            @PathVariable Long productId,
            @PathVariable int status
    ) {
        productService.updateProductStatus(productId, status);
        return ResponseEntity.noContent().build();
    }
}
