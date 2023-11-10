package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.model.request.CustomerRequest;
import com.camera.projectcamera.repository.AccountRepository;
import com.camera.projectcamera.repository.CustomerRepository;
import com.camera.projectcamera.responsentory.RoleRepo;
import com.camera.projectcamera.service.AccountService;
import com.camera.projectcamera.service.CustomerService;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final RoleService roleService;

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {

        Accounts account = new Accounts();
        try
        {
            try
            {
                Role role = roleService.getRoleById(customerRequest.getRoleId());

                account.setUserName(customerRequest.getUserName());
                account.setPassword(customerRequest.getPassWord());
                account.setRole(role);
                accountRepository.save(account);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Error when save account");
            }
            finally
            {

                Customer customer = new Customer();
                customer.setFirstName(customerRequest.getFirstName());
                customer.setLastName(customerRequest.getLastName());
                customer.setPhone(customerRequest.getPhone());
                customer.setEmail(customerRequest.getEmail());
                customer.setStreet(customerRequest.getStreet());
                customer.setCity(customerRequest.getCity());
                customer.setStatus(1);
                customer.setAccount(account);
                return customerRepo.save(customer);
            }

//

        }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }

    }

    @Override
    public Customer getCustomerById(Long customerId) {
       Customer customer = customerRepo
                .findById(customerId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong tim thay Khach hang"));
        return customer;
    }
}
