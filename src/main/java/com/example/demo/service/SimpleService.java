package com.example.demo.service;

import com.example.demo.Entity.SimpleEntity;
import com.example.demo.repository.SimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleService {

    @Autowired
    private final SimpleRepository simpleRepository;

    public SimpleService(SimpleRepository simpleRepository){
        this.simpleRepository = simpleRepository;
    }

    public List<SimpleEntity> findAll(String filter){
        if(filter == null || filter.isEmpty()){
            return (List<SimpleEntity>) simpleRepository.findAll();
        } else {
            return simpleRepository.findByFilter(filter);
        }
    }

    public void delete(SimpleEntity entity) {
        simpleRepository.delete(entity);
    }

    public void save(SimpleEntity entity) {
        if (entity == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        simpleRepository.save(entity);
    }



}
