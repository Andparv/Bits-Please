package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/login");
        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("users", user);
        model.setViewName("/signup");

        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registred successfully!");
            model.addObject("users", new User());
            model.setViewName("/signup");
        }

        return model;
    }
    @RequestMapping(value = {"/HomePage"}, method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("/home");
        return model;
    }
    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied(){
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
}
