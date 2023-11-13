package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Staff;
import com.camera.projectcamera.model.request.StaffRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StaffService{
//    void addStaff(Staff staff);

    List<Staff> getStaff();

    Staff getStaffById(Long staffId);

    Staff addStaff(StaffRequest staffRequest);
}
