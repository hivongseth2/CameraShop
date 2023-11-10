package com.camera.projectcamera.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private double price;
    private int quantity;
    private Long orderDetailId;
    private Long orderId;
    private Long productId;
}
