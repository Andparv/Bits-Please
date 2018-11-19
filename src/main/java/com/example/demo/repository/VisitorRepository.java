package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VisitorRepository extends CrudRepository<User, Integer>{

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO visitors" +
            " (dateANDtime, IPaddress, platform) " +
            "VALUES (:dateANDtime, :IPaddress, :platform) ")
    void save(@Param ("dateANDtime") Integer dateANDtime, @Param("IPaddress") String IPaddress, @Param("platform") String platform);

    

}