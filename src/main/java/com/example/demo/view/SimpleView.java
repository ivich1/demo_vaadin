package com.example.demo.view;

import com.example.demo.entity.SimpleEntity;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class SimpleView {

    private Long id;
    private String name;
    private String surname;
    private int number;
    private double salary;
    private String hiringDate;

    public SimpleView(){

    }

    public SimpleView(SimpleEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.number = entity.getNumber();
        this.salary = entity.getSalary();

        var tmp = entity.getHiringDate();
        var d = Integer.toString(tmp.get(Calendar.DAY_OF_MONTH));
        var m = Integer.toString(tmp.get(Calendar.MONTH));
        var y = Integer.toString(tmp.get(Calendar.YEAR));

        this.hiringDate = d + "-" + m + "-" + y;
    }

    public static List<SimpleView> toSimpleView(Iterable<SimpleEntity> source){
        LinkedList<SimpleView> result = new LinkedList<SimpleView>();
        for(SimpleEntity unit : source){
            result.add(new SimpleView(unit));
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String stringDate) {
        this.hiringDate = stringDate;
    }
}
