package com.example.demo.controllers;

import com.example.demo.entities.Phone;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PhoneController {
    @Qualifier("phoneRepository")
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<Void> addPhone(@RequestBody Phone phone){
        phoneRepository.save(phone);
        return ResponseEntity.ok().build();
    }
    @RequestMapping("/store")
    public String phoneList(Model model){
        model.addAttribute("phoneList", phoneService.listAll());
        return "store";
    }

    @RequestMapping(value = "/buyPhone/{id}", method = RequestMethod.GET)
    public String getBootById(Model model, @PathVariable("id") Long id){
        Optional<Phone> phone = phoneRepository.findById(id);
        if (phone.isPresent()){
            model.addAttribute("phone", phone.get());
        }
        else {
            model.addAttribute("phone", new Phone());
        }
        return "phone";
    }

}
