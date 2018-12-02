package com.example.demo.services;

import com.example.demo.controllers.SmartIdController;
import com.example.demo.controllers.SmartIdRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartService {

    @Autowired
    SmartIdController smartIdController;

    @Autowired
    SmartIdRestController smartIdRestController;

}
