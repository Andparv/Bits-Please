package com.example.demo.services;

import com.example.demo.entities.Phone;
import com.example.demo.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService {

    @Qualifier("phoneRepository")
    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> listAll(){
        List<Phone> phoneList = new ArrayList<>();
        phoneRepository.findAll().forEach(phoneList::add);
        return phoneList;
    }
    public List<Phone> listCartAll(){
        List<Phone> phonesInCartList = new ArrayList<>();

        return phonesInCartList;
    }

}
