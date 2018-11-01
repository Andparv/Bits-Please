package com.example.demo.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "uid")
    private String uid;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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