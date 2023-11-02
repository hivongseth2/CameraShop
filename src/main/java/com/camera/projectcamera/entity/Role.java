package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<Accounts> listAccount;
}
