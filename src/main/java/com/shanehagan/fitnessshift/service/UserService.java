/**
 * @Author: Shane Hagan
 * Date: 2/16/2023
 * UserService class that implements our methods to get find a user by their email and add a new user
 */

package com.shanehagan.fitnessshift.service;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    /**
     * Autowire via field injection
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Find a user by a given email
     * @param email - string of an email passed in
     * @return - returns a user object based on that string
     */
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * Adds a user to the database
     * @param user - takes in a user object, sets those values to the new user and saves it to the db
     */
    public void addUser(User user){
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setWeight(user.getWeight());
        newUser.setHeight(user.getHeight());
        newUser.setPassword(user.getPassword());

        userRepository.save(newUser);
    }
}
