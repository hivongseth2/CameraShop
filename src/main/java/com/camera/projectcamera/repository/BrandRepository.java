package com.camera.projectcamera.repository;

import com.camera.projectcamera.entity.Brands;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brands, Long> {

}
