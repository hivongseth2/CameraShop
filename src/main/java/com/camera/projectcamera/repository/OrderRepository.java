package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.orderId FROM Order o WHERE o.customer.personId = :customerId")
    Long findOrderIdByCustomerId(@Param("customerId") Long customerId);

    List<Order> findByCustomer_PersonId(Long personId);

}
