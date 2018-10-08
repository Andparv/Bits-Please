package com.example.demo.entities;

import com.example.demo.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository PhoneRepository;

        @RequestMapping(value = "/phones", method = RequestMethod.POST)
                public ResponseEntity addDefault(){
                Phone phone = new Phone();
                phone.setManifacturer("Apple");
                phone.setModel("Xs");
                PhoneRepository.save(phone);
                return ResponseEntity.ok().build();

    }
}
