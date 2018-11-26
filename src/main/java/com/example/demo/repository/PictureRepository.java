package com.example.demo.repository;

import com.example.demo.entities.Picture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PictureRepository extends CrudRepository<Picture, Integer>{

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO pictures" +
            " (id, uid, picture) " +
            "VALUES (:id, :uid, :picture) ")
    void save(@Param ("id") Integer id, @Param("uid") String uid, @Param("picture") String picture);

    @Query(nativeQuery = true, value = "SELECT picture.picture_name " +
            "FROM user INNER JOIN picture ON picture.uid=user.uid WHERE email=:email ORDER BY 1")
    String findPicture(@Param("email") String email);

}
