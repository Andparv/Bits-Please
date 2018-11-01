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
    public String imageAadress;
    public String manifacturer;
    public String model;
    public double price;

    public String getImageAadress() {
        return imageAadress;
    }

    public void setImageAadress(String imageAadress) {
        this.imageAadress = imageAadress;
    }

    public String getManifacturer() {
        return manifacturer;
    }

    public void setManifacturer(String manifacturer) {
        this.manifacturer = manifacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}