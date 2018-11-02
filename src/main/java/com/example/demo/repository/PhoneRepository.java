package com.example.demo.repository;

import com.example.demo.entities.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("phoneRepository")
public interface PhoneRepository extends CrudRepository<Phone,Long>{

}
