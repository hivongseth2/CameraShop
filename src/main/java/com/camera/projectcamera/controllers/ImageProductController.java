package com.camera.projectcamera.controllers;

import com.camera.projectcamera.service.ImageProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor

public class ImageProductController {


    private final ImageProductService imageProductService;

    @PostMapping("/{productId}")
    public ResponseEntity<String> uploadImage(@PathVariable Long productId,
                                              @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageProductService.saveImage(productId, file);
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private final String imageDirectory = "C:/Users/luan/OneDrive - Industrial University of HoChiMinh City/Desktop/web2/projectcamera/productImage/";

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) throws MalformedURLException {

        System.out.println((imageDirectory+ imageName));
        Path imagePath = Paths.get(imageDirectory, imageName);
        Resource imageResource = new UrlResource(imagePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(imageResource);
    }
}
