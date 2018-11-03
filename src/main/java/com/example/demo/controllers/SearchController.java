package com.example.demo.controllers;

import com.example.demo.entities.Phone;
import com.example.demo.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SearchController {

    @Autowired
    private PhoneRepository phoneRepository;

    /*@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getPhoneByManifacturer(@RequestParam("manifacturer") String manifacturer, ModelMap modelMap){
        List<Phone> phone = phoneRepository.findAllByManifacturer(manifacturer);
        modelMap.addAttribute("phone", phone);

        return "search";
    }*/
}
