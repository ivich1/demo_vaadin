package com.example.demo.service;

import com.example.demo.view.SimpleView;
import com.example.demo.entity.SimpleEntity;
import com.example.demo.repository.SimpleRepository;
import com.example.demo.view.SimpleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleService {

    @Autowired
    private final SimpleRepository simpleRepository;

    public SimpleService(SimpleRepository simpleRepository){
        this.simpleRepository = simpleRepository;
    }

    public List<SimpleView> findAll(String filter){
        if(filter == null || filter.isEmpty()){
            return SimpleView.toSimpleView(simpleRepository.findAll());
        } else {
            return SimpleView.toSimpleView(simpleRepository.findByFilter(filter));
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

    public void change(Long id, SimpleEntity entity) {
        SimpleEntity toSave = simpleRepository.findById(id).orElseThrow();

        toSave.setName(entity.getName());
        toSave.setSurname(entity.getSurname());
        toSave.setNumber(entity.getNumber());
        toSave.setSalary(entity.getSalary());
        toSave.setHiringDate(entity.getHiringDate());

        simpleRepository.save(toSave);
    }

    public SimpleEntity fillById(Long id){
        Optional<SimpleEntity> optionalSimpleEntity = simpleRepository.findById(id);
        return optionalSimpleEntity.orElse(null);
    }

    public void fillTable(){
        int size = 3;
        String[] names = {"ivan", "qwe", "mr3"};
        String[] surnames = {"ch", "qwe", "333"};
        int[] numbers = {123456, 123, 333};
        double[] salaries = {123.3, 1234566788, 333.333 };
        Calendar[] hiringDates = {
                new GregorianCalendar(1,1,1),
                new GregorianCalendar(32,12,23),
                new GregorianCalendar(3333,3,3)
        };
        for(int i =0; i<size;i++){
            simpleRepository.save(new SimpleEntity(
                    names[i],
                    surnames[i],
                    numbers[i],
                    salaries[i],
                    hiringDates[i]
            ));
        }
    }
}
