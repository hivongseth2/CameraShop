package com.camera.projectcamera.service.impl;

import com.camera.projectcamera.entity.*;
import com.camera.projectcamera.model.request.OrderDetailRequest;
import com.camera.projectcamera.model.request.OrderRequest;
import com.camera.projectcamera.model.request.OrderResponse;
import com.camera.projectcamera.repository.OrderDetailRepository;
import com.camera.projectcamera.repository.OrderRepository;
import com.camera.projectcamera.repository.ProductRepository;
import com.camera.projectcamera.service.CustomerService;
import com.camera.projectcamera.service.OrderDetailService;
import com.camera.projectcamera.service.OrderService;
import com.camera.projectcamera.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private  final ProductRepository productRepository;
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
            order.setStatus(orderRequest.getStatus());
            order.setShippedDate(orderRequest.getShipDate());
            order.setAddress(orderRequest.getAddress());
            System.out.println(customer);
            Order theOrder =  orderRepository.save(order);


//             create detail
            HashMap<Long, Integer> listOrderDetail = orderRequest.getOrderDetails();

            listOrderDetail.forEach((k,v)->
            {
                Optional<Products> productOption = productRepository.findById(k);
                if(productOption.isPresent())
                {
                    Products product = productOption.get();
                    double price = product.getPrice()*v;

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setPrice(price);
                    orderDetail.setOrder(theOrder);
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(v);
                    orderDetailRepository.save(orderDetail);


                }
            });
            return theOrder;


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

    @Override
    public Order getOrderByCustomerId(Long customerId) {
        return null;
    }
    @Override
    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomer_PersonId(customerId);

        List<OrderResponse> orderResponses = orders.stream()
                .map(order -> {
                    List<OrderDetailRequest> orderDetails = order.getOrderDetails().stream()
                            .map(orderDetail -> {
                                Products product = orderDetail.getProduct();
                                List<String> productImages = product.getImages().stream()
                                        .map(Images::getId)
                                        .collect(Collectors.toList());

                                return new OrderDetailRequest(
                                        orderDetail.getPrice(),
                                        orderDetail.getQuantity(),
                                        orderDetail.getOrderDetailId(),
                                        orderDetail.getOrder().getOrderId(),
                                        product.getProductId(),
                                        product.getName(),
                                        productImages,
                                        order.getOrderDate(),
                                        order.getShippedDate(),
                                        order.getAddress()
                                );
                            })
                            .collect(Collectors.toList());

                    Customer customer = order.getCustomer();

                    return new OrderResponse(
                            order.getOrderId(),
                            orderDetails,
                            order.getOrderDate(),
                            order.getShippedDate(),
                            order.getAddress(),
                            customer.getPersonId(),
                            customer.getFirstName(),
                            customer.getLastName(),
                            customer.getStreet(),
                            customer.getPhone(),
                            order.getStatus()
                    );
                })
                .collect(Collectors.toList());

        return orderResponses;
    }
    @Transactional
    public boolean updateOrderStatus(Long orderId, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(newStatus); // Đặt trạng thái mới cho đối tượng Order

            // Lưu thay đổi vào cơ sở dữ liệu
            orderRepository.save(order);

            return true; // Trạng thái order đã được cập nhật thành công
        }

        return false; // Không tìm thấy order với orderId tương ứng
    }
}
