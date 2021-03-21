package com.metapack.restaurantbackend.order.api;

import com.metapack.restaurantbackend.order.model.OrderEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderApi {

    @GetMapping(path = "/{id}")
    ResponseEntity getOrderById(@PathVariable long id);

    @GetMapping
    ResponseEntity getOrdersByEmail(@RequestParam("email") String email);

    @PostMapping
    ResponseEntity addOrder(@RequestBody OrderEntity orderEntity);

}