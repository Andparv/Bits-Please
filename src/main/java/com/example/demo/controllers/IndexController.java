package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, Principal user, String email) {

        if (user==null){
            model.addAttribute("currentuser", "noBodyHome");
        }
        else
        {
            Map<String, Object> info = (Map<String, Object>) ((OAuth2Authentication) user).getUserAuthentication().getDetails();

            model.addAttribute("currentuser", userService.getUser((String) info.get("email"), user));
        }
        return "HomePage";
    }
    @GetMapping("/register")
    public String registerPrompt(Principal principal) {
        if (userService.getUser(principal) != null) {
            return "redirect:/";
        }
        return "register";
    }
}