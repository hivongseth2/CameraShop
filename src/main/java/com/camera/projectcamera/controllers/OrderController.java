package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.*;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.model.request.OrderDetailRequest;
import com.camera.projectcamera.model.request.OrderRequest;
import com.camera.projectcamera.model.request.OrderResponse;
import com.camera.projectcamera.model.request.StaffRequest;
import com.camera.projectcamera.service.OrderDetailService;
import com.camera.projectcamera.service.OrderService;
import com.camera.projectcamera.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private  final ProductService productService;

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

    @GetMapping("/getOrderDetailByCustomerId")
    public ResponseEntity<List<OrderDetailRequest>> getOrderDetailByCustomerId(@RequestParam Long customerId) {
        try {
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByCustomerId(customerId);
            Order order = orderService.getOrderByCustomerId(customerId);

            if (order != null && !orderDetails.isEmpty()) {
                List<OrderDetailRequest> orderDetailResponses = orderDetails.stream()
                        .map(orderDetail -> {
                            Products product = productService.getProductById(orderDetail.getProduct().getProductId());

                            // Check if product is not null before accessing its properties
                            List<String> productImages = (product != null)
                                    ? product.getImages().stream().map(Images::getId).collect(Collectors.toList())
                                    : Collections.emptyList();

                            return new OrderDetailRequest(
                                    orderDetail.getPrice(),
                                    orderDetail.getQuantity(),
                                    orderDetail.getOrder().getOrderId(),
                                    orderDetail.getOrderDetailId(),
                                    orderDetail.getProduct().getProductId(),
                                    orderDetail.getProduct().getName(),
                                    productImages,
                                    order.getOrderDate(),
                                    order.getShippedDate(),
                                    order.getAddress()
                            );
                        })
                        .collect(Collectors.toList());

                return ResponseEntity.ok(orderDetailResponses);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getOrdersByCustomerId")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(@RequestParam Long customerId) {
        List<OrderResponse> orderResponses = orderService.getOrdersByCustomerId(customerId);

        if (!orderResponses.isEmpty()) {
            return ResponseEntity.ok(orderResponses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
