package com.metapack.restaurantbackend.dish.error;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
