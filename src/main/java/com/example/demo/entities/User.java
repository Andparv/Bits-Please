package com.example.demo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "uid")
    private String uid;

    @Column(name = "date")
    private LocalDate date;

    public User(String email, String firstname, String lastname, String uid, LocalDate date) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.uid = uid;
        this.date = date;
    }
    public User(){
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