package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Order;
import com.camera.projectcamera.entity.Staff;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.OrderRequest;
import com.camera.projectcamera.model.request.StaffRequest;
import com.camera.projectcamera.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?>addOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.addOrder(orderRequest);
        if (order == null) {
            return ResponseEntity.badRequest().body(new MessageError(400, "Create Order Error"));
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public List<Order> getOrder(){
        return orderService.getOrder();
    }

    @GetMapping("/get")
    public Order getOrderById(Long orderId){
        return orderService.getOrderById(orderId);
    }
}
