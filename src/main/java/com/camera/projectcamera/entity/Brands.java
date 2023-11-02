package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long brandId;
    private String name;
    private String image;

    @OneToMany(mappedBy = "brand")
    private List<Products> products;

}
