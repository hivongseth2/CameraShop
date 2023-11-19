package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Cart;
import com.camera.projectcamera.entity.CartItem;
import com.camera.projectcamera.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Products product);

//    @Query("SELECT c.cartId FROM Cart c WHERE c.customer.personId = :customerId")

    @Query("SELECT c FROM CartItem c " +
            "WHERE c.product.productId = :productId " +
            "AND c.cart.cartId = (SELECT ct.cartId FROM Cart ct WHERE ct.customer.personId = :customerId)")
    Optional<CartItem> findCartItemByProduct(@Param("customerId") Long customerId, @Param("productId") Long productId);

    List<CartItem> findByCart(Cart cart);
}
