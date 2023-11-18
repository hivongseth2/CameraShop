package com.camera.projectcamera.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long productId;
    private String name;
    private String modelYear;
    private Date yearOfManual;
    private int status;
    private double price;
    private Long categoryId;
    private String categoryName;
    private Long brandId;
    private String brandName;
    private int quantity;
    private List<ImageProductRequest> images;
    private List<PropertiesRequest> properties;
}
