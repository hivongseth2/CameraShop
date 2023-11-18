package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByUserNameAndPassword(String userName, String password);
}
