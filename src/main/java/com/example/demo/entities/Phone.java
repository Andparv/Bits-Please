package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public void setImageAadress(String imageAadress) {
        this.imageAadress = imageAadress;
    }

    public void setManifacturer(String manifacturer) {
        this.manifacturer = manifacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String imageAadress;
    private String manifacturer;
    private String model;



}
