package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.model.Workout;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WorkoutRepositoryTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testFindWorkoutByUser_uId(){
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

        Workout testWorkout = new Workout();
        testWorkout.setUser(userTest);
        testWorkout.setWorkoutName("Test Workout");
        testWorkout.setWorkoutCategory("Test Category");
        testWorkout.setEquipment("Test Equipment");
        testWorkout.setDuration(60);
        testWorkout.setDate(LocalDate.now());
        testWorkout.setTime(LocalTime.now());
        workoutRepository.save(testWorkout);

        List<Workout> expectedWorkout = new ArrayList<Workout>();
        expectedWorkout.add(testWorkout);

        List<Workout> actualWorkout = workoutRepository.findWorkoutByUser_uId(userTest.getuId());

        assertEquals(actualWorkout, expectedWorkout);

        workoutRepository.deleteById(testWorkout.getWrkId());
        userRepository.deleteById(userTest.getuId());
    }
}
