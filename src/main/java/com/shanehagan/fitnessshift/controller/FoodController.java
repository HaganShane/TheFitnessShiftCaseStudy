/**
 * @Author: Shane Hagan
 * Date: 2/14/2023
 * Food Controller to handle requests specific to the food module.
 * Able to show the food table specific to the user signed in, add food for that user,
 * update food for that user, or delete food for that user.
 * Uses @GetMapping to show, @PostMapping to process, @PathVariables to pass the userId around,
 * Model to add attributes to be displayed via Thymeleaf, @ModelAttribute to add the specific model.
 */

package com.shanehagan.fitnessshift.controller;

import com.shanehagan.fitnessshift.model.Food;
import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.repository.UserRepository;
import com.shanehagan.fitnessshift.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class FoodController {

    /**
     * Declare some constants here using the final tag
     */
    private final FoodService foodService;
    private final UserRepository userRepository;

    /**
     * Autowire via construction injection rather than field injection
     * @param foodService - declared above, our foodService obj
     * @param userRepository - declare above, our userRepository obj
     */
    @Autowired
    public FoodController(FoodService foodService, UserRepository userRepository){
        this.foodService = foodService;
        this.userRepository = userRepository;
    }

    /**
     * Display the foods relative to the user
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the module homepage where it displays the user information
     */
    @GetMapping("/food/{uId}")
    public String showFoods(@PathVariable("uId") int uId, Model model){
        List<Food> foodList = foodService.getFoodsByUserId(uId);
        model.addAttribute("foodList", foodList);
        return "food";
    }

    /**
     * Display the add food form on a separate page
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the add-food.html page where a user can add a new food
     */
    @GetMapping("/addFoodForm/{uId}")
    public String addFoodForm(@PathVariable("uId") int uId, Model model){
        Food food = new Food();
        model.addAttribute("food", food);

        return "add-food";
    }

    /**
     * Post mapping after the user adds a food to save it to the database
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param food - model attribute to save to the database
     * @return - redirects back to the module homepage where it displays the user information
     */
    @PostMapping("/addFood/{uId}")
    public String addFood(@PathVariable("uId") int uId, @ModelAttribute("food") Food food){
        Optional<User> user = userRepository.findById(uId);
        food.setUser(user.get());

        foodService.addFood(food);

        return "redirect:/food/" + uId;
    }

    /**
     * Deletes food after the user clicks the delete button
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the food id to be deleted
     * @return - redirects back to the module homepage where it displays the user information
     */
    @GetMapping("/deleteFood/{uId}/{id}")
    public String deleteFoodById(@PathVariable("uId") int uId, @PathVariable(value = "id") int id){
        foodService.deleteFoodById(id);

        return "redirect:/food/" + uId;
    }

    /**
     * Displays the update form where a user can update a given record, if needed
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the food id to be deleted updated
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the update food form where the user can update a given food
     */
    @GetMapping("/updateFoodForm/{uId}/{id}")
    public String updateFoodForm(@PathVariable("uId") int uId, @PathVariable(value = "id") int id, Model model){
        Food food = foodService.getFoodById(id);
        model.addAttribute("food", food);

        return "update-food";
    }
}
