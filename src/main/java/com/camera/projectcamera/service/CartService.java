package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Cart;
import com.camera.projectcamera.model.request.CartRequest;

public interface CartService {
    Cart addCart(CartRequest cartRequest);

    Cart getCartById(Long cartId);
}
