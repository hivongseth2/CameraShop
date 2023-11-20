package com.camera.projectcamera.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private double price;
    private int quantity;
    private Long orderDetailId;
    private Long orderId;
    private Long productId;
    private String productName;
    private List<String> productImages;
}
