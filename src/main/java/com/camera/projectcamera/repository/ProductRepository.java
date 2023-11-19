package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
