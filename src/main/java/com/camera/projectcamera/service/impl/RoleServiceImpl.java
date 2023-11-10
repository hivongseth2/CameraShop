package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.responsentory.RoleRepo;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepository;

    @Override
    public Role create(String name) {
        Role role = new Role();
        role.setRoleName(name);

        return roleRepository.save(role);
    }

    @Override
    public void update(Long id, String name) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if(roleOptional.isEmpty()){
//            throw new Bad
        }
    }
}
