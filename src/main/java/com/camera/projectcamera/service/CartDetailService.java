package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.CartItem;
import com.camera.projectcamera.model.request.UpdateCartRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartDetailService{
    CartItem addCartItem(CartDetailService cartDetailService);
    CartItem addCartWithQuantity(UpdateCartRequest updateCartRequest);

    List<CartItem> getCartItemsByCustomerId(Long customerId);

    void deleteCartItemById(Long cartDetailId);
}
