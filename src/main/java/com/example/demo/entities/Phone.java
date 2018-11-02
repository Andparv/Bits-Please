package com.example.demo.entities;

import javax.persistence.*;

@Table(name = "phone")
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    private String imageAadress;

    @Column(name = "manifacturer")
    private String manifacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private double price;

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