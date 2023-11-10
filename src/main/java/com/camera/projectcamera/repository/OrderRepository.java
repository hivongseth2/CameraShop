package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
