package com.camera.projectcamera.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy ="category")
    private List<Products> products;

}
