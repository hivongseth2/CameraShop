package com.camera.projectcamera.responsentory;

import com.camera.projectcamera.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface RoleRepo extends JpaRepository<Role,Long>
    {
//        Role findRoleByRoleName(String roleName);
//
//        @Query(nativeQuery = true, value = "")
//        List<Role> findAllRole();
    }

