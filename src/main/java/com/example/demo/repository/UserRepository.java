package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM v_users WHERE email=(:email)", nativeQuery = true)
    User findByEmail(@Param("email") String email);
}
