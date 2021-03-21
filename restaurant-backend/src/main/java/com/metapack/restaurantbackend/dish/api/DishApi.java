package com.metapack.restaurantbackend.dish.api;

import com.metapack.restaurantbackend.dish.model.DishEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping ("/dishes")
public interface DishApi {

    @GetMapping
    ResponseEntity getAllDishes();

    @PostMapping
    ResponseEntity addDish(@RequestBody DishEntity dishEntity);

    @PutMapping(path = "/{id}")
    ResponseEntity updateDish(@PathVariable long id,@RequestBody DishEntity dishEntity);

    @DeleteMapping(path = "/{id}")
    ResponseEntity deleteDish (@PathVariable long id);

}
