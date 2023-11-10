package com.camera.projectcamera.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;


    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
