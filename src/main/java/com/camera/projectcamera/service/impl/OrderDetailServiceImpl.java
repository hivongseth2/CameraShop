package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Order;
import com.camera.projectcamera.entity.OrderDetail;
import com.camera.projectcamera.model.request.OrderDetailRequest;
import com.camera.projectcamera.repository.OrderDetailRepository;
import com.camera.projectcamera.repository.OrderRepository;
import com.camera.projectcamera.service.OrderDetailService;
import com.camera.projectcamera.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail addOrderDetail(OrderDetailRequest orderDetailRequest) {
        return null;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByCustomerId(Long customerId) {
        Long orderId = orderRepository.findOrderIdByCustomerId(customerId);
        if (orderId != null) {
            Optional<Order> orderOptional = orderRepository.findById(orderId);

            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                return orderDetailRepository.findByOrder(order);
            }
        }
        return Collections.emptyList();
    }


}
