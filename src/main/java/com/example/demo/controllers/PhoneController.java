package com.example.demo.controllers;

import com.example.demo.entities.Phone;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping("/pood")
    public String teloList(Model model){
        model.addAttribute("phoneList", phoneService.listAll());
        return "pood";
    }

   /*@RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public Phone findPhone(Model model){*/
        /*Phone phone = phoneRepository.findById(id);
        model.addAttribute(phone);
        return phone;*/
     /*   Phone phone = phoneRepository.findById(1);
        model.addAttribute(phone);
     return phone;
    }*/

    @RequestMapping(value="/cart", method = RequestMethod.GET)
    public String cartList(Model model){
        model.addAttribute("cartList", phoneService.listCartAll());
        return "cart";
    }


   /* public String getBootById(Model model, @PathVariable("id") Long id){
        Optional<Phone> phone = phoneRepository.findById(id);
        if (phone.isPresent()){
            model.addAttribute("phone", phone.get());
        }
        else {
            model.addAttribute("phone", new Phone());
        }
        return "phone";
    }*/



}
