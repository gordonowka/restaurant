package com.metapack.restaurantfrontend.dish.service;

import com.metapack.restaurantfrontend.dish.model.DishEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class DishApiClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(DishApiClient.class);

    @Value("${restaurant-backend.server.url}")
    private String SERVER_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<DishEntity> getAllDishes() {
        return restTemplate.getForObject(getDishesUri(), List.class);
    }

    public void addDish(final DishEntity dishEntity) {
        final HttpEntity<DishEntity> dishBody = new HttpEntity<>(dishEntity);
        final URI uri = URI.create(getDishesUri());
        ResponseEntity<DishEntity> result = restTemplate.exchange(uri, HttpMethod.POST, dishBody, DishEntity.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("Error occurred from server: {}", result.getStatusCodeValue());
        }
    }

    public void updateDish(final DishEntity dishEntity) {
        final HttpEntity<DishEntity> dishBody = new HttpEntity<>(dishEntity);
        final URI uri = URI.create(getDishUri(dishEntity.getId()));
        ResponseEntity<DishEntity> result = restTemplate.exchange(uri, HttpMethod.PUT, dishBody, DishEntity.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("Error occurred from server: {}", result.getStatusCodeValue());
        }
    }

    public void deleteDish(long id) {
        final URI uri = URI.create(getDishUri(id));
        restTemplate.delete(uri);
    }

    private String getDishesUri() {
        return SERVER_URL + "/dishes";
    }

    private String getDishUri(final long id) {
        return String.format("%s/dishes/%d",SERVER_URL, id);
    }
}
