package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.repository.AccountRepository;
import com.camera.projectcamera.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccount(Accounts account) {
        accountRepository.save(account);
    }
}
