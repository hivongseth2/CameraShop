package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.RoleRequest;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoleRequest request) {
        Role role = roleService.create(request.getRoleName());

        if(role==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create role error"));
        }

        return ResponseEntity.ok(role);
    }
}
