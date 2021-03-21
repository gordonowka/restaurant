package com.metapack.restaurantbackend.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ZonedDateTime dateTime;
    private String email;
    private String comment;
    private double value;
    @ElementCollection
    private List<Long> dishesId;
}
