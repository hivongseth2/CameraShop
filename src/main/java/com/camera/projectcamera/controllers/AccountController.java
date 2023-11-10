package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
