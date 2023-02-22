package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.model.Water;
import com.shanehagan.fitnessshift.repository.UserRepository;
import com.shanehagan.fitnessshift.repository.WaterRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WaterServiceTest {

    @Autowired
    WaterService waterService;

    @Autowired
    WaterRepository waterRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testGetWaterById(){
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

        Water expectedWater = new Water();
        expectedWater.setUser(userTest);
        expectedWater.setAmount(100);
        expectedWater.setDate(LocalDate.now());
        expectedWater.setTime(LocalTime.now());
        waterRepository.save(expectedWater);

        Water actualWater = waterService.getWaterById(expectedWater.getWtrId());

        assertEquals(actualWater, expectedWater);

        waterRepository.deleteById(expectedWater.getWtrId());
        userRepository.deleteById(userTest.getuId());
    }
}
