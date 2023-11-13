package com.camera.projectcamera.model.request;


import com.camera.projectcamera.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private Date orderDate;
    private Long orderId;
    private Date shipDate;
    private String address;
    private HashMap<Long , Integer> OrderDetails;

}
