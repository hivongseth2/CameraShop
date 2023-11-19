package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Cart;
import com.camera.projectcamera.entity.CartItem;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.model.request.CartRequest;
import com.camera.projectcamera.model.request.UpdateCartRequest;
import com.camera.projectcamera.repository.CartDetailRepository;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.BrandService;
import com.camera.projectcamera.service.CartService;
import com.camera.projectcamera.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CartDetailService implements com.camera.projectcamera.service.CartDetailService {


    private final CartDetailRepository cartDetailRepository;
    private final CartService cartService;

    @Override
    public CartItem addCartItem(com.camera.projectcamera.service.CartDetailService cartDetailService) {

        return null;
    }

    @Override
    public CartItem addCartWithQuantity(UpdateCartRequest updateCartRequest) {
        Optional<CartItem> ExistItem = cartDetailRepository.findCartItemByProduct(updateCartRequest.getCustomerId(), updateCartRequest.getProductId());


        if (ExistItem.isPresent()) {
            CartItem cartItemTemp = ExistItem.get();
            if (cartItemTemp.getCartItemId() != null) {
                try {

                    cartItemTemp.setQuantity(cartItemTemp.getQuantity() + updateCartRequest.getQuantity());
                    return cartDetailRepository.save(cartItemTemp);
                } catch (Exception e) {
                    return null;
                }
            }
            else{

                HashMap<Long,Integer> map= new HashMap<>();
                map.put(updateCartRequest.getProductId(), 1);
                CartRequest cartRequest = new CartRequest(updateCartRequest.getCustomerId(), new Date(), map);
                cartService.updateCart(cartRequest);

            }

        }
        HashMap<Long,Integer> map= new HashMap<>();
        System.out.println("o day"+updateCartRequest);
        map.put(updateCartRequest.getProductId(), 1);

        CartRequest cartRequest = new CartRequest(updateCartRequest.getCustomerId(), new Date(), map);
        cartService.updateCart(cartRequest);
        return null;

    }
}