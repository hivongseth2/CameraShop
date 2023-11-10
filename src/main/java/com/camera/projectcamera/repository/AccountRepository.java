package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, Long> {


}
