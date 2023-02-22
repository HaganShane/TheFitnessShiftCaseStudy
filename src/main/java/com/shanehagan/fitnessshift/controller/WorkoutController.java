/**
 * @Author: Shane Hagan
 * Date: 2/14/2023
 * Workout Controller to handle requests specific to the workout module.
 * Able to show the workout table specific to the user signed in, add workout for that user,
 * update workout for that user, or delete workout for that user.
 * Uses @GetMapping to show, @PostMapping to process, @PathVariables to pass the userId around,
 * Model to add attributes to be displayed via Thymeleaf, @ModelAttribute to add the specific model.
 */

package com.shanehagan.fitnessshift.controller;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.model.Workout;
import com.shanehagan.fitnessshift.repository.UserRepository;
import com.shanehagan.fitnessshift.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class WorkoutController {

    /**
     * Declare some constants here using the final tag
     */
    private final WorkoutService workoutService;
    private final UserRepository userRepository;

    /**
     * Autowire via construction injection rather than field injection
     * @param workoutService - declared above, our workoutService obj
     * @param userRepository - declare above, our userRepository obj
     */
    @Autowired
    public WorkoutController(WorkoutService workoutService, UserRepository userRepository){
        this.workoutService = workoutService;
        this.userRepository = userRepository;
    }

    /**
     * Display the workouts relative to the user
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the module homepage where it displays the user information
     */
    @GetMapping("/workout/{uId}")
    public String showWorkouts(@PathVariable("uId") int uId, Model model){
        List<Workout> workoutList = workoutService.getWorkoutsByUserId(uId);
        model.addAttribute("workoutList", workoutList);

        return "workout";
    }

    /**
     * Display the add workout form on a separate page
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the add-workout.html page where a user can add a new workout
     */
    @GetMapping("/addWorkoutForm/{uId}")
    public String addWorkoutForm(@PathVariable("uId") int uId, Model model){
        Workout workout = new Workout();
        model.addAttribute("workout", workout);

        return "add-workout";
    }

    /**
     * Post mapping after the user adds a workout to save it to the database
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param workout - model attribute to save to the database
     * @return - redirects back to the module homepage where it displays the user information
     */
    @PostMapping("/addWorkout/{uId}")
    public String addWorkout(@PathVariable("uId") int uId, @ModelAttribute("workout") Workout workout){
        Optional<User> user = userRepository.findById(uId);
        workout.setUser(user.get());

        workoutService.addWorkout(workout);

        return "redirect:/workout/" + uId;
    }

    /**
     * Deletes workout after the user clicks the delete button
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the workout id to be deleted
     * @return - redirects back to the module homepage where it displays the user information
     */
    @GetMapping("/deleteWorkout/{uId}/{id}")
    public String deleteWorkoutById(@PathVariable(value = "uId") int uId, @PathVariable(value = "id") int id){
        workoutService.deleteWorkoutById(id);

        return "redirect:/workout/" + uId;
    }

    /**
     * Displays the update form where a user can update a given record, if needed
     * @param uId - passing the userId via the URL to be used throughout this module
     * @param id - passing the workout id to be deleted updated
     * @param model - model where we can add attribute to be used for displaying in thymeleaf
     * @return - returns the update workout form where the user can update a given workout
     */
    @GetMapping("/updateWorkoutForm/{uId}/{id}")
    public String updateWorkoutForm(@PathVariable(value = "uId") int uId, @PathVariable(value = "id") int id, Model model){
        Workout workout = workoutService.getWorkoutById(id);
        model.addAttribute("workout", workout);

        return "update-workout";
    }


}
