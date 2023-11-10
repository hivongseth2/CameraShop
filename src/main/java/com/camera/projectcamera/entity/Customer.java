// Customer.java
package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity

@Table(name = "customer_table")
public class Customer extends Person {



}
