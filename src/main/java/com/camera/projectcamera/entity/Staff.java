package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "staff_table")
public class Staff extends Person {

}
