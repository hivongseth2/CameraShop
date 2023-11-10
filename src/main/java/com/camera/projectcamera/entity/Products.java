package com.camera.projectcamera.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Table(name = "product_table")

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String modelYear;
    private Date yearOfManual;
    private int status;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brand;

    @OneToMany(mappedBy = "product")
    private List<Properties> properties;

    @OneToMany(mappedBy = "product")
    private List<Images> images;
}
