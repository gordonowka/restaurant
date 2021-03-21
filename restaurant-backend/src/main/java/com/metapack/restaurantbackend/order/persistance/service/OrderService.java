package com.metapack.restaurantbackend.order.persistance.service;

import com.metapack.restaurantbackend.dish.model.DishEntity;
import com.metapack.restaurantbackend.dish.persistance.repository.DishRepository;
import com.metapack.restaurantbackend.order.error.OrderNotFoundException;
import com.metapack.restaurantbackend.order.model.OrderEntity;
import com.metapack.restaurantbackend.order.persistance.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private DishRepository dishRepository;

    public OrderEntity getOrder(long id) {
        Optional<OrderEntity> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new OrderNotFoundException(String.format("Order with id %d not found", id));
    }

    public List<OrderEntity> getOrders(String email) {
        List<OrderEntity> orders = repository.findAll();
        List<OrderEntity> ordersByEmail = new ArrayList<>();
        for (OrderEntity order : orders) {
            String emailExistingOrders = order.getEmail();
            if (email.equals(emailExistingOrders)) {
                ordersByEmail.add(order);
            }
        }
        return ordersByEmail;
    }

    public OrderEntity addOrder(OrderEntity orderEntity) {
        orderEntity.setDateTime(ZonedDateTime.now());

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        String email = orderEntity.getEmail();
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches() == true) {
            double value = 0;
            List<Long> dishesId = orderEntity.getDishesId();
            for (int i = 0; i < dishesId.size(); i++) {
                long dishId = dishesId.get(i);
                DishEntity result = dishRepository.findById(dishId).get();
                value += result.getPrice();
            }
            orderEntity.setValue(value);
            return repository.save(orderEntity);
        }
        throw new OrderNotFoundException(String.format("Email %s is incorrect", email));
    }
}
