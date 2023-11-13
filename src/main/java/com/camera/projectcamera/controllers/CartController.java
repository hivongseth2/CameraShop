package com.camera.projectcamera.controllers;


import com.camera.projectcamera.entity.Cart;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.CartRequest;
import com.camera.projectcamera.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?>addCart(@RequestBody CartRequest cartRequest){
        Cart cart = cartService.addCart(cartRequest);
        if(cart == null){
            return ResponseEntity.badRequest().body(new MessageError(400, "create cart failed"));
        }
        return ResponseEntity.ok(cart);
    }
    @GetMapping("/get")
    public Cart getCartById(Long cartId){
        return cartService.getCartById(cartId);
    }
}
