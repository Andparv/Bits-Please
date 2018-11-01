package com.example.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, Principal user) {

        if (user==null){
            model.addAttribute("currentuser", "noBodyHome");
        }
        else
        {
            model.addAttribute("currentuser", user.getName());
        }
        return "HomePage";
    }
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(){
        return "map";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "signup";
    }

}

