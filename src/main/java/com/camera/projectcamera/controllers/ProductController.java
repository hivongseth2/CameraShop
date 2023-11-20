package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Products;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.ProductRequest;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Products products) {
        Products theProduct = productService.addProduct(products);
        if (theProduct == null) {
            return ResponseEntity.badRequest().body(new MessageError(400, "Create error"));
        }
        return ResponseEntity.ok(theProduct);
    }

    //@PostMapping("/add")
//public void addProduct(@RequestBody Products products){
//    Products theProduct = productService.addProduct(products);
//
////    return ResponseEntity.ok (theProduct);
//
//}
    @GetMapping
    public List<ProductRequest> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/get")
    public ProductRequest getProduct(@RequestParam Long productId) {

        return productService.getProduct(productId);
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @RequestBody Products products) {
        productService.updateProudct(productId, products);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("updateStatus/{productId}/{status}")
    public ResponseEntity<Void> updateProductStatus(
            @PathVariable Long productId,
            @PathVariable int status
    ) {
        Optional<Products> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setStatus(status);
            productRepository.save(product);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//
//    @GetMapping("/productsByCustomerId")
//    public ResponseEntity<Map<String, Integer>> getProductDetailsByCustomerId(@RequestParam("personId") Long personId) {
//        Map<String, Integer> productDetails = productService.getProductDetailsByCustomerId(personId);
//        return ResponseEntity.ok(productDetails);
//    }

}