package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role create(String name);

//    void update(Long id, String name);
}
