package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@ToString
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private String city;

    @OneToOne
    @JoinColumn(name="account_id")
    private Accounts account;
}
