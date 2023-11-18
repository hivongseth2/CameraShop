package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c JOIN c.account a WHERE a.userName = :userName")
    Customer findByUserName(@Param("userName") String userName);
}
