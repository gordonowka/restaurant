package com.metapack.restaurantfrontend.order.controller;

import com.metapack.restaurantfrontend.dish.model.DishEntity;
import com.metapack.restaurantfrontend.order.model.OrderEntity;
import com.metapack.restaurantfrontend.order.service.OrderApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller

public class OrderController {

    @Autowired
    private OrderApiClient orderApiClient;

    @GetMapping("/order")
    public String getOrder(final Model model, @PathVariable final long id){
        model.addAttribute("order", orderApiClient.getOrder(id));
        return "order";
    }

//    @PostMapping
//    public String addToOrder(@Valid final DishEntity dishEntity, final OrderEntity orderEntity){
//        orderApiClient.addDishToOrder(dishEntity, orderEntiry);
//
//        return "redirect:/order";
//    }
}
