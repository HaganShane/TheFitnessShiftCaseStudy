/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * FoodService class that implements our methods to get user lists of food, add food, delete food, or update food
 */

package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.Food;
import com.shanehagan.fitnessshift.repository.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    /**
     * Autowire field injection for FoodRepository
     */
    @Autowired
    private FoodRepository foodRepository;

    private Logger logging = LoggerFactory.getLogger(FoodService.class);

    /**
     * Method to find a list of foods for this specific user
     * @param uId - takes in a userId
     * @return - returns a list of foods for this userId
     */
    public List<Food> getFoodsByUserId(int uId){
        return foodRepository.findFoodByUser_uId(uId);
    }

    /**
     * Method to add food to the database
     * @param food - takes in the food added by the user
     * @return - returns a food object to be added to the database
     */
    public Food addFood(Food food){
        return foodRepository.save(food);
    }

    /**
     * Deletes a selected food by its id
     * @param id - id relative to that food
     */
    public void deleteFoodById(int id){
        foodRepository.deleteById(id);
    }

    /**
     * Gets a specific food by its id - used for update
     * @param id - id for the specific food
     * @return - returns the referenced food object
     */
    public Food getFoodById(int id){
        try{
            return foodRepository.getReferenceById(id);
        }
        catch (Exception e){
            logging.error("ERROR OCCURRED!: " + e.getMessage());
            return null;
        }
    }
}
