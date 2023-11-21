package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Images;
import com.camera.projectcamera.repository.ImageProductRepository;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.ImageProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageProductServiceImpl implements ImageProductService {

    private final ImageProductRepository imageProductRepository;
    private final ProductRepository productRepository;
    @Value("${upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public String saveImage(Long productId, MultipartFile file) throws IOException {
        // Lưu file vào thư mục uploadDir
        try
        {
            String fileName = productId + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + "/" + fileName));

            // Lưu thông tin ảnh vào cơ sở dữ liệu
            Images image = new Images();
            image.setId( "http://localhost:8081/images/"+fileName); // Có thể sử dụng UUID để đảm bảo độ duy nhất
            // Lấy sản phẩm từ cơ sở dữ liệu và thiết lập nó cho ảnh
            // (tùy thuộc vào cách bạn đã thiết kế quan hệ giữa sản phẩm và ảnh)

            image.setProduct(productRepository.findById(productId).orElse(null));

            imageProductRepository.save(image);

            // Trả về URL của file đã lưu
            return "http://localhost:8081/images/"+fileName;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

}
