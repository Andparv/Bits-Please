package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO user" +
            " (email, firstname, lastname, uid, date) " +
    "VALUES (:email, :firstname, :lastname, :uid, :date)")
    void save(@Param("email") String email, @Param("firstname") String firstname,
              @Param("lastname") String lastname, @Param("uid") String uid,
              @Param("date") LocalDate date);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email=:email")
    User findByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT COUNT(*)  FROM USER")
    Long countUsers();


}
