/**
 * @Author: Shane Hagan
 * Date: 2/14/2023
 * Main Controller to handle requests specific to the login and signup module.
 * Also maps to the main page - essentially used for the main app functions, not specific to a module
 */

package com.shanehagan.fitnessshift.controller;

import com.shanehagan.fitnessshift.model.User;
import com.shanehagan.fitnessshift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.SecureRandom;


@Controller
public class MainController {

    /**
     * Autowire via field injection
     */
    @Autowired
    private UserService userService;

    /**
     * Shows the homepage, defaults to the main page
     * @return - returns the homepage.html
     */
    @GetMapping("/")
    public String showHomepage(){
        return "homepage";
    }

    /**
     * Shows the login page
     * @return - returns the login.html
     */
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    /**
     * Shows the signup page
     * @param model - adds the user model to the attribute for thymeleaf access
     * @return - returns the signup.html
     */
    @GetMapping("/signup")
    public String showSignup(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    /**
     * Saves the user sign up information. checks if it already exists
     * If it does exist, will display a message telling them.
     * Otherwise, will create it for them
     * Implements BCrypt Encoding
     * @param user - takes in the user information
     * @param model - used to display our errors if they input bad information
     * @return - returns the signup.html form either way
     * @throws Exception - if they enter a bad or wrong value in the field, the exception will catch and display
     */
    @PostMapping("/signup/save")
    public String processSignup(@ModelAttribute("user") User user, Model model) throws Exception{
        User existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            model.addAttribute("signupError","There is already an account associated with that email. Please try to login instead.");
            return "signup";
        }
        try{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(6, new SecureRandom());
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            System.out.println(encodedPassword);
            user.setPassword(encodedPassword);
            userService.addUser(user);
            model.addAttribute("signupSuccess","Account created successfully! Please go to the login page to login.");
            return "signup";
        }
        catch (Exception e){
            model.addAttribute("signupError", "Invalid format was entered, please try again");
            return "signup";
        }

    }

    /**
     * Processes the user trying to log in
     * Implements BCrypt Encoding
     * @param user - takes in the user information
     * @param model - model attribute to display a message based on if they have an unsuccessful login or not
     * @return - returns the login.html if there is an error, otherwise it would redirect them to the dashboard with their userId
     * Checks to see if their information is valid
     */
    @PostMapping("/login/save")
    public String processLogin(@ModelAttribute("user") User user, Model model){
        User existingUser = userService.findUserByEmail(user.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (existingUser == null){
            model.addAttribute("unsuccessfulLogin", "Email not recognized. Be sure it is entered correctly, or create an account.");
            return "login";
        }
        else if (existingUser != null && !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            model.addAttribute("unsuccessfulLogin", "Password is not correct. Be sure it is entered correctly and try again");
            return "login";
        }
        else if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            int uId = existingUser.getuId();
            model.addAttribute("uId", uId);
            return "redirect:/dashboard/" + uId;
        }
        return "login";
    }
}
