package com.metapack.restaurantfrontend.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private String id;
    private ZonedDateTime dateTime;
    private String email;
    private String comment;
    private double value;
    private List<Long> dishesId;
}
