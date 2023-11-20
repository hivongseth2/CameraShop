package com.camera.projectcamera.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private List<OrderDetailRequest> orderDetails;
    private Date orderDate;
    private Date shippedDate;
    private String address;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String status;
}

