package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PictureRepository extends CrudRepository<User, Integer>{

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO pictures" +
            " (uid, picture) " +
            "VALUES (:uid, :picture)")
    void save(@Param("uid") String uid, @Param("picture") String picture);

    @Query(nativeQuery = true, value = "SELECT pictures.pictureName , user.email " +
            "FROM user INNER JOIN pictures ON pictures.uid=user.uid WHERE email=:email ORDER BY 1")
    User findPicture(@Param("email") String email);

}
