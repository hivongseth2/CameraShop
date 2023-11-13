package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageProductRepository extends JpaRepository<Images, Long> {

}
