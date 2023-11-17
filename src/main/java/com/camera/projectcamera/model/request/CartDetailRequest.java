package com.camera.projectcamera.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailRequest {
    private double price;
    private int quantity;
    private Long CartId;
    private Long CartDetailId;
    private Long productId;
}
