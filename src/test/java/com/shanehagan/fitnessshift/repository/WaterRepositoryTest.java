package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.model.Water;
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
public class WaterRepositoryTest {

    @Autowired
    WaterRepository waterRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testFindWaterByUser_uId(){
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

        Water testWater = new Water();
        testWater.setUser(userTest);
        testWater.setAmount(100);
        testWater.setDate(LocalDate.now());
        testWater.setTime(LocalTime.now());
        waterRepository.save(testWater);

        List<Water> expectedWater = new ArrayList<Water>();
        expectedWater.add(testWater);

        List<Water> actualWater = waterRepository.findWaterByUser_uId(userTest.getuId());

        assertEquals(actualWater, expectedWater);

        waterRepository.deleteById(testWater.getWtrId());
        userRepository.deleteById(userTest.getuId());
    }
}
