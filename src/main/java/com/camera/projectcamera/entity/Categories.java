package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categories {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String name;
    private String image;

//    @OneToMany(mappedBy = "role")
//    private List<accounts> listAccount;

    @OneToMany(mappedBy ="category")
    private List<Products> products;

}
