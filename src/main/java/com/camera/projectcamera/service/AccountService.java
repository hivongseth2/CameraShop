package com.camera.projectcamera.service;

import com.camera.projectcamera.dto.AccountDTO;
import com.camera.projectcamera.entity.Accounts;

public interface AccountService {
    void addAccount(Accounts account);

    void updatePasswordAccount(Long accountId, AccountDTO accountDTO);
}
