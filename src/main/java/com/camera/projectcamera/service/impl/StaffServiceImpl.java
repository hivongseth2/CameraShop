    package com.camera.projectcamera.service.impl;

    import com.camera.projectcamera.entity.Staff;
    import com.camera.projectcamera.repository.StaffRepository;
    import com.camera.projectcamera.service.StaffService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class StaffServiceImpl implements StaffService {

        @Autowired
        private StaffRepository staffRepository;

        @Override
        public void addStaff(Staff staff) {
            staffRepository.save(staff);
        }
    }
