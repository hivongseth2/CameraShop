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

    @Override
    public Cart updateCart(CartRequest cartRequest) {
        try {
            // Retrieve the existing cart based on the given cartId

            Long cartIdTemp = cartRepository.findCartIdByCustomerId(cartRequest.getCustomerId());


//            if(cartIdTemp==null)
//            {
//
//            }

            Optional<Cart> existingCartOptional = cartRepository.findById(cartIdTemp);
            if (existingCartOptional.isPresent()) {
                Cart existingCart = existingCartOptional.get();

                // Update customer information if customerId is provided
                if (cartRequest.getCustomerId() != null) {
                    try {
                        Customer customer = customerService.getCustomerById(cartRequest.getCustomerId());
                        existingCart.setCustomer(customer);
                    } catch (Exception e) {
                        existingCart.setCustomer(null);
                    }
                }

                // Update last update timestamp if provided
                if (cartRequest.getLastUpdate() != null) {
                    existingCart.setLastUpdate(cartRequest.getLastUpdate());
                }

                // Save the updated cart
                Cart updatedCart = cartRepository.save(existingCart);

                // Update or add cart details
                HashMap<Long, Integer> listCartDetail = cartRequest.getCartDetails();
                if (listCartDetail != null) {
                    listCartDetail.forEach((productId, quantity) -> {
                        Optional<Products> productsOptional = productRepository.findById(productId);
                        if (productsOptional.isPresent()) {
                            Products product = productsOptional.get();
                            double price = product.getPrice() * quantity;

                            // Check if the cart item already exists, update it; otherwise, create a new one
                            Optional<CartItem> existingCartItemOptional = cartDetailRepository.findByCartAndProduct(existingCart, product);
                            CartItem cartItem;
                            if (existingCartItemOptional.isPresent()) {
                                cartItem = existingCartItemOptional.get();
                                cartItem.setQuantity(quantity);
                                cartItem.setPrice(price);
                            } else {
                                cartItem = new CartItem();
                                cartItem.setCart(existingCart);
                                cartItem.setProduct(product);
                                cartItem.setQuantity(quantity);
                                cartItem.setPrice(price);
                            }

                            cartDetailRepository.save(cartItem);
                        }
                    });
                }

                return updatedCart;
            } else {
                // Handle case where the cart with the given cartId is not found
              addCart(cartRequest);
              return null;
            }
        } catch (Exception e) {
            addCart(cartRequest);

            return null;
        }
    }
}
