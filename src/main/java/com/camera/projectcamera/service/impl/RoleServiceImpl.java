package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.responsentory.RoleRepo;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Role getRoleById(Long id) {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Mặt hàng không được tìm thấy với id" + id));
        return role;
    }

    @Override
    public Role updateRole(Long roleId, String name) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRoleName(name);
            // Perform any other update logic as needed

            // Save the updated role
            return roleRepository.save(role);
        } else {
            // Role with the given ID not found
            return null;
        }
    }
//
//    @Override
//    public void update(Long id, String name) {
//        Optional<Role> roleOptional = roleRepository.findById(id);
//
//        if(roleOptional.isEmpty()){
////            throw new Bad
//        }
//    }
}
