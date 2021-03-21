package com.metapack.restaurantbackend.dish.persistance.service;

import com.metapack.restaurantbackend.dish.error.DishNotFoundException;
import com.metapack.restaurantbackend.dish.model.DishEntity;
import com.metapack.restaurantbackend.dish.persistance.repository.DishRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository repository;

    public List<DishEntity> getAllDishes() {
        return repository.findAll();

    }

    public DishEntity addDish(DishEntity dishEntity) {
        return repository.save(dishEntity);
    }

    public DishEntity updateDish(DishEntity dishEntity) throws DishNotFoundException {
        Optional<DishEntity> result = repository.findById(dishEntity.getId());
        if (result.isPresent()) {
            DishEntity existingDish = result.get();
            BeanUtils.copyProperties(dishEntity, existingDish);
            return repository.save(existingDish);
        }
        throw new DishNotFoundException(String.format("Dish with id %d not found.", dishEntity.getId()));
    }

    public void deleteDish(long id) {
        repository.deleteById(id);
    }
}
