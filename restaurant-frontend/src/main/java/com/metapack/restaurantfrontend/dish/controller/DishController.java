package com.metapack.restaurantfrontend.dish.controller;

import com.metapack.restaurantfrontend.dish.model.DishEntity;
import com.metapack.restaurantfrontend.dish.service.DishApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DishController {

    @Autowired
    private DishApiClient dishApiClient;

    @GetMapping("/menu")
    public String allDishes(final Model model) {
        final List<DishEntity> dishes = dishApiClient.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "menu";
    }

    @GetMapping("/dishes/new")
    public String newDish(final Model model) {
        model.addAttribute("dish", new DishEntity());
        return "dish-add";
    }

    @PostMapping("/dishes")
    public String addDish(@Valid final DishEntity dishEntity) {
        try {
            dishApiClient.addDish(dishEntity);
            return "redirect:/dishes";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/dishes/update")
    public String updateDish(@Valid final DishEntity dishEntity) {
        dishApiClient.updateDish(dishEntity);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/{id}/delete")
    public String deleteDish(@PathVariable final long id) {
        dishApiClient.deleteDish(id);
        return "redirect:/dishes";
    }

}
