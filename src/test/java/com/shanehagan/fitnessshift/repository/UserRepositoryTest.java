package com.shanehagan.fitnessshift.repository;

import com.shanehagan.fitnessshift.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testFindByEmail(){
        User expectedUser = new User();
        expectedUser.setFirstName("Test");
        expectedUser.setLastName("Testing");
        expectedUser.setPassword("1234");
        expectedUser.setPhoneNumber("111-222-3333");
        expectedUser.setDateOfBirth("01-01-01");
        expectedUser.setEmail("test@email.com");
        expectedUser.setHeight(60);
        expectedUser.setWeight(120);
        expectedUser.setUserFood(null);
        expectedUser.setUserWorkouts(null);
        expectedUser.setUserSleep(null);
        expectedUser.setUserWater(null);
        userRepository.save(expectedUser);

        User actualUser = userRepository.findByEmail("test@email.com");

        assertEquals(expectedUser, actualUser);
    }
}
