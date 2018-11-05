package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("email") String email, Principal principal){
        if (userService.getUser(email)!=null){
            return "HomePage";
        }
        userService.addUser(email, principal);
        return "HomePage";
    }
}
