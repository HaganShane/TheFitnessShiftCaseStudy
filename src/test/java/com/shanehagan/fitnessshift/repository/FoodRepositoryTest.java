package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.Food;
import com.shanehagan.fitnessshift.model.User;
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
public class FoodRepositoryTest {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testFindFoodByUser_uId(){
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

        Food testFood = new Food();
        testFood.setUser(userTest);
        testFood.setMealName("Test Meal");
        testFood.setMealCategory("Test Category");
        testFood.setCalories(500);
        testFood.setDate(LocalDate.now());
        testFood.setTime(LocalTime.now());
        foodRepository.save(testFood);

        List<Food> expectedFood = new ArrayList<Food>();
        expectedFood.add(testFood);

        List<Food> actualFood = foodRepository.findFoodByUser_uId(userTest.getuId());

        assertEquals(actualFood, expectedFood);

        foodRepository.deleteById(testFood.getfId());
        userRepository.deleteById(userTest.getuId());
    }
}
