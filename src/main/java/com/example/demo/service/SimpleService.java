package com.example.demo.service;

import com.example.demo.Entity.SimpleEntity;
import com.example.demo.repository.SimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void delete(Long id){
        if(id == null){
            return;
        }
        simpleRepository.deleteById(id);
    }

    public void save(SimpleEntity entity) {
        if (entity == null) {
            System.err.println("entity is null");
            return;
        }
        simpleRepository.save(entity);
    }

    public void change(String id, SimpleEntity entity) {
        boolean conditionEntityNotNull = entity == null;
        boolean conditionIdIsCorrect = id == null || id.isEmpty();
        if(conditionEntityNotNull ||conditionIdIsCorrect){
            System.err.println("something went wrong");
            return;
        }
        entity.setId(Long.getLong(id));
        simpleRepository.save(entity);
    }

    public SimpleEntity fillById(Long id){
        Optional<SimpleEntity> optionalSimpleEntity = simpleRepository.findById(id);
        return optionalSimpleEntity.orElse(null);
        //добавить нотификацию, позже
    }
}
