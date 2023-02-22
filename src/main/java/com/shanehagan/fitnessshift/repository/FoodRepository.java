/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * FoodRepository that extends the JpaRepository
 * Used for various functions in controller and service, such as adding, deleting, updating.
 * Added our own method to find food records specific to a userId - this is used when displaying information on that
 * module page matching a userId entry (so it only displays their information, as opposed to all).
 */

package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    /**
     * Custom method to find all food for a specific user
     * @param uId - takes in the user id
     * @return - returns a list of food relative to that specific userId
     */
    List<Food> findFoodByUser_uId(Integer uId);
}
