package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c.cartId FROM Cart c WHERE c.customer.personId = :customerId")
    Long findCartIdByCustomerId(@Param("customerId") Long customerId);
}
