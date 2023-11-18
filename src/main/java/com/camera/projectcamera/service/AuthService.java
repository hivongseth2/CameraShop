package com.camera.projectcamera.service;

import com.camera.projectcamera.model.request.CustomerRequest;

public interface AuthService {
    boolean login(String userName, String password);

    CustomerRequest getUserInfo(String userName);
}
