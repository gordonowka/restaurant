package com.metapack.restaurantbackend.order.controller;

import com.metapack.restaurantbackend.order.api.OrderApi;
import com.metapack.restaurantbackend.order.model.OrderEntity;
import com.metapack.restaurantbackend.order.persistance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController implements OrderApi {

    @Autowired
    private OrderService service;

    @Override
    public ResponseEntity getOrderById(long id) {
        OrderEntity order = service.getOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @Override
    public ResponseEntity getOrdersByEmail(String email) {
        List<OrderEntity> orders = service.getOrders(email);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @Override
    public ResponseEntity addOrder(OrderEntity orderEntity) {
        if (orderEntity.getId() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id should be empty");
        }
        OrderEntity newOrder = service.addOrder(orderEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

}
