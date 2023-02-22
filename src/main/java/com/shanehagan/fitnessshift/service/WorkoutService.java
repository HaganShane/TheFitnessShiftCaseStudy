/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * WorkoutService class that implements our methods to get user lists of workout, add workout, delete workout, or update workout
 */

package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.Workout;
import com.shanehagan.fitnessshift.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    /**
     * Autowire field injection for WorkoutRepository
     */
    @Autowired
    private WorkoutRepository workoutRepository;

    /**
     * Method to find a list of workouts for this specific user
     * @param uId - takes in a userId
     * @return - returns a list of workouts for this userId
     */
    public List<Workout> getWorkoutsByUserId(int uId){
        return workoutRepository.findWorkoutByUser_uId(uId);
    }

    /**
     * Method to add workout to the database
     * @param workout - takes in the workout added by the user
     * @return - returns a workout object to be added to the database
     */
    public Workout addWorkout(Workout workout){
        return workoutRepository.save(workout);
    }

    /**
     * Deletes selected workout by its id
     * @param id - id relative to that workout
     */
    public void deleteWorkoutById(int id){
        workoutRepository.deleteById(id);
    }

    /**
     * Gets specific workout by its id - used for update
     * @param id - id for the specific workout
     * @return - returns the referenced workout object
     */
    public Workout getWorkoutById(int id){
        return workoutRepository.getReferenceById(id);
    }


}
