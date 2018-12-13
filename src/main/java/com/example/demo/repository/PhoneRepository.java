package com.example.demo.repository;

import com.example.demo.entities.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("phoneRepository")
public interface PhoneRepository extends CrudRepository<Phone,Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM phone WHERE id=:id")
    Phone findById(@Param("id") int id);


}
