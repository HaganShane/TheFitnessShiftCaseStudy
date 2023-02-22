/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * WorkoutRepository that extends the JpaRepository
 * Used for various functions in controller and service, such as adding, deleting, updating.
 * Added our own method to find workout records specific to a userId - this is used when displaying information on that
 * module page matching a userId entry (so it only displays their information, as opposed to all).
 */

package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    /**
     * Custom method to find all workout for a specific user
     * @param uId - takes in the user id
     * @return - returns a list of workout relative to that specific userId
     */
    List<Workout> findWorkoutByUser_uId(Integer uId);
}
