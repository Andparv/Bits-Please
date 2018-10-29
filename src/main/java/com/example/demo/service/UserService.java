package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User updateUserStatus(Principal principal){
        Map<String,Object> info = (Map<String,Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        String uid = (String)info.get("sub");
        String firstname = (String)info.get("given_name");
        String lastname = (String)info.get("family_name");
        String email = (String)info.get("email");

        User user = userRepository.findByEmail(email);

        if (user==null){
            user = new User();
            user.setEmail(email);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUid(uid);

        }
        return user;

    }

    public User getUser(Principal principal){
        if (principal==null){
            return null;
        }
        Map<String,Object> info = (Map<String,Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        User user = userRepository.findByEmail((String)info.get("email"));

        if (user==null){
            return updateUserStatus(principal);
        }
        return user;
    }

}
