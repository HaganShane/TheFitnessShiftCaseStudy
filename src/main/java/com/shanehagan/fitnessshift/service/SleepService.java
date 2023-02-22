/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * SleepService class that implements our methods to get user lists of sleep, add sleep, delete sleep, or update sleep
 */

package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.Sleep;
import com.shanehagan.fitnessshift.repository.SleepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepService {

    /**
     * Autowire field injection for SleepRepository
     */
    @Autowired
    private SleepRepository sleepRepository;

    /**
     * Method to find a list of sleeps for this specific user
     * @param uId - takes in a userId
     * @return - returns a list of sleeps for this userId
     */
    public List<Sleep> getSleepsByUserId(int uId){
        return sleepRepository.findSleepByUser_uId(uId);
    }

    /**
     * Method to add sleep to the database
     * @param sleep - takes in the sleep added by the user
     * @return - returns a sleep object to be added to the database
     */
    public Sleep addSleep(Sleep sleep){
        return sleepRepository.save(sleep);
    }

    /**
     * Deletes a selected sleep by its id
     * @param id - id relative to that sleep
     */
    public void deleteSleepById(int id){
        sleepRepository.deleteById(id);
    }

    /**
     * Gets a specific sleep by its id - used for update
     * @param id - id for the specific sleep
     * @return - returns the referenced sleep object
     */
    public Sleep getSleepById(int id){
        return sleepRepository.getReferenceById(id);
    }
}
