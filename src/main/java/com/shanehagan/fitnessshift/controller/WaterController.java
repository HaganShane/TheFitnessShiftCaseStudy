/**
 * @Author: Shane Hagan
 * Date: 2/14/2023
 * Water Controller to handle requests specific to the water module.
 * Able to show the water table specific to the user signed in, add water for that user,
 * update water for that user, or delete water for that user.
 * Uses @GetMapping to show, @PostMapping to process, @PathVariables to pass the userId around,
 * Model to add attributes to be displayed via Thymeleaf, @ModelAttribute to add the specific model.
 */

package com.shanehagan.fitnessshift.controller;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.model.Water;
import com.shanehagan.fitnessshift.repository.UserRepository;
import com.shanehagan.fitnessshift.service.WaterService;
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
public class WaterController {

    /**
     * Declare some constants here using the final tag
     */
    private final WaterService waterService;
    private final UserRepository userRepository;

    /**
     * Autowire via construction injection rather than field injection
     * @param waterService - declared above, our waterService obj
     * @param userRepository - declare above, our userRepository obj
     */
    @Autowired
    public WaterController(WaterService waterService, UserRepository userRepository){
        this.waterService = waterService;
        this.userRepository = userRepository;
    }

    /**
     * Display the waters relative to the user
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the module homepage where it displays the user information
     */
    @GetMapping("/water/{uId}")
    public String showWaters(@PathVariable("uId") int uId, Model model){
        List<Water> waterList = waterService.getWatersByUserId(uId);
        model.addAttribute("waterList", waterList);
        return "water";
    }

    /**
     * Display the add water form on a separate page
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the add-water.html page where a user can add a new water
     */
    @GetMapping("/addWaterForm/{uId}")
    public String addWaterForm(@PathVariable("uId") int uId, Model model){
        Water water = new Water();
        model.addAttribute("water", water);

        return "add-water";
    }

    /**
     * Post mapping after the user adds a water to save it to the database
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param water - model attribute to save to the database
     * @return - redirects back to the module homepage where it displays the user information
     */
    @PostMapping("/addWater/{uId}")
    public String addWater(@PathVariable("uId") int uId, @ModelAttribute("water") Water water){
        Optional<User> user = userRepository.findById(uId);
        water.setUser(user.get());

        waterService.addWater(water);

        return "redirect:/water/" + uId;
    }

    /**
     * Deletes water after the user clicks the delete button
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the water id to be deleted
     * @return - redirects back to the module homepage where it displays the user information
     */
    @GetMapping("/deleteWater/{uId}/{id}")
    public String deleteWaterById(@PathVariable("uId") int uId, @PathVariable(value = "id") int id){
        waterService.deleteWaterById(id);

        return "redirect:/water/" + uId;
    }

    /**
     * Displays the update form where a user can update a given record, if needed
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the water id to be deleted updated
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the update water form where the user can update a given water
     */
    @GetMapping("/updateWaterForm/{uId}/{id}")
    public String updateWaterForm(@PathVariable("uId") int uId, @PathVariable(value = "id") int id, Model model){
        Water water = waterService.getWaterById(id);
        model.addAttribute("water", water);

        return "update-water";
    }
}
