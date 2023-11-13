package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartItem, Long> {
}
