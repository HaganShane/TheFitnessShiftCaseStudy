package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {"test@email.com", "test2@email.com", "test3@email.com"})
    @Transactional
    public void testFindUserByEmail(String email){
        User expectedUser = new User();
        expectedUser.setFirstName("Test");
        expectedUser.setLastName("Testing");
        expectedUser.setPassword("1234");
        expectedUser.setPhoneNumber("111-222-3333");
        expectedUser.setDateOfBirth("01-01-01");
        expectedUser.setEmail(email);
        expectedUser.setHeight(60);
        expectedUser.setWeight(120);
        expectedUser.setUserFood(null);
        expectedUser.setUserWorkouts(null);
        expectedUser.setUserSleep(null);
        expectedUser.setUserWater(null);
        userRepository.save(expectedUser);

        User actualUser = userRepository.findByEmail(email);

        assertEquals(expectedUser, actualUser);
    }
}
