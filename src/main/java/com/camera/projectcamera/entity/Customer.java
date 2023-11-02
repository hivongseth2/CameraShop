package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private String city;


    @OneToOne()
    @JoinColumn(name="account_id")
    private Accounts account;


    @OneToMany()
    private List<Order> orders;


}
