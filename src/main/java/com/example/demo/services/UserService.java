package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private EntityManager entityManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public User updateUserStatus(Principal principal){
        Map<String,Object> info = (Map<String,Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        String uid = (String)info.get("sub");
        String firstname = (String)info.get("given_name");
        String lastname = (String)info.get("family_name");
        String email = (String)info.get("email");
        String password = passwordEncoder().encode((String)info.get("password"));

        User user = userRepository.findByEmail(email);

        if (user==null){
            user = new User();
            user.setEmail(email);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUid(uid);
            user.setPassword(password);
            userRepository.save(user);
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