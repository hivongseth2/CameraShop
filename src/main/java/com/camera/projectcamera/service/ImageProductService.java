package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Images;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service

public interface ImageProductService {

    String saveImage(Long productId, MultipartFile file) throws IOException;


}
