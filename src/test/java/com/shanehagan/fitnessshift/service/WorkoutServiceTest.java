package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.model.Workout;
import com.shanehagan.fitnessshift.repository.UserRepository;
import com.shanehagan.fitnessshift.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WorkoutServiceTest {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testGetWorkoutById(){
        User userTest = new User();
        userTest.setFirstName("Test");
        userTest.setLastName("Testing");
        userTest.setPassword("1234");
        userTest.setPhoneNumber("111-222-3333");
        userTest.setDateOfBirth("01-01-01");
        userTest.setEmail("test@email.com");
        userTest.setHeight(60);
        userTest.setWeight(120);
        userTest.setUserFood(null);
        userTest.setUserWorkouts(null);
        userTest.setUserSleep(null);
        userTest.setUserWater(null);
        userRepository.save(userTest);

        Workout expectedWorkout = new Workout();
        expectedWorkout.setUser(userTest);
        expectedWorkout.setWorkoutName("Test Workout");
        expectedWorkout.setWorkoutCategory("Test Category");
        expectedWorkout.setEquipment("Test Equipment");
        expectedWorkout.setDuration(60);
        expectedWorkout.setDate(LocalDate.now());
        expectedWorkout.setTime(LocalTime.now());
        workoutRepository.save(expectedWorkout);

        Workout actualWorkout = workoutService.getWorkoutById(expectedWorkout.getWrkId());

        assertEquals(actualWorkout, expectedWorkout);

        workoutRepository.deleteById(expectedWorkout.getWrkId());
        userRepository.deleteById(userTest.getuId());
    }
}
