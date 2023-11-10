package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.RoleRequest;
import com.camera.projectcamera.service.CustomerService;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        Customer theCustomer =customerService.createCustomer(customer);

        if(theCustomer==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create Customer Error"));
        }

        return ResponseEntity.ok(theCustomer);
    }
}
