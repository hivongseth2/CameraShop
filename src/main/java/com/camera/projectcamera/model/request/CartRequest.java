package com.camera.projectcamera.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Long customerId;
//    private Long cartId;
    private Date lastUpdate;

    private HashMap<Long, Integer> cartDetails;
}
