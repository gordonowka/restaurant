package com.metapack.restaurantbackend.dish.persistance.repository;

import com.metapack.restaurantbackend.dish.model.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {
}
