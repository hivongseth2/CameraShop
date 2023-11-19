package com.camera.projectcamera.controllers;


import com.camera.projectcamera.entity.Cart;
import com.camera.projectcamera.entity.CartItem;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.CartDetailRequest;
import com.camera.projectcamera.model.request.CartRequest;
import com.camera.projectcamera.model.request.UpdateCartRequest;
import com.camera.projectcamera.service.CartDetailService;
import com.camera.projectcamera.service.CartService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartDetailService cartDetailService;

    @PostMapping("/add")
    public ResponseEntity<?>addCart(@RequestBody CartRequest cartRequest){

        System.out.println("cart rq nè "+ cartRequest);
        Cart cart = cartService.addCart(cartRequest);
        if(cart == null){
            return ResponseEntity.badRequest().body(new MessageError(400, "create cart failed"));
        }
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody CartRequest cartRequest) {
        Cart updatedCart = cartService.updateCart(cartRequest);

        if (updatedCart != null) {
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get")
    public Cart getCartById(Long cartId){
        return cartService.getCartById(cartId);
    }

    @PutMapping("/updateCartItem")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartRequest updateCartRequest) {
        CartItem updatedCart = cartDetailService.addCartWithQuantity(updateCartRequest);

        if (updatedCart != null) {
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCart")
    public Cart getCartByIdCustomer(Long customerId){
        return cartService.getCartByCustomerId(customerId);
    }
    @GetMapping("/getCartItems")
    public ResponseEntity<List<CartDetailRequest>> getCartItemsByCustomerId(@RequestParam Long customerId) {
        try {
            Cart cart = cartService.getCartByCustomerId(customerId);
            if (cart != null) {
                List<CartItem> cartItems = cart.getCartItems();
                List<CartDetailRequest> cartDetailResponses = cartItems.stream()
                        .map(cartItem -> new CartDetailRequest(
                                cartItem.getPrice(),
                                cartItem.getQuantity(),
                                cartItem.getCart().getCartId(),  // Fix: Corrected the syntax here
                                cartItem.getCartItemId(),     // Fix: Assuming you have a method getCartDetailId in CartItem
                                cartItem.getProduct().getProductId() // Fix: Assuming you have a method getProductId in Product
                        ))
                        .collect(Collectors.toList());

                return ResponseEntity.ok(cartDetailResponses);
            } else {
                // If the cart is not found, return a 404 status code
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exceptions if present
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}