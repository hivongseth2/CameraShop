package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.Categories;
import com.camera.projectcamera.entity.Customer;
import com.camera.projectcamera.entity.Order;
import com.camera.projectcamera.model.request.OrderRequest;
import com.camera.projectcamera.repository.OrderDetailRepository;
import com.camera.projectcamera.repository.OrderRepository;
import com.camera.projectcamera.service.CustomerService;
import com.camera.projectcamera.service.OrderDetailService;
import com.camera.projectcamera.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public Order addOrder(OrderRequest orderRequest) {

        Order order = new Order();
        try
        {
            Customer customer = new Customer();
            try
            {
                customer = customerService.getCustomerById(orderRequest.getCustomerId());
                order.setCustomer(customer);
            }
            catch(Exception e)
            {
                order.setCustomer(null);
            }
            order.setOrderDate(orderRequest.getOrderDate());
            order.setCustomer(customer);
            order.setShippedDate(orderRequest.getShipDate());
            order.setAddress(order.getAddress());
            System.out.println(customer);
            return orderRepository.save(order);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Mặt hàng không được tìm thấy với id" + orderId));
        return order;
    }



}
