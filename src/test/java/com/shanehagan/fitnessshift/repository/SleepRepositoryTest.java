package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.Sleep;
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
public class SleepRepositoryTest {

    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testFindSleepByUser_uId(){
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

        Sleep testSleep = new Sleep();
        testSleep.setUser(userTest);
        testSleep.setHours(10);
        testSleep.setDate(LocalDate.now());
        testSleep.setTime(LocalTime.now());
        sleepRepository.save(testSleep);

        List<Sleep> expectedSleep = new ArrayList<Sleep>();
        expectedSleep.add(testSleep);

        List<Sleep> actualSleep = sleepRepository.findSleepByUser_uId(userTest.getuId());

        assertEquals(actualSleep, expectedSleep);

        sleepRepository.deleteById(testSleep.getsId());
        userRepository.deleteById(userTest.getuId());
    }
}
