package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.Food;
import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.repository.FoodRepository;
import com.shanehagan.fitnessshift.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    FoodService foodService;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    public void testGetFoodById(){
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

        Food expectedFood = new Food();
        expectedFood.setUser(userTest);
        expectedFood.setMealName("Test Meal");
        expectedFood.setMealCategory("Test Category");
        expectedFood.setCalories(500);
        expectedFood.setDate(LocalDate.now());
        expectedFood.setTime(LocalTime.now());
        foodRepository.save(expectedFood);

        Food actualFood = foodService.getFoodById(expectedFood.getfId());

        assertEquals(actualFood, expectedFood);

        foodRepository.deleteById(expectedFood.getfId());
        userRepository.deleteById(userTest.getuId());
    }
}
