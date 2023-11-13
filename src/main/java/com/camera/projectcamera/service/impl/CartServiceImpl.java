package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Cart;
import com.camera.projectcamera.entity.CartItem;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Products;
import com.camera.projectcamera.model.request.CartRequest;
import com.camera.projectcamera.repository.CartDetailRepository;
import com.camera.projectcamera.repository.CartRepository;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.CartDetailService;
import com.camera.projectcamera.service.CartService;
import com.camera.projectcamera.service.CustomerService;
import com.camera.projectcamera.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    @Override
    public Cart addCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        try {
            Customer customer = new Customer();
            try {
                customer = customerService.getCustomerById(cartRequest.getCustomerId());
                cart.setCustomer(customer);
            } catch (Exception e) {
                cart.setCustomer(null);
            }
            cart.setLastUpdate(cartRequest.getLastUpdate());
            Cart theCart = cartRepository.save(cart);

            // Check if cartDetails is not null before using it
            HashMap<Long, Integer> listCartDetail = cartRequest.getCartDetails();
            if (listCartDetail != null) {
                listCartDetail.forEach((k, v) -> {
                    Optional<Products> productsOptional = productRepository.findById(k);
                    if (productsOptional.isPresent()) {
                        Products product = productsOptional.get();
                        double price = product.getPrice() * v;
                        CartItem cartItem = new CartItem();
                        cartItem.setPrice(price);
                        cartItem.setCart(theCart);
                        cartItem.setProduct(product);
                        cartItem.setQuantity(v);
                        cartDetailRepository.save(cartItem);
                    }
                });
            }

            return theCart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Cart getCartById(Long cartId) {
        Cart cart = cartRepository
                .findById(cartId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy giỏ hàng: "+ cartId));
        return  cart;
    }
}
