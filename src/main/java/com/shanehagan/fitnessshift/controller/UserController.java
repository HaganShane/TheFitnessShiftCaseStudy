/**
 * @Author: Shane Hagan
 * Date: 2/14/2023
 * User Controller to handle requests specific to the user
 * Displays the dashboard specific to a user
 */

package com.shanehagan.fitnessshift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    /**
     * Displays the dashboard unique to a user's ID
     * @param uId - passing the userId via the URL to be used throughout this module
     * @return - returns dashboard.html
     */
    @GetMapping("/dashboard/{uId}")
    public String showUserDashboard(@PathVariable("uId") int uId){
        return "dashboard";
    }
}
