package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;
    private String name;
    private String value;


    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;


}
