package com.camera.projectcamera.controllers;

import com.camera.projectcamera.dto.AccountDTO;
import com.camera.projectcamera.entity.Accounts;
import com.camera.projectcamera.model.MessageError;
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
    public ResponseEntity<?> addAccount(@RequestBody Accounts account){
        accountService.addAccount(account);
        if(account==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create role error"));
        }
       return ResponseEntity.ok(account);
    }

    @PatchMapping("update-password/{accountId}")
    public ResponseEntity<Void> updatePasswordAccount(@PathVariable Long accountId, @RequestBody AccountDTO accountDTO)
    {
        accountService.updatePasswordAccount(accountId, accountDTO);
        return ResponseEntity.noContent().build();
    }

}
