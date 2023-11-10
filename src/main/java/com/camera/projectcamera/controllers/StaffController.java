package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Staff;
import com.camera.projectcamera.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/add")
    public String addStaff(@RequestBody Staff staff){
        staffService.addStaff(staff);
        return "add staff success";
    }
}
