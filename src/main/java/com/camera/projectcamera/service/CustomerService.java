package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer createCustomer(Customer customer);


}
