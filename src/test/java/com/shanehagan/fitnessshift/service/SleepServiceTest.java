package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.Sleep;
import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.repository.SleepRepository;
import com.shanehagan.fitnessshift.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SleepServiceTest {

    @Autowired
    SleepService sleepService;

    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testGetSleepById(){
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

        Sleep expectedSleep = new Sleep();
        expectedSleep.setUser(userTest);
        expectedSleep.setHours(10);
        expectedSleep.setDate(LocalDate.now());
        expectedSleep.setTime(LocalTime.now());
        sleepRepository.save(expectedSleep);

        Sleep actualSleep = sleepService.getSleepById(expectedSleep.getsId());

        assertEquals(actualSleep, expectedSleep);

        sleepRepository.deleteById(expectedSleep.getsId());
        userRepository.deleteById(userTest.getuId());
    }
}
