package com.metapack.restaurantfrontend.order.service;

import com.metapack.restaurantfrontend.dish.model.DishEntity;
import com.metapack.restaurantfrontend.order.model.OrderEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class OrderApiClient {

    @Value("${restaurant-backend.server.url}")
    private String SERVER_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    public OrderEntity getOrder(final long id) {
        final URI uri = URI.create(SERVER_URL + "/actualOrder");
        return restTemplate.getForObject(uri, OrderEntity.class);
    }

//    public void addDishToOrder(final DishEntity dishEntity, final OrderEntity orderEntity) {
//        final HttpEntity<OrderEntity> orderEntityBody = new HttpEntity<>(dishEntity);
//        final URI uri = URI.create(getOrderUri(orderEntity.getId()));
//    }

    private String getOrderUri(final long id) {
        return String.format("%s/actualOrder",SERVER_URL);
    }
}
