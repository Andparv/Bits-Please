package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(Principal principal) throws Exception {
        Map<String, Object> info = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        System.out.println(info);
        String email = (String) info.get("email");
        if (userService.getUser(email)!=null){
            return "HomePage";
        }
        userService.addUser(principal);
        return "HomePage";
    }
}
