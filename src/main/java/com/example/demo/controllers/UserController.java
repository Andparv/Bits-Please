package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity registerUser(@ModelAttribute("email") String email, Principal principal){
        if (userService.getUser(principal)!=null){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.addUser(email, principal);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
