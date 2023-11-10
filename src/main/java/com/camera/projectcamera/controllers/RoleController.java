package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Role;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.RoleRequest;
import com.camera.projectcamera.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody RoleRequest request) {
        Role role = roleService.create(request.getRoleName());

        if(role==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create role error"));
        }

        return ResponseEntity.ok(role);
    }

    @GetMapping("/get")
    public Role getRole(@RequestParam Long roleId)
    {
        return roleService.getRoleById(roleId);
    }
    @PutMapping("/update/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable Long roleId,@RequestBody RoleRequest request) {
        Role role = roleService.updateRole(roleId,request.getRoleName());

        if(role==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create role error"));
        }

        return ResponseEntity.ok(role);


    }

}
