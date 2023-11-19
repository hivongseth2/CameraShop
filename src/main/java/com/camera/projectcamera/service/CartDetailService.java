package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.CartItem;
import com.camera.projectcamera.model.request.UpdateCartRequest;
import org.springframework.stereotype.Service;

public interface CartDetailService{
    CartItem addCartItem(CartDetailService cartDetailService);
    CartItem addCartWithQuantity(UpdateCartRequest updateCartRequest);
}
