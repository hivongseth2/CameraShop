package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.dto.AccountDTO;
import com.camera.projectcamera.dto.CategoriesDTO;
import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.entity.Categories;
import com.camera.projectcamera.repository.AccountRepository;
import com.camera.projectcamera.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccount(Accounts account) {
        if(account.getUserName()==null || account.getPassword()==null) {
            throw new RuntimeException("Username and password is required!");
        }
        accountRepository.save(account);
    }

    @Override
    public void updatePasswordAccount(Long accountId, AccountDTO accountDTO) {
        Accounts accounts = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + accountId));

        accounts.setPassword(accountDTO.getPassword());
        accountRepository.save(accounts);
    }


}


