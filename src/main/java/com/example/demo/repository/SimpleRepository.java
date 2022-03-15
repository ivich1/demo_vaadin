package com.example.demo.repository;

import com.example.demo.entity.SimpleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SimpleRepository extends CrudRepository<SimpleEntity, Long> {

    @Query("select c from SimpleEntity c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.surname) like lower(concat('%', :searchTerm, '%'))")
    List<SimpleEntity> findByFilter(@Param("searchTerm") String searchTerm);
}
