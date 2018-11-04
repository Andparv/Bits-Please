package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity registerUser(@ModelAttribute("email") String email, Principal principal){
        if (userService.getUser(email)!=null){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.addUser(email, principal);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}