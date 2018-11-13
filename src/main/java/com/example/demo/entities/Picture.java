package com.example.demo.entities;


import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @Column
    public Integer id;

    @Column(name = "uid")
    public String uid;


    @Column(name = "pictureName")
    public String pictureName;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "pictureName")
    public User user;

    public Picture(){
    }

    public Picture(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
    //with their getter and setters
}