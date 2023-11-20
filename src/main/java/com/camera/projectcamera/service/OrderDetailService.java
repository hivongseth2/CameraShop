package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.OrderDetail;
import com.camera.projectcamera.model.request.OrderDetailRequest;

import java.util.List;

public interface OrderDetailService {
    OrderDetail addOrderDetail(OrderDetailRequest orderDetailRequest);

    List<OrderDetail> getOrderDetailsByCustomerId(Long customerId);
}
