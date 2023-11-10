package com.camera.projectcamera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Date orderDate;
    private Date shippedDate;
    private String address;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "customer_personId", referencedColumnName = "personId"),
            @JoinColumn(name = "customer_customerId", referencedColumnName = "customerId")
    })
    private Customer customer;

    @OneToMany(mappedBy="order")
    private List<OrderDetail> orderDetails;



}
