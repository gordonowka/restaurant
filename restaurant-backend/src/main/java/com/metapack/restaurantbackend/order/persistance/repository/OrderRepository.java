package com.metapack.restaurantbackend.order.persistance.repository;

import com.metapack.restaurantbackend.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
