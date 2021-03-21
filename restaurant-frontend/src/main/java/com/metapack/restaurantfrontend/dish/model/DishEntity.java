package com.metapack.restaurantfrontend.dish.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishEntity {

    private long id;
    private String dishGroup;
    private String name;
    private int price;

}
