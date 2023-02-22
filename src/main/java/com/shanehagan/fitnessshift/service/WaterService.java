/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * WaterService class that implements our methods to get user lists of water, add water, delete water, or update water
 */

package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.Water;
import com.shanehagan.fitnessshift.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterService {

    /**
     * Autowire field injection for WaterRepository
     */
    @Autowired
    private WaterRepository waterRepository;

    /**
     * Method to find a list of waters for this specific user
     * @param uId - takes in a userId
     * @return - returns a list of waters for this userId
     */
    public List<Water> getWatersByUserId(int uId){
        return waterRepository.findWaterByUser_uId(uId);

    }

    /**
     * Method to add water to the database
     * @param water - takes in the water added by the user
     * @return - returns a water object to be added to the database
     */
    public Water addWater(Water water){
        return waterRepository.save(water);
    }

    /**
     * Deletes selected water by its id
     * @param id - id relative to that water
     */
    public void deleteWaterById(int id){
        waterRepository.deleteById(id);
    }

    /**
     * Gets specific water by its id - used for update
     * @param id - id for the specific water
     * @return - returns the referenced water object
     */
    public Water getWaterById(int id){
        return waterRepository.getReferenceById(id);
    }
}
