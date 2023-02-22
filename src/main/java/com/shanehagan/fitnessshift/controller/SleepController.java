/**
 * @Author: Shane Hagan
 * Date: 2/14/2023
 * Sleep Controller to handle requests specific to the sleep module.
 * Able to show the sleep table specific to the user signed in, add sleep for that user,
 * update sleep for that user, or delete sleep for that user.
 * Uses @GetMapping to show, @PostMapping to process, @PathVariables to pass the userId around,
 * Model to add attributes to be displayed via Thymeleaf, @ModelAttribute to add the specific model.
 */

package com.shanehagan.fitnessshift.controller;

import com.shanehagan.fitnessshift.model.Sleep;
import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.repository.UserRepository;
import com.shanehagan.fitnessshift.service.SleepService;
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
public class SleepController {

    /**
     * Declare some constants here using the final tag
     */
    SleepService sleepService;
    UserRepository userRepository;

    /**
     * Autowire via construction injection rather than field injection
     * @param sleepService - declared above, our sleepService obj
     * @param userRepository - declare above, our userRepository obj
     */
    @Autowired
    public SleepController(SleepService sleepService, UserRepository userRepository){
        this.sleepService = sleepService;
        this.userRepository = userRepository;
    }

    /**
     * Display the sleeps relative to the user
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the module homepage where it displays the user information
     */
    @GetMapping("/sleep/{uId}")
    public String showSleep(@PathVariable("uId") int uId, Model model){
        List<Sleep> sleepList = sleepService.getSleepsByUserId(uId);
        model.addAttribute("sleepList", sleepList);
        return "sleep";
    }

    /**
     * Display the add sleep form on a separate page
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the add-sleep.html page where a user can add a new sleep
     */
    @GetMapping("/addSleepForm/{uId}")
    public String addSleepForm(@PathVariable("uId") int uId, Model model){
        Sleep sleep = new Sleep();
        model.addAttribute("sleep", sleep);

        return "add-sleep";
    }

    /**
     * Post mapping after the user adds a sleep to save it to the database
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param sleep - model attribute to save to the database
     * @return - redirects back to the module homepage where it displays the user information
     */
    @PostMapping("/addSleep/{uId}")
    public String addSleep(@PathVariable("uId") int uId, @ModelAttribute("sleep") Sleep sleep){
        Optional<User> user = userRepository.findById(uId);
        sleep.setUser(user.get());

        sleepService.addSleep(sleep);

        return "redirect:/sleep/" + uId;
    }

    /**
     * Deletes sleep after the user clicks the delete button
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the sleep id to be deleted
     * @return - redirects back to the module homepage where it displays the user information
     */
    @GetMapping("/deleteSleep/{uId}/{id}")
    public String deleteSleepById(@PathVariable("uId") int uId, @PathVariable(value = "id") int id){
        sleepService.deleteSleepById(id);

        return "redirect:/sleep/" + uId;
    }

    /**
     * Displays the update form where a user can update a given record, if needed
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the sleep id to be deleted updated
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the update sleep form where the user can update a given sleep
     */
    @GetMapping("/updateSleepForm/{uId}/{id}")
    public String updateSleepForm(@PathVariable("uId") int uId, @PathVariable(value = "id") int id, Model model){
        Sleep sleep = sleepService.getSleepById(id);
        model.addAttribute("sleep", sleep);

        return "update-sleep";
    }
}
