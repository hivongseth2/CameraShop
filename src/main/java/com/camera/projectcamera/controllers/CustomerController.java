package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Brands;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.CustomerRequest;
import com.camera.projectcamera.model.request.RoleRequest;
import com.camera.projectcamera.service.CustomerService;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CustomerRequest requestCustomer) {
        Customer theCustomer =customerService.createCustomer(requestCustomer);

        if(theCustomer==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create Customer Error"));
        }

        return ResponseEntity.ok(theCustomer);
    }

    @GetMapping("/getById")
    public Customer getCustomer(@RequestParam Long personId) {
        return customerService.getCustomerById(personId);
    }
    @PutMapping("/update/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId,@RequestBody Customer customer) {
        Customer theCustomer = customerService.updateCustomer(customerId,customer);

        if(customer==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create role error"));
        }
        return ResponseEntity.ok(theCustomer);
    }

}
