package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.model.request.CustomerRequest;
import com.camera.projectcamera.repository.AccountRepository;
import com.camera.projectcamera.repository.CustomerRepository;
import com.camera.projectcamera.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean login(String userName, String passWord) {
        Optional<Accounts> account = accountRepository.findByUserNameAndPassword(userName, passWord);
        return account.isPresent();
    }

    @Override
    public CustomerRequest getUserInfo(String userName) {
        Customer customer = customerRepository.findByUserName(userName);
        if (customer != null) {
            return new CustomerRequest(
                    customer.getPersonId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getStreet(),
                    customer.getCity(),
                    String.valueOf(customer.getStatus()),
                    customer.getAccount().getUserName(),
                    customer.getAccount().getPassword(),
                    customer.getAccount().getRole().getRoleId()
            );
        } else {
            return null;
        }
    }

}
