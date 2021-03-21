package com.metapack.restaurantbackend.dish.controller;

import com.metapack.restaurantbackend.dish.api.DishApi;
import com.metapack.restaurantbackend.dish.model.DishEntity;
import com.metapack.restaurantbackend.dish.persistance.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishController implements DishApi {

    @Autowired
    private DishService service;

    @Override
    public ResponseEntity getAllDishes() {
        List<DishEntity> allDishes = service.getAllDishes();
        return ResponseEntity.status(HttpStatus.OK).body(allDishes);
    }

    @Override
    public ResponseEntity addDish(DishEntity dishEntity) {
        if (dishEntity.getId()!=0){
            return ResponseEntity.badRequest().body("Id should be empty.");
        }
        DishEntity newDish = service.addDish(dishEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDish);
    }

    @Override
    public ResponseEntity updateDish(long id, DishEntity dishEntity) {
        if (id!=dishEntity.getId()){
            return ResponseEntity.badRequest().body("Id from path should be the same as from body.");
        }
        DishEntity updateDish = service.updateDish(dishEntity);
        return ResponseEntity.status(HttpStatus.OK).body(updateDish);
    }

    @Override
    public ResponseEntity deleteDish(long id) {
        service.deleteDish(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
