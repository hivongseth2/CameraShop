package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
