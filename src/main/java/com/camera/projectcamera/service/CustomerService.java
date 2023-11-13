package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.model.request.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer createCustomer(CustomerRequest customerRequest);
    Customer getCustomerById(Long customerId);
    Customer updateCustomer (Long customerId, Customer customer);


}
