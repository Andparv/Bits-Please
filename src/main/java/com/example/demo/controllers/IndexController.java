package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        //model.addAttribute("text", "This is text");
        return "HomePage";
    }
    @RequestMapping(value = "/redirectlogin", method = RequestMethod.GET)
        public String redirectlogin() {
            return "redirect:login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
        public String login(){
            return "login";
    }

    @RequestMapping(value = "redirectmap", method = RequestMethod.GET)
    public String redirectmap() {
        return "redirect:map";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(){
        return "map";
    }
}

