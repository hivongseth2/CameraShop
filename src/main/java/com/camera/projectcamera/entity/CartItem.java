package com.camera.projectcamera.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cart_item_table")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    private int quantity;
    private double price;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @OneToOne
    @JoinColumn(name="product_id")
    private Products product;
}
