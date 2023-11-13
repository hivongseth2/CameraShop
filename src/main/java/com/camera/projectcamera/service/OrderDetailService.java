package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.OrderDetail;
import com.camera.projectcamera.model.request.OrderDetailRequest;

public interface OrderDetailService {
    OrderDetail addOrderDetail(OrderDetailRequest orderDetailRequest);
}
