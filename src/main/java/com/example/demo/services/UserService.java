package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(Principal principal) {
        if (principal == null) {
            return null;
        }

        Map<String, Object> info = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        String email = (String) info.get("email");

        User user = userRepository.findByEmail(email);
        return user;
    }

    public User getUser(String email){
        return userRepository.findByEmail(email);
    }

    public void addUser(Principal principal){
        Map<String, Object> info = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String uid = (String) info.get("id");
        String firstname = (String) info.get("given_name");
        String lastname = (String) info.get("family_name");
        String email = (String) info.get("email");
        userRepository.save(email, firstname, lastname, uid, LocalDate.now());
    }
}