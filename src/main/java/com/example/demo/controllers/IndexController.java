package com.example.demo.controllers;

import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, Principal user) {

        if (user==null){
            model.addAttribute("currentuser", "noBodyHome");
        }
        else
        {
            model.addAttribute("currentuser","someBodyHome");
        }
        model.addAttribute("usercount", userRepository.countUsers());
        return "HomePage";
    }

	@RequestMapping(value = "/ee", method = RequestMethod.GET)
    public String estHome(Model model,Principal user) {

        if (user==null){
            model.addAttribute("currentuser", "noBodyHome");
        }
        else
        {
            model.addAttribute("currentuser","someBodyHome");
        }
        model.addAttribute("usercount", userRepository.countUsers());
        return "ee";
    }
	
	@RequestMapping("/cart", method = RequestMethod.GET)
    public String cart(){
        return "cart";
    }
	
    @GetMapping("/register")
    public String registerPrompt(Principal principal) {
        if (userService.getUser(principal) != null) {
            return "redirect:/";
        }
        return "register";
    }
    @RequestMapping("/user")
    public @ResponseBody
    Principal user(Principal principal) {
        return principal;
    }
}