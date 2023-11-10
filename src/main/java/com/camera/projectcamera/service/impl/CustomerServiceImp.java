package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.repository.CustomerRepository;
import com.camera.projectcamera.responsentory.RoleRepo;
import com.camera.projectcamera.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepo;

    @Override
    public Customer createCustomer(Customer customer) {
        try
        {
            return customerRepo.save(customer);

        }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }

    }
}
