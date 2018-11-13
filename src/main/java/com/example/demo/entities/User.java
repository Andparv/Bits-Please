package com.example.demo.entities;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "email")
    public String email;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uid", nullable = false, updatable = false, insertable = false)
    public Picture pictureName;

    @Column(name = "uid")
    public String uid;

    @Column(name = "date")
    public LocalDate date;

    public User(String email, String firstname, String lastname, String uid, LocalDate date) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.uid = uid;
        this.date = date;
    }
    public User(){
    }

    public void setPictureName(Picture pictureName) {
        this.pictureName = pictureName;
    }

    public Picture getPictureName() {
        return pictureName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) {
            return false;
        }
        User user = (User)obj;
        if(this.uid.equals(user.uid)) {
            return true;
        }
        else if(this.email.equals(user.email)) {
            return true;
        }
        return false;
    }


}