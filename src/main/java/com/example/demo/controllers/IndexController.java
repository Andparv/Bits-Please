package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String welcome(Model model) {
        //model.addAttribute("text", "This is text");
        return "HomePage";
    }
}
