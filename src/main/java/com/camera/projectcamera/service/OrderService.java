package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Order;
import com.camera.projectcamera.model.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order addOrder(OrderRequest orderRequest);

    List<Order> getOrder();

    Order getOrderById(Long orderId);

    Order getOrderByCustomerId(Long customerId);
    OrderRequest getOrderCustomerId(Long customerId);
}
