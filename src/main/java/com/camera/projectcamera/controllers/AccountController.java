package com.camera.projectcamera.controllers;

import com.camera.projectcamera.dto.AccountDTO;
import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/add")
    public String addAccount(@RequestBody Accounts account){
        accountService.addAccount(account);
        return "add account success";
    }

    @PatchMapping("update-password/{accountId}")
    public ResponseEntity<Void> updatePasswordAccount(@PathVariable Long accountId, @RequestBody AccountDTO accountDTO)
    {
        accountService.updatePasswordAccount(accountId, accountDTO);
        return ResponseEntity.noContent().build();
    }

}
