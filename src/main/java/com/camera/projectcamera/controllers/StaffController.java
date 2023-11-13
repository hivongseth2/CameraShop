package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Categories;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Staff;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.CustomerRequest;
import com.camera.projectcamera.model.request.StaffRequest;
import com.camera.projectcamera.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

//    @PostMapping("/add")
//    public String addStaff(@RequestBody Staff staff){
//        staffService.addStaff(staff);
//        return "add staff success";
//    }



    @GetMapping
    public List<Staff>  getStaff(){
        return staffService.getStaff();
    }

    @GetMapping("/get")
    public Staff getStaffById(Long staffId){
        return staffService.getStaffById(staffId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStaff(@RequestBody StaffRequest staffRequest) {
        Staff staff = staffService.addStaff(staffRequest);

        if(staff==null){
            return ResponseEntity.badRequest().body(new MessageError(400, "Create Staff Error"));
        }

        return ResponseEntity.ok(staff);
    }}
